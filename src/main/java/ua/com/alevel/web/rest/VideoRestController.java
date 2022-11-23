package ua.com.alevel.web.rest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.facade.VideoFacade;
import ua.com.alevel.web.dto.request.VideoRequestDto;

@RestController
public class VideoRestController {

    private final VideoFacade videoFacade;

    public VideoRestController(VideoFacade videoFacade) {
        this.videoFacade = videoFacade;
    }

    @RequestMapping("/uploadVideo")
    public String handleFileUpload(@ModelAttribute VideoRequestDto videoRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            videoRequestDto.setUserEmail(authentication.getName());
            videoFacade.create(videoRequestDto);
            return "done";
        } else {
            return "XUITA KAKAYTO";
        }
    }
}
