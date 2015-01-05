package com.gmail.maxdiland.consolescanner;

import java.lang.reflect.Proxy;

/**
 * author Maksim Diland
 */
public final class ExtendedScannerFactory {

    public static SystemInExtendedScanner getRetriableExtendedScanner() {
        return  (SystemInExtendedScanner) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{SystemInExtendedScanner.class},
                new RetriableExtendedScannerInvocationHandler(new PrimordialSystemInExtendedScanner(), "Incorrect input. Try again or enter \"exit\" to finish.")
        );
    }
}
