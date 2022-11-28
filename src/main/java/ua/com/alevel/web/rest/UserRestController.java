package ua.com.alevel.web.rest;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.request.ChannelRequestDto;
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

    @PostMapping(value = "/deleteUser")
    public String delete() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userFacade.delete(1L, authentication.getName());
            return "done";
        } else {
            return "XUITA KAKAYTO";
        }
    }

    @PostMapping(value = "/updateUser")
    public String update(@RequestBody UserRequestDto userRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userFacade.update(1L, userRequestDto);
            return "done";
        } else {
            return "XUITA KAKAYTO";
        }
    }
}
