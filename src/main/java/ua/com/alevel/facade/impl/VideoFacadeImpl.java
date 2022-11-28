package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.VideoFacade;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.persistence.entity.video.Video;
import ua.com.alevel.service.UserService;
import ua.com.alevel.service.VideoService;
import ua.com.alevel.util.AvatarForVideoRenderUtil;
import ua.com.alevel.util.VideoSavingUtil;
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
        User user = userService.findByEmail(videoRequestDto.getUserEmail());
        Video video = new Video();
        video.setDescription(videoRequestDto.getDescriptionVideo());
        video.setTags(videoRequestDto.getTagsVideo());
        video.setTitle(videoRequestDto.getTitle());
//      @TODO add VideoRenderUtil
        video.setPathToVideo(VideoSavingUtil.writeVideoToFilesAndGetPath(
                videoRequestDto.getVideo(), user.getId(), videoService.getLastIndex()));
        video.setPathToAvatar(AvatarForVideoRenderUtil.getImageAndReturnPathToNewAvatar(
                videoRequestDto.getAvatarForVideo(), user.getId(), videoService.getLastIndex()
        ));
        video.setChannel(user.getChannel());
        videoService.create(video);
    }

    @Override
    public void delete(Long id) {
        videoService.delete(videoService.findById(id));
    }
}
