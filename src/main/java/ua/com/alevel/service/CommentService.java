package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.comment.Comment;

public interface CommentService extends BaseService<Comment> {

    void delete(Long id);

}
