package ua.com.alevel.facade;

import ua.com.alevel.web.dto.request.VideoRequestDto;
import ua.com.alevel.web.dto.response.VideoResponseDto;

public interface VideoFacade extends BaseFacade<VideoRequestDto, VideoResponseDto> {

    void create(VideoRequestDto videoRequestDto);

    void delete(Long id);
}
