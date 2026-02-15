package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Simple test runner demonstrating DRY principles:
 * - Reusable test infrastructure
 * - Data-driven testing
 * - No code duplication
 *
 * This is a temporary solution until Maven repository access is available.
 */
public class SimpleTestRunner {

    private static final String EXPECTED_ERROR_MESSAGE = "Name cannot be null or empty";
    private static final List<TestResult> results = new ArrayList<>();

    public static void main(String[] args) {
        printHeader("Running HelloWorld Tests");

        HelloWorld helloWorld = new HelloWorld();

        // Valid name tests - data-driven approach
        testValidNames(helloWorld, new String[][] {
            {"World", "Hello, World!"},
            {"A", "Hello, A!"},
            {"John Doe", "Hello, John Doe!"},
            {"O'Brien", "Hello, O'Brien!"},
            {"José", "Hello, José!"}
        });

        // Invalid name tests - data-driven approach
        testInvalidNames(helloWorld, new String[] {null, "", "   ", "\t", "\n"});

        printSummary();

        if (getFailureCount() > 0) {
            System.exit(1);
        }
    }

    // ========================================
    // Data-Driven Test Methods (DRY)
    // ========================================

    /**
     * Tests multiple valid names using data-driven approach.
     * Eliminates duplication of similar test cases.
     */
    private static void testValidNames(HelloWorld helloWorld, String[][] testData) {
        for (String[] data : testData) {
            String name = data[0];
            String expected = data[1];
            test("Should greet '" + name + "' correctly", () -> {
                String result = helloWorld.greet(name);
                assertEqual(expected, result);
            });
        }
    }

    /**
     * Tests multiple invalid names using data-driven approach.
     * Eliminates duplication of exception test cases.
     */
    private static void testInvalidNames(HelloWorld helloWorld, String[] invalidNames) {
        for (String invalidName : invalidNames) {
            String displayName = invalidName == null ? "null" : "'" + invalidName + "'";
            test("Should reject " + displayName, () -> {
                assertThrows(IllegalArgumentException.class,
                    () -> helloWorld.greet(invalidName),
                    EXPECTED_ERROR_MESSAGE);
            });
        }
    }

    // ========================================
    // Test Infrastructure (Reusable)
    // ========================================

    private static void test(String description, Runnable testCase) {
        try {
            testCase.run();
            recordSuccess(description);
        } catch (Exception e) {
            recordFailure(description, e);
        }
    }

    private static void recordSuccess(String description) {
        results.add(new TestResult(description, true, null));
        System.out.println("✓ " + description);
    }

    private static void recordFailure(String description, Exception error) {
        results.add(new TestResult(description, false, error));
        System.out.println("✗ " + description);
        System.out.println("  Error: " + error.getMessage());
    }

    // ========================================
    // Assertion Methods (DRY)
    // ========================================

    private static void assertEqual(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", but got: " + actual);
        }
    }

    private static void assertThrows(Class<? extends Exception> expectedException,
                                    Runnable code,
                                    String expectedMessage) {
        try {
            code.run();
            throw new AssertionError("Expected " + expectedException.getSimpleName() + " but no exception was thrown");
        } catch (Exception e) {
            if (!expectedException.isInstance(e)) {
                throw new AssertionError("Expected " + expectedException.getSimpleName() +
                    " but got " + e.getClass().getSimpleName());
            }
            if (!e.getMessage().contains(expectedMessage)) {
                throw new AssertionError("Expected message to contain '" + expectedMessage +
                    "' but got: " + e.getMessage());
            }
        }
    }

    // ========================================
    // Reporting (DRY)
    // ========================================

    private static void printHeader(String title) {
        System.out.println("========================================");
        System.out.println(title);
        System.out.println("========================================\n");
    }

    private static void printSummary() {
        int total = results.size();
        int passed = (int) results.stream().filter(r -> r.passed).count();
        int failed = total - passed;

        System.out.println("\n========================================");
        System.out.println("Test Summary");
        System.out.println("========================================");
        System.out.println("Total tests:  " + total);
        System.out.println("Passed:       " + passed + " ✓");
        System.out.println("Failed:       " + failed + " ✗");
        System.out.println("Success rate: " + (passed * 100 / total) + "%");
        System.out.println("========================================");
    }

    private static int getFailureCount() {
        return (int) results.stream().filter(r -> !r.passed).count();
    }

    // ========================================
    // Test Result Record
    // ========================================

    private static class TestResult {
        final String description;
        final boolean passed;
        final Exception error;

        TestResult(String description, boolean passed, Exception error) {
            this.description = description;
            this.passed = passed;
            this.error = error;
        }
    }
}
