package ua.com.alevel.util;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.properties.StaticMainProperties;

import java.util.Objects;

public final class GeneratePath {

    private GeneratePath() {
        throw new IllegalStateException("Utility class.");
    }

    public static String generatePathToFolder(String nameFolder, Long userId) {
        return StaticMainProperties.PATH_PROJECT +
                "/src/main/resources/static/" + nameFolder +
                "/user" + userId;
    }

    public static String generatePathToAvatar(String pathToFolder, Long newAvatarId, String originalFileName) {
        return pathToFolder + "/avatar" + newAvatarId + getExtensionFile(originalFileName);
    }

    public static String getImageFormat(String pathToAvatar) {
        return pathToAvatar.substring(pathToAvatar.lastIndexOf(".") + 1);     //jpg
    }

    public static String generatePathToUserFolder(String pathToFolder, Long lastIndexOfDb){
       return pathToFolder + "/video" + lastIndexOfDb;
    }

    public static String generatePathToFile(String nameFile, String pathToUserFolder, Long lastIndexOfDb, MultipartFile multipartFile){
        return pathToUserFolder + "/" + nameFile + lastIndexOfDb + getExtensionFile(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    }

    public static String getExtensionFile(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf('.'));     //.jpg
    }
}
