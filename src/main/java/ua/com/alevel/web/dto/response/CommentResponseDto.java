package ua.com.alevel.web.dto.response;

import ua.com.alevel.persistence.entity.comment.Comment;

public class CommentResponseDto extends ResponseDto{

    private String text;

    public CommentResponseDto(Comment comment) {
        setId(comment.getId());
        setCreated(comment.getCreated());
        setUpdated(comment.getUpdated());
        setVisible(comment.getVisible());
        this.text = comment.getText();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
