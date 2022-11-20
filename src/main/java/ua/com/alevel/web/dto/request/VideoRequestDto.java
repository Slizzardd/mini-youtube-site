package ua.com.alevel.web.dto.request;

public class VideoRequestDto extends RequestDto{

    private String descriptionVideo;
    private String tagsVideo;
    private String title;
    private String userEmail;

    public VideoRequestDto() {
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
