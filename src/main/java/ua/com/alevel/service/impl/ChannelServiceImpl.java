package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.repository.channel.ChannelRepository;
import ua.com.alevel.service.ChannelService;

import java.util.Optional;

@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final CrudRepositoryHelper<Channel, ChannelRepository> crudRepositoryHelper;

    public ChannelServiceImpl(ChannelRepository channelRepository, CrudRepositoryHelper<Channel, ChannelRepository> crudRepositoryHelper) {
        this.channelRepository = channelRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public void create(Channel channel) {
        crudRepositoryHelper.create(channelRepository, channel);
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

    @Override
    public Optional<Channel> findByLogin(String login) {
        return channelRepository.findByLogin(login);
    }
}
