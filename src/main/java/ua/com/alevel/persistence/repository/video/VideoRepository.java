package ua.com.alevel.persistence.repository.video;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.video.Video;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.Optional;

@Repository
public interface VideoRepository extends BaseRepository<Video> {

    Optional<Video> findTopByOrderByIdDesc();
}
