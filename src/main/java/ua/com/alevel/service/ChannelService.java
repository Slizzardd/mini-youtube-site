package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.channel.Channel;

import java.util.Optional;

public interface ChannelService extends BaseService<Channel>{

    void delete(Long id);

    Channel findByLogin(String login);

    Long getLastIndex();
}
