package org.factoriaf5.pizzeriapaca.products.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ProductExceptionTest {

    @Test
    void testAddSuppressed() {
        ProductException exception = new ProductException("Main Exception");
        Throwable suppressedException = new IllegalArgumentException("Suppressed Exception");
        exception.addSuppressed(suppressedException);

        Throwable[] suppressed = exception.getSuppressed();
        assertEquals(1, suppressed.length);
        assertEquals(suppressedException, suppressed[0]);
    }

    @Test
    void testFillInStackTrace() {
        ProductException exception = new ProductException("Exception with stack trace");
        Throwable filledException = exception.fillInStackTrace();
        assertNotNull(filledException.getStackTrace());
        assertEquals(exception, filledException);
    }

    @Test
    void testGetCause() {
        Throwable cause = new IllegalArgumentException("Cause of exception");
        ProductException exception = new ProductException("Exception with cause", cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testGetLocalizedMessage() {
        ProductException exception = new ProductException("Localized exception message");
        assertEquals("Localized exception message", exception.getLocalizedMessage());
    }

    @Test
    void testGetMessage() {
        ProductException exception = new ProductException("Exception message");
        assertEquals("Exception message", exception.getMessage());
    }

    @Test
    void testGetStackTrace() {
        ProductException exception = new ProductException("Exception with stack trace");
        StackTraceElement[] stackTrace = exception.getStackTrace();
        assertNotNull(stackTrace);
        assertTrue(stackTrace.length > 0);
    }

    @Test
    void testGetSuppressed() {
        ProductException exception = new ProductException("Main Exception");
        assertEquals(0, exception.getSuppressed().length); 

        Throwable suppressedException = new IllegalArgumentException("Suppressed Exception");
        exception.addSuppressed(suppressedException);
        assertEquals(1, exception.getSuppressed().length);
        assertEquals(suppressedException, exception.getSuppressed()[0]);
    }

    @Test
    void testInitCause() {
        ProductException exception = new ProductException("Main Exception");
        Throwable cause = new IllegalArgumentException("Cause of exception");
        exception.initCause(cause);
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testPrintStackTrace() {
        ProductException exception = new ProductException("Exception with stack trace");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent);
        exception.printStackTrace(printStream);

        String printedStackTrace = outContent.toString();
        assertTrue(printedStackTrace.contains("ProductException: Exception with stack trace"));
    }

    @Test
    void testPrintStackTrace2() {
        ProductException exception = new ProductException("Exception with stack trace");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent)); 

        exception.printStackTrace();
        String printedStackTrace = outContent.toString();
        assertTrue(printedStackTrace.contains("ProductException: Exception with stack trace"));
    }

    @Test
    void testPrintStackTrace3() {
        ProductException exception = new ProductException("Exception with stack trace");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent);
        exception.printStackTrace(printStream);

        String printedStackTrace = outContent.toString();
        assertTrue(printedStackTrace.contains("ProductException: Exception with stack trace"));
    }

    @Test
    void testSetStackTrace() {
        ProductException exception = new ProductException("Exception with custom stack trace");
        StackTraceElement[] customStackTrace = new StackTraceElement[]{
            new StackTraceElement("ClassName", "methodName", "FileName", 123)
        };

        exception.setStackTrace(customStackTrace);
        StackTraceElement[] returnedStackTrace = exception.getStackTrace();
        assertArrayEquals(customStackTrace, returnedStackTrace);
    }

    @Test
    void testToString() {
        ProductException exception = new ProductException("Exception with toString");
        String exceptionString = exception.toString();
        assertTrue(exceptionString.contains("ProductException: Exception with toString"));
    }
}

