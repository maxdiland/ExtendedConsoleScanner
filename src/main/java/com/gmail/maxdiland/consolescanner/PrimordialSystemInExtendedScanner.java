package com.gmail.maxdiland.consolescanner;

import com.gmail.maxdiland.consolescanner.exception.DialogInterruptionRequestException;
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
    private final String INTERRUPTION_STRING;

    public PrimordialSystemInExtendedScanner() {
        this("exit");
    }

    public PrimordialSystemInExtendedScanner(String interruptionString) {
        this.scanner = new Scanner(System.in);
        INTERRUPTION_STRING = interruptionString;
    }

    @Override
    public String getNextLine() throws DialogInterruptionRequestException {
        String readLine = scanner.nextLine();
        checkReadLineForExitRequest(readLine);
        return readLine;
    }

    @Override
    public int getNextInt() throws NotSuitableInputDataException, DialogInterruptionRequestException {
        String readLine = getNextLine();
        try {
            return Integer.parseInt(readLine);
        } catch (NumberFormatException nfe) {
            throw new NotSuitableInputDataException();
        }
    }

    @Override
    public int getNextInt(int allowedMinimumValue, int allowedMaximumValue) throws NotSuitableInputDataException, DialogInterruptionRequestException {
        if (allowedMinimumValue > allowedMaximumValue) {
            throw new IllegalArgumentException("Allowed minimum value shouldn't be greater than allowed maximum value");
        }

        int gottenInt = getNextInt();
        if (gottenInt >= allowedMinimumValue && gottenInt <= allowedMaximumValue) {
            return gottenInt;
        } else {
            throw new NotSuitableInputDataException();
        }
    }

    @Override
    public File getNextExistingFile() throws NotSuitableInputDataException, DialogInterruptionRequestException {
        File file = getNextFile();
        if (file.exists()) {
            return file;
        } else {
            throw new NotSuitableInputDataException();
        }
    }

    @Override
    public File getNextFile() throws NotSuitableInputDataException, DialogInterruptionRequestException {
        String readLine = getNextLine();
        return new File(readLine);
    }


    @Override
    public Date getDate(DateFormat format) throws NotSuitableInputDataException, DialogInterruptionRequestException {
        String readLine = getNextLine();
        try {
            return format.parse(readLine);
        } catch (ParseException e) {
            throw new NotSuitableInputDataException(e);
        }
    }

    private void checkReadLineForExitRequest(String readLine) throws DialogInterruptionRequestException {
        if (readLine.equalsIgnoreCase(INTERRUPTION_STRING)) {
            throw new DialogInterruptionRequestException();
        }
    }
}
