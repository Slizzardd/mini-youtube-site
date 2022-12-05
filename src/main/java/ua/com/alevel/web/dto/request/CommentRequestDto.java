package ua.com.alevel.web.dto.request;

public class CommentRequestDto extends RequestDto {

    private String text;
    private Long idVideo;
    private String userEmail;

    public CommentRequestDto() {
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(Long idVideo) {
        this.idVideo = idVideo;
    }
}
