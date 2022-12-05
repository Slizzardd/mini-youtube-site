package ua.com.alevel.exception;

public class ImageSizeNotAllowed extends RuntimeException {

    private final String message;

    public ImageSizeNotAllowed(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
