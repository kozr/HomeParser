package exceptions;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
