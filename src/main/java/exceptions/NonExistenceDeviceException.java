package exceptions;

public class NonExistenceDeviceException extends IllegalArgumentException {

    public NonExistenceDeviceException() {
    }

    public NonExistenceDeviceException(String message) {
        super(message);
    }
}
