package pl.kaczorowski.postoffice.exception;

public enum ExceptionType {
    CLIENT_FOUND("ERROR: CLIENT '%s' already exist"),
    CLIENT_PIN("ERROR: CLIENT '%s' can't be add with this status, because pin is wrong"),
    CLIENT_NUMBER_NOT_SAVE("Unexpected problem with Client number, Please try add Client again."),
    CLIENT_NUMBER_NOT_FIND("ERROR: CLIENT with number '%s' not exist"),
    CLIENT_NAME_NOT_FIND("ERROR: CLIENT with name '%s' not exist");

    private final String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
