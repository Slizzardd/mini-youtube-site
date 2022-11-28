package ua.com.alevel.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.CommentFacade;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final CommentFacade commentFacade;

    public CommentController(CommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    @RequestMapping("/createComment")
    public String create(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("isLogin", true);
        }else{
            model.addAttribute("isLogin", false);
        }
        return "/createComment";
    }

    @RequestMapping("/update")
    public String update(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("isLogin", true);
            model.addAttribute("comment", commentFacade.findById(1L));
        }else{
            model.addAttribute("isLogin", false);
        }
        return "/updateComment";
    }
}
