package com.gmail.maxdiland.consolescanner.exception;

/**
 * author Maksim Diland
 */
public class NotSuitableInputDataException extends RuntimeException {
    public NotSuitableInputDataException() {}

    public NotSuitableInputDataException(String message) {
        super(message);
    }

    public NotSuitableInputDataException(Throwable cause) {
        super(cause);
    }
}
