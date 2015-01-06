package com.gmail.maxdiland.consolescanner;

import com.gmail.maxdiland.consolescanner.exception.DialogInterruptionRequestException;
import com.gmail.maxdiland.consolescanner.exception.FileNotFoundException;
import com.gmail.maxdiland.consolescanner.exception.NotSuitableInputDataException;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 * author Maksim Diland
 */
public class PrimordialSystemInExtendedScanner implements SystemInExtendedScanner {

    private final Scanner scanner;
    private String interruptionPhrase = "exit"; //Default interruption phrase

    public PrimordialSystemInExtendedScanner() {
        this.scanner = new Scanner(System.in);
    }

    public PrimordialSystemInExtendedScanner(String interruptionString) {
        this();
        interruptionPhrase = interruptionString;
    }

    @Override
    public String getString() throws DialogInterruptionRequestException {
        String readLine = scanner.nextLine();
        checkReadLineForExitRequest(readLine);
        return readLine;
    }

    @Override
    public int getInt() throws NotSuitableInputDataException, DialogInterruptionRequestException {
        String readLine = getString();
        try {
            return Integer.parseInt(readLine);
        } catch (NumberFormatException nfe) {
            throw new NotSuitableInputDataException();
        }
    }

    @Override
    public int getInt(int minValue, int maxValue) throws NotSuitableInputDataException, DialogInterruptionRequestException {
        if (minValue > maxValue) {
            throw new IllegalArgumentException("Allowed minimum value shouldn't be greater than allowed maximum value");
        }

        int gottenInt = getInt();
        if (gottenInt >= minValue && gottenInt <= maxValue) {
            return gottenInt;
        } else {
            throw new NotSuitableInputDataException();
        }
    }

    @Override
    public File getExistingFile() throws FileNotFoundException, DialogInterruptionRequestException {
        File file = getFile();
        if (file.exists()) {
            return file;
        } else {
            throw new FileNotFoundException("File does not exist");
        }
    }

    @Override
    public File getFile() throws NotSuitableInputDataException, DialogInterruptionRequestException {
        String readLine = getString();
        return new File(readLine);
    }


    @Override
    public Date getDate(DateFormat format) throws NotSuitableInputDataException, DialogInterruptionRequestException {
        String readLine = getString();
        try {
            return format.parse(readLine);
        } catch (ParseException e) {
            throw new NotSuitableInputDataException(e);
        }
    }

    @Override
    public <T extends Enum> T getChoice(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        int userChoice = getInt(0, values.length - 1);
        return values[userChoice];
    }

    private void checkReadLineForExitRequest(String readLine) throws DialogInterruptionRequestException {
        if (readLine.equalsIgnoreCase(interruptionPhrase)) {
            throw new DialogInterruptionRequestException();
        }
    }
}
