package com.gmail.maxdiland.consolescanner;

import com.gmail.maxdiland.consolescanner.exception.DialogInterruptionRequestException;
import com.gmail.maxdiland.consolescanner.exception.FileNotFoundException;
import com.gmail.maxdiland.consolescanner.exception.NotSuitableInputDataException;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

/**
 * The SystemInExtendedScanner provides dialogs with user via System.in
 *
 * author Maksim Diland
 */
public interface SystemInExtendedScanner {

    /**
     * Retrieves String entered by user
     *
     * @return entered by user string
     * @throws DialogInterruptionRequestException in case if user have entered specific phrase to interrupt dialog
     */
    String getString() throws DialogInterruptionRequestException;


    /**
     * Retrieves int entered by user
     *
     * @return entered by user int
     * @throws NotSuitableInputDataException in case if user have entered some value which cannot be casted to int
     * @throws DialogInterruptionRequestException in case if user have entered specific phrase to interrupt dialog
     */
    int getInt() throws NotSuitableInputDataException, DialogInterruptionRequestException;


    /**
     * Retrieves int entered by user within given range
     *
     * @param minValue - lower bound of the range
     * @param maxValue - higher bound of the range
     * @return entered by user int
     * @throws NotSuitableInputDataException in case if user have entered some value which cannot be casted to int or
     *         if entered int is out of range
     * @throws DialogInterruptionRequestException in case if user have entered specific phrase to interrupt dialog
     */
    int getInt(int minValue, int maxValue) throws NotSuitableInputDataException, DialogInterruptionRequestException;

    /**
     * Retrieves File by path entered by user
     *
     * @return File which corresponds to a path entered by user
     * @throws NotSuitableInputDataException in case if entered by user path is invalid
     * @throws DialogInterruptionRequestException in case if user have entered specific phrase to interrupt dialog
     */
    File getFile() throws NotSuitableInputDataException, DialogInterruptionRequestException;


    /**
     * Retrieves existing File by path entered by user
     *
     * @return File which corresponds to a path entered by user
     * @throws FileNotFoundException if File corresponding to entered by user path doesn't exist
     * @throws NotSuitableInputDataException in case if entered by user path is invalid
     * @throws DialogInterruptionRequestException in case if user have entered specific phrase to interrupt dialog
     */
    File getExistingFile() throws FileNotFoundException, NotSuitableInputDataException,
            DialogInterruptionRequestException;


    /**
     * Retrieves Date entered by user
     *
     * @param format - format of the date the user should follow
     * @return entered by user Date
     * @throws NotSuitableInputDataException if entered by user date string cannot be converted to Date object
     * @throws DialogInterruptionRequestException in case if user have entered specific phrase to interrupt dialog
     */
    Date getDate(DateFormat format) throws NotSuitableInputDataException, DialogInterruptionRequestException;


    /**
     * Retrieves chosen by user static enumeration of Enum
     *
     * @param enumClass - Class of the Enum the static enumeration of which should be chosen
     * @return chosen by user static enumeration from Enum
     */
    <T extends Enum> T getChoice(Class<T> enumClass);
}
