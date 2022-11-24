package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.channel.User;
import ua.com.alevel.persistence.entity.video.Video;
import ua.com.alevel.service.HelpService;
import ua.com.alevel.util.GeneratePath;

import java.io.File;
import java.util.Set;

@Service
public class HelpServiceImpl implements HelpService {

//    @TODO Figure out how to do it differently
    @Override
    public void deletingAvatarWhenDeletingEntity(Set<Video> videoSet, Long userId, String pathToChannelAvatar) {
        String pathToFolder = GeneratePath.generatePathToFolder("avatarChannel", userId);
        if (!videoSet.isEmpty()) {
            for (Video video : videoSet) {
                if(new File(video.getPathToAvatar()).delete() && new File(video.getPathToVideo()).delete()){
                    String pathToVideoFolder = GeneratePath.generatePathToFolder("video", userId);
                    if(new File(GeneratePath.generatePathToUserFolder(
                            pathToVideoFolder,
                            video.getId())).delete() && new File(pathToVideoFolder).delete()){

                    }
                }

            }
        }
        if(new File(pathToChannelAvatar).delete()){}
        if(new File(pathToFolder).delete()){}
    }
}
