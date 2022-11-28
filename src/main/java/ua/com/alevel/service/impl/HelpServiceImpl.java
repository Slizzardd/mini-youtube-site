package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.service.HelpService;
import ua.com.alevel.util.GeneratePath;

import java.io.File;

@Service
public class HelpServiceImpl<E extends BaseEntity> implements HelpService<E> {

    @Override
    public void deletingFileWhenDeleteEntity(E entity) {
        switch (entity.getClass().getSimpleName()) {
            case "User": {
                recursiveDelete(new File(GeneratePath.generatePathToFolder("video", entity.getId())));
                recursiveDelete(new File(GeneratePath.generatePathToFolder("avatarChannel", entity.getId())));
            }
            case "Channel": {
                recursiveDelete(new File(GeneratePath.generatePathToFolder("avatarChannel", entity.getId())));
                recursiveDelete(new File(GeneratePath.generatePathToFolder("video", entity.getId())));
            }
            case "Video": {
                recursiveDelete(new File(GeneratePath.generatePathToUserFolder(
                        GeneratePath.generatePathToFolder("video", entity.getId()), entity.getId()
                )));
            }
        }
    }

    private static void recursiveDelete(File file) {
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                recursiveDelete(f);
            }
        }
        file.delete();
    }

}
