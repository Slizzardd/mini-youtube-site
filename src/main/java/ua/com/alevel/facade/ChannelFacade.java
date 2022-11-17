package ua.com.alevel.facade;

import ua.com.alevel.web.dto.request.ChannelRequestDto;
import ua.com.alevel.web.dto.response.ChannelResponseDto;

public interface ChannelFacade extends BaseFacade<ChannelRequestDto, ChannelResponseDto> {

    void create(ChannelRequestDto channelRequestDto, String userEmail);

    void delete(Long id);

    ChannelResponseDto findByLogin(String login);
}
