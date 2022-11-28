package ua.com.alevel.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.response.UserResponseDto;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping("/update")
    public String create(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("isLogin", true);
            UserResponseDto userResponseDto = userFacade.findById(1L);
            model.addAttribute("user",userResponseDto);
        }else{
            model.addAttribute("isLogin", false);
        }
        return "/updateUser";
    }
}
