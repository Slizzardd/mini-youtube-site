package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.comment.Comment;
import ua.com.alevel.service.CommentService;

import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    @Override
    public void create(Comment entity) {

    }

    @Override
    public void update(Comment entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DataTableResponse<Comment> findAll(DataTableRequest request) {
        return null;
    }
}
