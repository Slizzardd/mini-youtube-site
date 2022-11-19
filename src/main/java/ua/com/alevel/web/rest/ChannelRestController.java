package ua.com.alevel.web.rest;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.ChannelFacade;
import ua.com.alevel.web.dto.request.ChannelRequestDto;

import java.nio.file.FileAlreadyExistsException;

@RestController
public class ChannelRestController {

    private final ChannelFacade channelFacade;

    public ChannelRestController(ChannelFacade channelFacade) {
        this.channelFacade = channelFacade;
    }

    @PostMapping(value = "/createChannel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String create(@ModelAttribute ChannelRequestDto channelRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            try{
                channelFacade.create(channelRequestDto, authentication.getName());
                return "done";
            }catch (EntityNotFoundException | EntityExistException e){
                return e.toString();
            } catch (FileAlreadyExistsException e) {
                return "XUITrA KArKAYrTO";
            }
        }else{
            return "XUITA KAKAYTO";
        }
    }

    @PostMapping(value = "/updateChannel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String update(@ModelAttribute ChannelRequestDto channelRequestDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            channelFacade.update(channelRequestDto, authentication.getName());
            return "done";
        }else{
            return "XUITA KAKAYTO";
        }
    }
}
