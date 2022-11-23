package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.video.Video;
import ua.com.alevel.persistence.repository.video.VideoRepository;
import ua.com.alevel.service.VideoService;

import java.util.Objects;
import java.util.Optional;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final CrudRepositoryHelper <Video, VideoRepository> crudRepositoryHelper;

    public VideoServiceImpl(VideoRepository videoRepository, CrudRepositoryHelper<Video, VideoRepository> crudRepositoryHelper) {
        this.videoRepository = videoRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public void create(Video video) {
        crudRepositoryHelper.create(videoRepository, video);
    }

    @Override
    public void update(Video entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Video findById(Long id) {
        return crudRepositoryHelper.findById(videoRepository, id).orElse(null);
    }

    @Override
    public DataTableResponse<Video> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public Long getLastIndex() {
        try{
            return Objects.requireNonNull(videoRepository.findTopByOrderByIdDesc().orElse(null)).getId() + 1L;
        }catch (NullPointerException e){
            return 1L;
        }
    }
}
