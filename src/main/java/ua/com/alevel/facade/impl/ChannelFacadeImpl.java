package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.ChannelFacade;
import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.service.ChannelService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.web.dto.request.ChannelRequestDto;
import ua.com.alevel.web.dto.response.ChannelResponseDto;

@Service
public class ChannelFacadeImpl implements ChannelFacade {

    private final ChannelService channelService;
    private final UserService userService;

    public ChannelFacadeImpl(ChannelService channelService, UserService userService) {
        this.channelService = channelService;
        this.userService = userService;
    }

    @Override
    public void create(ChannelRequestDto channelRequestDto, String userEmail) {
        User user = userService.findByEmail(userEmail).orElse(null);
        assert user != null;
        if (checkUser(user, channelRequestDto.getChannelLogin())) {
            Channel channel = new Channel();
            channel.setName(channelRequestDto.getChannelName());
            channel.setDescription(channelRequestDto.getDescription());
            channel.setLogin(channelRequestDto.getChannelLogin());

//          @TODO Create an ImageRenderUtil and make the avatar load
            channel.setUser(user);
            channelService.create(channel);

//          @TODO Figure out how to do it differently
            user.setChannel(channel);
            userService.update(user);
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ChannelResponseDto findByLogin(String login) {
        Channel channel = channelService.findByLogin(login).orElse(null);
        if (ObjectUtils.isNotEmpty(channel)) {
            return new ChannelResponseDto(channel);
        } else {
            return null;
        }
    }

    private boolean checkUser(User user, String channelLogin) {
        Channel channel = user.getChannel();
        if (ObjectUtils.isNotEmpty(user) && ObjectUtils.isEmpty(channel)
                && ObjectUtils.isEmpty(findByLogin(channelLogin))) {
            return true;
        } else if (ObjectUtils.isNotEmpty(channel)) {
            throw new EntityExistException("You can't create more than one channel :(");
        } else if (ObjectUtils.isEmpty(user)) {
            throw new EntityNotFoundException("The user with this email does not exist");
        } else if (ObjectUtils.isNotEmpty(findByLogin(channelLogin))) {
            throw new EntityExistException("The channel with this login does not exist");
        } else {
            return false;
        }
    }
}
