package ua.com.alevel.service.impl;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.service.ChannelService;

import java.util.Optional;

public class ChannelServiceImpl implements ChannelService {

    @Override
    public void create(Channel entity) {

    }

    @Override
    public void update(Channel entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Channel> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public DataTableResponse<Channel> findAll(DataTableRequest request) {
        return null;
    }
}
