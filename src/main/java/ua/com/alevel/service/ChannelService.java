package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.channel.Channel;

public interface ChannelService extends BaseService<Channel> {

    void delete(Channel channel);

    Channel findByLogin(String login);

    Long getLastIndex();
}
