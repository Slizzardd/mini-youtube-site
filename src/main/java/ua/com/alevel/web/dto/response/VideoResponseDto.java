package ua.com.alevel.web.dto.response;

import ua.com.alevel.persistence.entity.video.Video;

public class VideoResponseDto extends ResponseDto{

    private String pathToVideo;
    private String pathToAvatar;
    private String description;
    private String title;
    private String tags;

    public VideoResponseDto(Video video) {
        setId(video.getId());
        setCreated(video.getCreated());
        setUpdated(video.getUpdated());
        setVisible(video.getVisible());
        this.pathToVideo = video.getPathToVideo();
        this.pathToAvatar = video.getPathToAvatar();
        this.description = video.getDescription();
        this.title = video.getTitle();
        this.tags = video.getTags();
    }

    public String getPathToVideo() {
        return pathToVideo;
    }

    public void setPathToVideo(String pathToVideo) {
        this.pathToVideo = pathToVideo;
    }

    public String getPathToAvatar() {
        return pathToAvatar;
    }

    public void setPathToAvatar(String pathToAvatar) {
        this.pathToAvatar = pathToAvatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
