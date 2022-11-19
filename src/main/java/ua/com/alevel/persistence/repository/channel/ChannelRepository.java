package ua.com.alevel.persistence.repository.channel;

import ua.com.alevel.persistence.entity.channel.Channel;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.Optional;

public interface ChannelRepository extends BaseRepository<Channel> {

    Optional<Channel> findByLogin(String login);

    Optional<Channel> findTopByOrderByIdDesc();
}
