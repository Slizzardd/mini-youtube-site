package ua.com.alevel.util;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.properties.StaticMainProperties;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class VideoSavingUtil {

    public VideoSavingUtil() {
        throw new IllegalStateException("Utility class.");
    }

    public static String writeVideoToFilesAndGetPath(MultipartFile multipartFile, Long userId, Long lastIndexOfDB) {
        try {
            String pathToFolder = generatePathToMainFolder(userId);
            String pathToUserFolder = generatePathToUserFolder(userId, lastIndexOfDB);
            String pathToVideo = generatePathToVideo(pathToUserFolder, lastIndexOfDB, multipartFile);
            new File(pathToFolder).mkdir();
            if (new File(generatePathToUserFolder(userId, lastIndexOfDB)).mkdir()) {
                multipartFile.transferTo(new File(pathToVideo));
                return pathToVideo;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "null";
    }

    private static String generatePathToMainFolder(Long userId) {
        return StaticMainProperties.PATH_PROJECT + "/src/main/resources/static/video/" + "user" + userId;
    }

    private static String generatePathToUserFolder(Long userId, Long lastIndexOfDb) {
        return generatePathToMainFolder(userId) + "/video" + lastIndexOfDb;
    }

    private static String generatePathToVideo(String pathToUserFolder, Long lastVideoIndexOfDb, MultipartFile multipartFile) {
        return pathToUserFolder + "/" + "video" + +lastVideoIndexOfDb + getExtensionFile(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    }

    private static String getExtensionFile(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf('.'));
    }
}