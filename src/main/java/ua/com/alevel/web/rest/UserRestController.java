package ua.com.alevel.web.rest;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.request.UserRequestDto;

@RestController
public class UserRestController {

    private final UserFacade userFacade;

    public UserRestController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping(value = "/add_user")
    public String addUser(@RequestBody UserRequestDto userRequestDto){
        try{
            userFacade.create(userRequestDto);
            return JSONObject.quote("done");
        } catch (EntityExistException e) {
            return JSONObject.quote("this personal is exist");
        }
    }
}
