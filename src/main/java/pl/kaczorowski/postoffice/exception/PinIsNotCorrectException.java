package pl.kaczorowski.postoffice.exception;

public class PinIsNotCorrectException extends RuntimeException {
    public PinIsNotCorrectException(ExceptionType type, String value) {
        super(String.format(type.getMessage(), value));
    }
}