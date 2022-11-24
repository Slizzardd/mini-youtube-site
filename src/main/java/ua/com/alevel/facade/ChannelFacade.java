package ua.com.alevel.facade;

import ua.com.alevel.web.dto.request.ChannelRequestDto;
import ua.com.alevel.web.dto.response.ChannelResponseDto;

import java.nio.file.FileAlreadyExistsException;

public interface ChannelFacade extends BaseFacade<ChannelRequestDto, ChannelResponseDto> {

    void create(ChannelRequestDto channelRequestDto) throws FileAlreadyExistsException;

    void delete(Long channelId, String userEmail);

    void update(ChannelRequestDto channelRequestDto) throws FileAlreadyExistsException;

    ChannelResponseDto findByUser(String userEmail);

    ChannelResponseDto findById(Long id);

    ChannelResponseDto findByLogin(String login);
}
