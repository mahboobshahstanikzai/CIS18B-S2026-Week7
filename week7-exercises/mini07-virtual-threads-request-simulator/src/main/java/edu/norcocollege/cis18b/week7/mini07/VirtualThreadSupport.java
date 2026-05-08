package edu.norcocollege.cis18b.week7.mini07;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * ============================================
 * SELF-CHECK ANSWERS - MINI 07
 * ============================================
 * 
 * Q1: Does code fail clearly instead of silently pretending?
 * A1: YES - throws IllegalStateException with clear message.
 * 
 * Q2: Why do virtual threads help blocking work but not fix unsafe state?
 * A2: Virtual threads are cheap for blocking I/O but still share heap
 *     memory - race conditions still need synchronization.
 */

public final class VirtualThreadSupport {
    private static final String ERROR_MESSAGE = "Virtual threads require Java 21 or newer.";

    private VirtualThreadSupport() {}

    public static boolean isAvailable() {
        try {
            Executors.class.getMethod("newVirtualThreadPerTaskExecutor");
            return true;
        } catch (NoSuchMethodException ex) {
            return false;
        }
    }

    public static ExecutorService newExecutor() {
        if (!isAvailable()) {
            throw new IllegalStateException(ERROR_MESSAGE);
        }

        try {
            Method factory = Executors.class.getMethod("newVirtualThreadPerTaskExecutor");
            return (ExecutorService) factory.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
            throw new IllegalStateException(ERROR_MESSAGE, ex);
        }
    }
    
    public static String errorMessage() {
        return ERROR_MESSAGE;
    }
}