package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.facade.ChannelFacade;
import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.service.ChannelService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.util.AvatarRenderUtil;
import ua.com.alevel.web.dto.request.ChannelRequestDto;
import ua.com.alevel.web.dto.response.ChannelResponseDto;

import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;

@Service
public class ChannelFacadeImpl implements ChannelFacade {

    private final ChannelService channelService;
    private final UserService userService;

    public ChannelFacadeImpl(ChannelService channelService, UserService userService) {
        this.channelService = channelService;
        this.userService = userService;
    }

    @Override
    public void create(ChannelRequestDto channelRequestDto) throws FileAlreadyExistsException {
        User user = userService.findByEmail(channelRequestDto.getUserEmail());
        assert user != null;
        if (checkUser(user, channelRequestDto.getChannelLogin())) {
            Channel channel = new Channel();

            setMainChannelInformation(channel, channelRequestDto, user);
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
    public void update(ChannelRequestDto channelRequestDto) {
        Channel channel = channelService.findById(
                userService.findByEmail(channelRequestDto.getUserEmail()).getId());
        if (ObjectUtils.isNotEmpty(channel)) {
            setMainChannelInformation(channel, channelRequestDto, channel.getUser());
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

    private void updateAvatar(ChannelRequestDto channelRequestDto, Channel channel){
        if (ObjectUtils.isNotEmpty(channelRequestDto.getAvatar())) {
            if (StringUtils.isEmpty(channel.getPathToAvatar())) {
                channel.setPathToAvatar(AvatarRenderUtil.writeImageToFilesAndGetPath(
                        channelRequestDto.getAvatar(),
                        channelService.getLastIndex(),
                        channel.getUser().getId()));
            } else if (StringUtils.isNotEmpty(channel.getPathToAvatar())) {
                AvatarRenderUtil.writeNewImageToFiles(
                        channelRequestDto.getAvatar(),
                        channel.getPathToAvatar());
            }
        }
    }

    private void setMainChannelInformation(Channel channel, ChannelRequestDto channelRequestDto, User user){
        channel.setLogin(channelRequestDto.getChannelLogin());
        channel.setDescription(channelRequestDto.getDescription());
        channel.setName(channelRequestDto.getChannelName());
        if(channel.getId() != null){
            updateAvatar(channelRequestDto, channel);
        }else{
            channel.setPathToAvatar(AvatarRenderUtil.writeImageToFilesAndGetPath(
                    channelRequestDto.getAvatar(),
                    channelService.getLastIndex(),
                    user.getId()));
            channel.setUser(user);
        }
    }
}
