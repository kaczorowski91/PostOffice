package pl.kaczorowski.postoffice.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(ExceptionType type, String value) {
        super(String.format(type.getMessage(), value));
    }
}
