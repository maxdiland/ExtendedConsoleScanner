package com.gmail.maxdiland.consolescanner.exception;

/**
 * author Maksim Diland
 */
public class FileNotFoundException extends NotSuitableInputDataException {
    public FileNotFoundException() {}

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(Throwable cause) {
        super(cause);
    }
}
