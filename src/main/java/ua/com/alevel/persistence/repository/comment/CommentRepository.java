package ua.com.alevel.persistence.repository.comment;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.comment.Comment;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface CommentRepository extends BaseRepository<Comment> {
}
