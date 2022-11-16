package ua.com.alevel.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.UserFacade;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserFacade userFacade;

    public MainController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping
    public String mainPage(Model model, WebRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("isLogin", true);
        }else{
            model.addAttribute("isLogin", false);
        }
        return "/index";
    }
}
