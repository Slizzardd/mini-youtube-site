package ua.com.alevel.web.rest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.facade.CommentFacade;
import ua.com.alevel.facade.UserFacade;
import ua.com.alevel.web.dto.request.CommentRequestDto;
import ua.com.alevel.web.dto.request.VideoRequestDto;

@RestController
@RequestMapping("/")
public class CommentRestController {

    private final CommentFacade commentFacade;
    private final UserFacade userFacade;
    public CommentRestController(CommentFacade commentFacade, UserFacade userFacade) {
        this.commentFacade = commentFacade;
        this.userFacade = userFacade;

    }

    @RequestMapping("/createComment")
    public String handleFileUpload(@RequestBody CommentRequestDto commentRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            commentRequestDto.setUserEmail(authentication.getName());
            commentRequestDto.setIdVideo(1L);
            commentFacade.create(commentRequestDto);
            return "done";
        } else {
            return "XUITA KAKAYTO";
        }
    }

    @RequestMapping("/deleteComment")
    public String delete(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            commentFacade.delete(1L);
            return "done";
        } else {
            return "XUITA KAKAYTO";
        }
    }
}
