package ua.com.alevel.util;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.exception.ImageSizeNotAllowed;
import ua.com.alevel.properties.StaticMainProperties;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class AvatarForChannelUtil {

    private static final int imageLength = 300;


    private AvatarForChannelUtil() {
        throw new IllegalStateException("Utility class.");
    }

    public static String writeImageToFilesAndGetPath(MultipartFile multipartFile, Long userId, Long lastIndexOfDb) throws NullPointerException{
        try {
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            if (checkSizeImage(image)) {
                image = image.getSubimage(
                        getCoordinateForNewImage(image.getWidth()),
                        getCoordinateForNewImage(image.getHeight()),
                        imageLength, imageLength);
                String pathToFolder = GeneratePath.generatePathToFolder("avatarChannel", userId);
                String pathToAvatar = GeneratePath.generatePathToAvatar(pathToFolder, lastIndexOfDb, multipartFile.getOriginalFilename());

                if (checkOldAvatar(pathToFolder, pathToAvatar)) {
                    return writeFileToTheFolder(image, pathToAvatar);
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

    private static boolean checkSizeImage(BufferedImage image) throws IOException {
        return image.getHeight() >= 300 || image.getWidth() >= 300 || image.getWidth() <= 3100 || image.getHeight() <= 3100;
    }

    private static int getCoordinateForNewImage(int coordinate) {
        return (coordinate / 2) - (imageLength / 2);
    }

    private static String writeFileToTheFolder(BufferedImage image, String pathToAvatar) throws IOException {
        String formatName = GeneratePath.getImageFormat(pathToAvatar);

        ImageIO.write(image, formatName, new File(pathToAvatar));
        return pathToAvatar;

    }

    private static boolean checkOldAvatar(String pathToFolder, String pathToAvatar) {
        if (new File(pathToFolder).mkdir()) {
            if (new File(pathToAvatar).mkdir()) {
                return true;
            } else {
                if (new File(pathToAvatar).exists()) {
                    if (new File(pathToAvatar).delete()) {
                        return new File(pathToAvatar).mkdir();
                    }
                }
            }
        } else {
            if (new File(pathToFolder).exists()) {
                if (new File(pathToAvatar).mkdir()) {
                    return true;
                } else {
                    if (new File(pathToAvatar).exists()) {
                        if (new File(pathToAvatar).delete()) {
                            return new File(pathToAvatar).mkdir();
                        }
                    }
                }
            }
        }
        return false;
    }
}
