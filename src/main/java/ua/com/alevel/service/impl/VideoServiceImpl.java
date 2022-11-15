package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.video.Video;
import ua.com.alevel.service.VideoService;

import java.util.Optional;

public class VideoServiceImpl implements VideoService {

    @Override
    public void create(Video entity) {

    }

    @Override
    public void update(Video entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Video> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DataTableResponse<Video> findAll(DataTableRequest request) {
        return null;
    }
}
