package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.facade.CommentFacade;
import ua.com.alevel.persistence.entity.comment.Comment;
import ua.com.alevel.service.CommentService;
import ua.com.alevel.service.UserService;
import ua.com.alevel.service.VideoService;
import ua.com.alevel.web.dto.request.CommentRequestDto;

@Service
public class CommentFacadeImpl implements CommentFacade {

    private final CommentService commentService;
    private final UserService userService;
    private final VideoService videoService;

    public CommentFacadeImpl(CommentService commentService, UserService userService, VideoService videoService) {
        this.commentService = commentService;
        this.userService = userService;
        this.videoService = videoService;
    }


    @Override
    public void create(CommentRequestDto commentRequestDto) {
        Comment comment = new Comment();
        comment.setChannel(userService.findByEmail(commentRequestDto.getUserEmail()).getChannel());
        comment.setText(commentRequestDto.getText());
        comment.setVideo(videoService.findById(commentRequestDto.getIdVideo()));
        commentService.create(comment);
    }

    @Override
    public void delete(Long id) {
        commentService.delete(id);
    }
}
