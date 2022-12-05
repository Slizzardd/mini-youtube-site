package ua.com.alevel.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.VideoFacade;

@Controller
@RequestMapping("/video")
public class VideoController {

    private final VideoFacade videoFacade;

    public VideoController(VideoFacade videoFacade) {
        this.videoFacade = videoFacade;
    }

    @RequestMapping("/uploadVideo")
    public String create(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("isLogin", true);
        } else {
            model.addAttribute("isLogin", false);
        }
        return "/createVideo";
    }

    @RequestMapping("/update")
    public String update(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("isLogin", true);
            model.addAttribute("video", videoFacade.findById(1L));
        } else {
            model.addAttribute("isLogin", false);
        }
        return "/updateVideo";
    }
}
