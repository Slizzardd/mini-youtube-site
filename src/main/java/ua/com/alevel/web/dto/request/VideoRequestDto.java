package ua.com.alevel.web.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class VideoRequestDto extends RequestDto {

    private String descriptionVideo;
    private String tagsVideo;
    private String title;
    private String userEmail;
    private MultipartFile video;
    private MultipartFile avatarForVideo;

    public VideoRequestDto() {
    }

    public MultipartFile getVideo() {
        return video;
    }

    public void setVideo(MultipartFile video) {
        this.video = video;
    }

    public MultipartFile getAvatarForVideo() {
        return avatarForVideo;
    }

    public void setAvatarForVideo(MultipartFile avatarForVideo) {
        this.avatarForVideo = avatarForVideo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDescriptionVideo() {
        return descriptionVideo;
    }

    public void setDescriptionVideo(String descriptionVideo) {
        this.descriptionVideo = descriptionVideo;
    }

    public String getTagsVideo() {
        return tagsVideo;
    }

    public void setTagsVideo(String tagsVideo) {
        this.tagsVideo = tagsVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
