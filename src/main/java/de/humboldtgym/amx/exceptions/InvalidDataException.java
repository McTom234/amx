package de.humboldtgym.amx.exceptions;

public class InvalidDataException extends DataException {
    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
