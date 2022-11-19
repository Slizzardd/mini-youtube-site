package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.comment.Comment;
import ua.com.alevel.persistence.repository.comment.CommentRepository;
import ua.com.alevel.service.CommentService;

import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CrudRepositoryHelper<Comment, CommentRepository> crudRepositoryHelper;

    public CommentServiceImpl(CommentRepository commentRepository, CrudRepositoryHelper<Comment, CommentRepository> crudRepositoryHelper) {
        this.commentRepository = commentRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

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
    public Comment findById(Long id) {
        return crudRepositoryHelper.findById(commentRepository, id).orElse(null);
    }

    @Override
    public DataTableResponse<Comment> findAll(DataTableRequest request) {
        return null;
    }
}
