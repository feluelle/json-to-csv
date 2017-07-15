package util.validator;

public class InvalidJsonFormatException extends RuntimeException {
    public InvalidJsonFormatException(String message) {
        super(message);
    }

    public InvalidJsonFormatException(Exception e) {
        super(e);
    }
}
