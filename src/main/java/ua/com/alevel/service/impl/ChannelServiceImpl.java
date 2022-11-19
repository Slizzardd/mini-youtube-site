package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.repository.channel.ChannelRepository;
import ua.com.alevel.service.ChannelService;

import java.util.Objects;
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
    public void update(Channel channel) {
        crudRepositoryHelper.update(channelRepository, channel);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Channel findById(Long id) {
        return channelRepository.findById(id).orElse(null);
    }

    @Override
    public DataTableResponse<Channel> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public Channel findByLogin(String login) {
        return channelRepository.findByLogin(login).orElse(null);
    }

    @Override
    public Long getLastIndex() {
        try{
            return Objects.requireNonNull(channelRepository.findTopByOrderByIdDesc().orElse(null)).getId() + 1L;
        }catch (NullPointerException e){
            return 1L;
        }
    }
}
