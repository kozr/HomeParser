package exceptions;

public class StatusUnclearException extends IllegalArgumentException {
    public StatusUnclearException() {
    }

    public StatusUnclearException(String message) {
        super(message);
    }
}
