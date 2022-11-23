package ua.com.alevel.util;

import org.springframework.web.multipart.MultipartFile;
import ua.com.alevel.properties.StaticMainProperties;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class AvatarForVideoRenderUtil {

    private static double newWight;
    private static double newHeight;

    public AvatarForVideoRenderUtil() {
    }


    public static String getImageAndReturnPathToNewAvatar(MultipartFile avatar, Long userId, Long lastIndexVideoOfDb) {
        String fullPathToTheAvatar = generatePathToAvatar(
                generatePathToUserFolder(userId, lastIndexVideoOfDb),
                lastIndexVideoOfDb,
                avatar);
        try {
            BufferedImage image = getProcessedImage(avatar);
            ImageIO.write(
                    image,
                    "jpg",
                    new File(fullPathToTheAvatar));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullPathToTheAvatar;
    }

    public static void generateNewSizeForImage(double wight, double height) {
        double ratios = wight / height;
        if (wight > height) {
            newWight = 1920;
            newHeight = 1080;
        } else if (wight < height) {
            newHeight = 1080;
            newWight = 1080 * ratios;
        } else {
            newHeight = 1080;
            newWight = 1080;
        }
    }

    public static BufferedImage getBlackNewImage() {
        BufferedImage image = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);

        Graphics2D draw = image.createGraphics();

        draw.setColor(Color.black);
        draw.fillRect(0, 0, (int) newWight, (int) newHeight);
        draw.dispose();

        return image;
    }

    public static BufferedImage getProcessedImage(MultipartFile avatar) throws IOException {
        BufferedImage newImage = getBlackNewImage();

        BufferedImage reqImage = ImageIO.read(avatar.getInputStream());

        generateNewSizeForImage(reqImage.getWidth(), reqImage.getHeight());
        reqImage = generateSmallSizeImage(reqImage);

        Graphics2D draw = newImage.createGraphics();

        draw.drawImage(reqImage, (1920 - reqImage.getWidth()) / 2, 0, null);
        draw.dispose();
        return newImage;
    }

    private static String generatePathToMainFolder(Long userId) {
        return StaticMainProperties.PATH_PROJECT + "/src/main/resources/static/video/" + "user" + userId;
    }

    private static String generatePathToUserFolder(Long userId, Long lastIndexOfDb) {
        return generatePathToMainFolder(userId) + "/video" + lastIndexOfDb;
    }

    private static String generatePathToAvatar(String pathToUserFolder, Long lastVideoIndexOfDb, MultipartFile multipartFile) {
        return pathToUserFolder + "/" + "avatar" + +lastVideoIndexOfDb + getExtensionFile(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    }

    private static String getExtensionFile(String originalFileName) {
        return originalFileName.substring(originalFileName.lastIndexOf('.'));
    }

    private static BufferedImage generateSmallSizeImage(BufferedImage bufferedImage){
        BufferedImage bufferedImageOutput = new BufferedImage((int) newWight,
                (int) newHeight, bufferedImage.getType());
        Graphics2D g2d = bufferedImageOutput.createGraphics();

        g2d.drawImage(bufferedImage, 0, 0, (int) newWight, (int) newHeight, null);
        g2d.dispose();

        return bufferedImageOutput;
    }
}
