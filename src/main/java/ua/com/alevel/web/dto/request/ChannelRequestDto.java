package ua.com.alevel.web.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class ChannelRequestDto extends RequestDto{

    private String channelName;
    private MultipartFile avatar;
    private String channelLogin;
    private String description;

    public ChannelRequestDto() {
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getChannelLogin() {
        return channelLogin;
    }

    public void setChannelLogin(String channelLogin) {
        this.channelLogin = channelLogin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
