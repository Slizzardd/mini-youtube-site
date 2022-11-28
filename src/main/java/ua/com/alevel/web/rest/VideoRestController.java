package ua.com.alevel.web.rest;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.facade.VideoFacade;
import ua.com.alevel.web.dto.request.ChannelRequestDto;
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

    @RequestMapping("/deleteVideo")
    public String delete(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            videoFacade.delete(1L);
            return "done";
        } else {
            return "XUITA KAKAYTO";
        }
    }

    @PostMapping(value = "/updateVideo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String update(@ModelAttribute VideoRequestDto videoRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            videoRequestDto.setUserEmail(authentication.getName());
            videoFacade.update(1L, videoRequestDto);
            return "done";
        } else {
            return "XUITA KAKAYTO";
        }
    }
}
