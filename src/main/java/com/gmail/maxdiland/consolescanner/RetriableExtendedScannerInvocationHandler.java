package com.gmail.maxdiland.consolescanner;

import com.gmail.maxdiland.consolescanner.exception.FileNotFoundException;
import com.gmail.maxdiland.consolescanner.exception.NotSuitableInputDataException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author Maksim Diland
 */
public class RetriableExtendedScannerInvocationHandler implements InvocationHandler {
    private SystemInExtendedScanner scanner;
    private String retryMessage;

    public RetriableExtendedScannerInvocationHandler(SystemInExtendedScanner scanner, String retryMessage) {
        this.scanner = scanner;
        this.retryMessage = retryMessage;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        while (true) {
            try {
                return method.invoke(scanner, args);
            } catch (InvocationTargetException e) {
                Class<?> targetExceptionClass = e.getTargetException().getClass();
                if (targetExceptionClass == FileNotFoundException.class) {
                    System.out.println("File does not exist.");
                } else if (e.getTargetException().getClass() == NotSuitableInputDataException.class) {
                    System.out.println(retryMessage);
                } else {
                    throw e.getTargetException();
                }
            }
        }

    }
}
