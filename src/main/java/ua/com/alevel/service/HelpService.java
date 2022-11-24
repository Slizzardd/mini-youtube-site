package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.persistence.entity.video.Video;

import java.util.Set;

public interface HelpService{

    void deletingAvatarWhenDeletingEntity(Set<Video> videoSet, Long userId, String pathToChannelAvatar);
}
