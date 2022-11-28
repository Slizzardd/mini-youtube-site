package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.ChannelFacade;
import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.properties.StaticMainProperties;
import ua.com.alevel.service.ChannelService;
import ua.com.alevel.service.HelpService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.AvatarForChannelUtil;
import ua.com.alevel.web.dto.request.ChannelRequestDto;
import ua.com.alevel.web.dto.response.ChannelResponseDto;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;

@Service
public class ChannelFacadeImpl implements ChannelFacade {

    private final ChannelService channelService;
    private final UserService userService;
    private final HelpService<Channel> helpService;

    public ChannelFacadeImpl(ChannelService channelService, UserService userService, HelpService<Channel> helpService) {
        this.channelService = channelService;
        this.userService = userService;
        this.helpService = helpService;
    }

    @Override
    public void create(ChannelRequestDto channelRequestDto) throws FileAlreadyExistsException {
        User user = userService.findByEmail(channelRequestDto.getUserEmail());
        assert user != null;
        if (checkUser(user, channelRequestDto.getChannelLogin())) {
            Channel channel = new Channel();

            setMainChannelInformation(channel, channelRequestDto, user);
            if (channelRequestDto.getAvatar().isEmpty()) {
                channel.setPathToAvatar(StaticMainProperties.PATH_TO_BASE_AVATAR);
            } else {
                channel.setPathToAvatar(AvatarForChannelUtil.writeImageToFilesAndGetPath(
                        channelRequestDto.getAvatar(),
                        user.getId(),
                        channelService.getLastIndex()));
            }
            channelService.create(channel);

            user.setChannel(channel);
            userService.update(user);
        }
    }

    @Override
    public void delete(String userEmail) {
        User user = userService.findByEmail(userEmail);
        Channel channel = user.getChannel();
        user.setChannel(null);
        userService.update(user);
        channelService.delete(channel);
    }

    @Override
    public void update(ChannelRequestDto channelRequestDto) throws FileAlreadyExistsException {
        User user = userService.findByEmail(channelRequestDto.getUserEmail());
        Channel channel = channelService.findById(user.getChannel().getId());
        if (ObjectUtils.isNotEmpty(channel)) {
            setMainChannelInformation(channel, channelRequestDto, channel.getUser());
            if (Objects.equals(channel.getPathToAvatar(), StaticMainProperties.PATH_TO_BASE_AVATAR)
                    && !channelRequestDto.getAvatar().isEmpty()) {
                channel.setPathToAvatar(
                        AvatarForChannelUtil.writeImageToFilesAndGetPath(
                                channelRequestDto.getAvatar(),
                                user.getId(),
                                channel.getId()));
            }else if(!Objects.equals(channel.getPathToAvatar(), StaticMainProperties.PATH_TO_BASE_AVATAR)
                    && !channelRequestDto.getAvatar().isEmpty()){
                helpService.recursiveDelete(new File(channel.getPathToAvatar()));
                channel.setPathToAvatar(
                        AvatarForChannelUtil.writeImageToFilesAndGetPath(
                                channelRequestDto.getAvatar(),
                                user.getId(),
                                channel.getId()));
            }
            channelService.update(channel);
        }
    }

    @Override
    public ChannelResponseDto findByUser(String userEmail) {
        return new ChannelResponseDto(Objects.requireNonNull(userService.findByEmail(userEmail)).getChannel());
    }

    @Override
    public ChannelResponseDto findById(Long id) {
        return new ChannelResponseDto(Objects.requireNonNull(channelService.findById(id)));
    }


    @Override
    public ChannelResponseDto findByLogin(String login) {
        Channel channel = channelService.findByLogin(login);
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

    private void setMainChannelInformation(Channel channel, ChannelRequestDto channelRequestDto, User user) {
        channel.setLogin(channelRequestDto.getChannelLogin());
        channel.setDescription(channelRequestDto.getDescription());
        channel.setName(channelRequestDto.getChannelName());
        channel.setUser(user);
    }
}
