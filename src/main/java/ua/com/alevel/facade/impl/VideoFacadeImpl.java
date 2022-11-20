package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.VideoFacade;
import ua.com.alevel.persistence.entity.video.Video;
import ua.com.alevel.service.UserService;
import ua.com.alevel.service.VideoService;
import ua.com.alevel.web.dto.request.VideoRequestDto;

@Service
public class VideoFacadeImpl implements VideoFacade {

    private final VideoService videoService;
    private final UserService userService;

    public VideoFacadeImpl(VideoService videoService, UserService userService) {
        this.videoService = videoService;
        this.userService = userService;
    }

    @Override
    public void create(VideoRequestDto videoRequestDto) {
        Video video = new Video();
        video.setDescription(videoRequestDto.getDescriptionVideo());
        video.setTags(videoRequestDto.getTagsVideo());
        video.setTitle(videoRequestDto.getTitle());
//      @TODO add VideoRenderUtil
        video.setChannel(userService.findByEmail(videoRequestDto.getUserEmail()).getChannel());
        videoService.create(video);
    }
}
