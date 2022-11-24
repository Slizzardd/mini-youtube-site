package ua.com.alevel.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public final class VideoSavingUtil {

    private VideoSavingUtil() {
        throw new IllegalStateException("Utility class.");
    }

    public static String writeVideoToFilesAndGetPath(MultipartFile multipartFile, Long userId, Long lastIndexOfDB) {
        try {
            String pathToFolder = GeneratePath.generatePathToFolder("video", userId);
            String pathToUserFolder = GeneratePath.generatePathToUserFolder(pathToFolder, lastIndexOfDB);
            String pathToVideo = GeneratePath.generatePathToFile("video", pathToUserFolder, lastIndexOfDB, multipartFile);
            new File(pathToFolder).mkdir();
            if (new File(pathToUserFolder).mkdir()) {
                multipartFile.transferTo(new File(pathToVideo));
                return pathToVideo;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "null";
    }

}