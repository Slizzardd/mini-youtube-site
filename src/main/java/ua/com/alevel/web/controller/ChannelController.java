package ua.com.alevel.web.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.ChannelFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.response.ChannelResponseDto;
import ua.com.alevel.web.rest.ChannelRestController;

@Controller
@RequestMapping("/channel")
public class ChannelController {

    private final UserFacade userFacade;
    private final ChannelFacade channelFacade;

    public ChannelController(UserFacade userFacade, ChannelFacade channelFacade) {
        this.userFacade = userFacade;
        this.channelFacade = channelFacade;
    }

    @RequestMapping("")
    public String create(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("isLogin", true);
        }else{
            model.addAttribute("isLogin", false);
        }
        return "/createChannel";
    }

    @RequestMapping("/updateChannel")
    public String update(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            ChannelResponseDto channelResponseDto = channelFacade.findByUser(authentication.getName());
            model.addAttribute("channel", channelResponseDto);
            model.addAttribute("isLogin", true);
        }else{
            model.addAttribute("isLogin", false);
        }
        return "/updateChannel";
    }
}
