package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.repository.channel.ChannelRepository;
import ua.com.alevel.service.ChannelService;
import ua.com.alevel.service.HelpService;

import java.util.Objects;

@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final CrudRepositoryHelper<Channel, ChannelRepository> crudRepositoryHelper;
    private final HelpService<Channel> helpService;

    public ChannelServiceImpl(ChannelRepository channelRepository, CrudRepositoryHelper<Channel, ChannelRepository> crudRepositoryHelper, HelpService<Channel> helpService) {
        this.channelRepository = channelRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.helpService = helpService;
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
    public void delete(Channel channel) {
        helpService.deletingFileWhenDeleteEntity(channel);
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
