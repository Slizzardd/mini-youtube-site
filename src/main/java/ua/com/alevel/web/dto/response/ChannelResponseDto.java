package ua.com.alevel.web.dto.response;

import ua.com.alevel.persistence.entity.channel.Channel;

public class ChannelResponseDto extends ResponseDto{

    private String nameChannel;
    private String descriptionChannel;
    private String avatarChannel;
    private String loginChannel;

    public ChannelResponseDto(Channel channel) {
        setId(channel.getId());
        setVisible(channel.getVisible());
        setCreated(channel.getCreated());
        setUpdated(channel.getUpdated());
        this.nameChannel = channel.getName();
        this.descriptionChannel = channel.getDescription();
        this.avatarChannel = channel.getPathToAvatar();
        this.loginChannel = channel.getLogin();
    }

    public String getNameChannel() {
        return nameChannel;
    }

    public void setNameChannel(String nameChannel) {
        this.nameChannel = nameChannel;
    }

    public String getDescriptionChannel() {
        return descriptionChannel;
    }

    public void setDescriptionChannel(String descriptionChannel) {
        this.descriptionChannel = descriptionChannel;
    }

    public String getAvatarChannel() {
        return avatarChannel;
    }

    public void setAvatarChannel(String avatarChannel) {
        this.avatarChannel = avatarChannel;
    }

    public String getLoginChannel() {
        return loginChannel;
    }

    public void setLoginChannel(String loginChannel) {
        this.loginChannel = loginChannel;
    }
}
