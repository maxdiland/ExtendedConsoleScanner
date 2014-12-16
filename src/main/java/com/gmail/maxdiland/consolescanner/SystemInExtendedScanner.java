package com.gmail.maxdiland.consolescanner;

import com.gmail.maxdiland.consolescanner.exception.DialogInterruptionRequestException;
import com.gmail.maxdiland.consolescanner.exception.NotSuitableInputDataException;

import java.io.File;

/**
 * author Maksim Diland
 */
public interface SystemInExtendedScanner {
    String getNextLine() throws DialogInterruptionRequestException;
    int getNextInt() throws NotSuitableInputDataException, DialogInterruptionRequestException;
    int getNextInt(int allowedMinimumValue, int allowedMaximumValue) throws NotSuitableInputDataException, DialogInterruptionRequestException;

    File getNextFile() throws NotSuitableInputDataException, DialogInterruptionRequestException;
    File getNextExistingFile() throws NotSuitableInputDataException, DialogInterruptionRequestException;
}
