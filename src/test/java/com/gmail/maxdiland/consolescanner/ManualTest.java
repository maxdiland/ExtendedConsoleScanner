package com.gmail.maxdiland.consolescanner;

import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;

/**
 * author Maksim Diland
 */
public class ManualTest {
    public static void main(String[] args) {
        SystemInExtendedScanner scanner =
                (SystemInExtendedScanner) Proxy.newProxyInstance(
                        ClassLoader.getSystemClassLoader(),
                        new Class[]{SystemInExtendedScanner.class},
                        new RetriableExtendedScannerInvocationHandler(new PrimordialSystemInExtendedScanner(), "Incorrect input. Try again or enter \"exit\" to finish.")
                );

        System.out.println( scanner.getNextInt() );
        System.out.println( scanner.getDate(new SimpleDateFormat("dd-MM-yyyy")) );
    }
}
