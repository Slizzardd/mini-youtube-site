package ua.com.alevel.exception;

public class FileDeleteException extends RuntimeException {

    private final String message;

    public FileDeleteException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
