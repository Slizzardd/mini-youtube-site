package ua.com.alevel.web.dto.response;

import ua.com.alevel.persistence.entity.channel.Channel;

public class ChannelResponseDto extends ResponseDto{

    public ChannelResponseDto(Channel channel) {
        setId(channel.getId());
        setVisible(channel.getVisible());
        setCreated(channel.getCreated());
        setUpdated(channel.getUpdated());
    }
}
