package ua.com.alevel.web.dto.response;

import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.entity.comment.Comment;

import java.util.Set;

public class ChannelResponseDto extends ResponseDto {

    private String nameChannel;
    private String descriptionChannel;
    private String avatarChannel;
    private String loginChannel;
    private Long countSubscribers;
    private Long countLikes;
    private Set<Comment> comment;


    public ChannelResponseDto(Channel channel) {
        setId(channel.getId());
        setVisible(channel.getVisible());
        setCreated(channel.getCreated());
        setUpdated(channel.getUpdated());
        this.nameChannel = channel.getName();
        this.descriptionChannel = channel.getDescription();
        this.avatarChannel = channel.getPathToAvatar();
        this.loginChannel = channel.getLogin();
        this.countSubscribers = (long) channel.getSubscribers().size();
        this.countLikes = (long) channel.getLikes().size();
        this.comment = channel.getComments();
    }


    public Long getCountSubscribers() {
        return countSubscribers;
    }

    public void setCountSubscribers(Long countSubscribers) {
        this.countSubscribers = countSubscribers;
    }

    public Long getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(Long countLikes) {
        this.countLikes = countLikes;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
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
