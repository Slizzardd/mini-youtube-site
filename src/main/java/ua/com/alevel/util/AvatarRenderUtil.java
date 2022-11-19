package ua.com.alevel.util;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.exception.FileDeleteException;
import ua.com.alevel.exception.ImageSizeNotAllowed;
import ua.com.alevel.properties.StaticMainProperties;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public final class AvatarRenderUtil {

    private static final int imageLength = 300;

    public AvatarRenderUtil() {
        throw new IllegalStateException("Utility class.");
    }

    public static String writeImageToFilesAndGetPath(MultipartFile multipartFile, Long userId, Long newAvatarId) {
        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (checkSizeImage(image)) {
                image = image.getSubimage(
                        getCoordinateForNewImage(image.getWidth()),
                        getCoordinateForNewImage(image.getHeight()),
                        imageLength, imageLength);
                String pathToFolder = generatePathToFolder(userId);
                if (new File(pathToFolder).mkdir()) {
                    String pathToAvatar = generatePathToAvatar(pathToFolder, newAvatarId, multipartFile);
                    String formatName = getImageFormat(pathToAvatar);

                    ImageIO.write(image, formatName, new File(pathToAvatar));

                    return pathToAvatar;
                } else {
                    throw new FileNotFoundException("error by create a new folder");
                }
            } else {
                throw new ImageSizeNotAllowed("image size not allowed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return StaticMainProperties.PATH_TO_BASE_AVATAR;
    }

    public static void writeNewImageToFiles(MultipartFile multipartFile, String pathToAvatar){
        try{
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (checkSizeImage(image)) {
                if(new File(pathToAvatar).delete()){
                    image = image.getSubimage(
                            getCoordinateForNewImage(image.getWidth()),
                            getCoordinateForNewImage(image.getHeight()),
                            imageLength, imageLength);
                    ImageIO.write(image, getImageFormat(pathToAvatar), new File(pathToAvatar));
                }else{
                    throw new FileDeleteException("Error by delete file" + pathToAvatar);
                }
            }else{
                throw new ImageSizeNotAllowed("image size not allowed");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static boolean checkSizeImage(BufferedImage image) throws IOException {
        return image.getHeight() >= 300 || image.getWidth() >= 300 || image.getWidth() <= 3100 || image.getHeight() <= 3100;
    }

    private static int getCoordinateForNewImage(int coordinate) {
        return (coordinate / 2) - (imageLength / 2);
    }


    private static String generatePathToFolder(Long userId) {
        return StaticMainProperties.PATH_PROJECT + "/src/main/resources/static/imageChannel/" + "user" + userId;
    }
    private static String generatePathToAvatar(String pathToFolder, Long newAvatarId, MultipartFile multipartFile) {
        return pathToFolder + "/" + "avatar" + newAvatarId + getExtensionFile(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    }

    private static String getImageFormat(String pathToAvatar){
        return pathToAvatar.substring(pathToAvatar.lastIndexOf(".") + 1);
    }

    private static String getExtensionFile(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf('.'));
    }
}
