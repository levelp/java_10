package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Comprehensive tests for HelloWorld class demonstrating:
 * - 100% code coverage (line and branch)
 * - DRY principle (no code duplication)
 * - BDD-style test naming
 * - AssertJ fluent assertions (DSL)
 * - Parameterized tests for edge cases
 * - Reusable test helpers
 */
@DisplayName("HelloWorld Tests")
class HelloWorldTest {

    private static final String EXPECTED_ERROR_MESSAGE = "Name cannot be null or empty";
    private static final String GREETING_PREFIX = "Hello, ";
    private static final String GREETING_SUFFIX = "!";

    private HelloWorld helloWorld;

    @BeforeEach
    void setUp() {
        helloWorld = new HelloWorld();
    }

    // ========================================
    // Parameterized Tests - Valid Names
    // ========================================

    /**
     * Provides valid names with their expected greetings.
     * This eliminates duplication across multiple similar tests.
     */
    private static Stream<Arguments> validNamesWithExpectedGreetings() {
        return Stream.of(
            Arguments.of("World", "Hello, World!"),
            Arguments.of("A", "Hello, A!"),
            Arguments.of("Alexander", "Hello, Alexander!"),
            Arguments.of("John Doe", "Hello, John Doe!"),
            Arguments.of("O'Brien", "Hello, O'Brien!"),
            Arguments.of("José", "Hello, José!"),
            Arguments.of("Alice", "Hello, Alice!"),
            Arguments.of("Bob", "Hello, Bob!")
        );
    }

    @ParameterizedTest(name = "Should greet ''{0}'' with ''{1}''")
    @MethodSource("validNamesWithExpectedGreetings")
    @DisplayName("Should greet valid names correctly")
    void shouldGreetValidNames(String name, String expectedGreeting) {
        // When
        String result = helloWorld.greet(name);

        // Then
        assertThatGreetingIsCorrect(result, expectedGreeting);
    }

    // ========================================
    // Parameterized Tests - Invalid Names
    // ========================================

    @ParameterizedTest(name = "Should reject invalid name: ''{0}''")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "   ", "\t", "\n", "    \t\n"})
    @DisplayName("Should throw exception for invalid names")
    void shouldThrowExceptionForInvalidNames(String invalidName) {
        // When & Then
        assertThatInvalidNameThrowsException(invalidName);
    }

    // ========================================
    // Edge Case Tests
    // ========================================

    @Test
    @DisplayName("Should handle very long names")
    void shouldHandleVeryLongNames() {
        // Given
        String longName = "A".repeat(100);

        // When
        String result = helloWorld.greet(longName);

        // Then
        assertThatGreetingContainsName(result, longName);
        assertThat(result).hasSize(longName.length() + GREETING_PREFIX.length() + GREETING_SUFFIX.length());
    }

    @Test
    @DisplayName("Should return different greetings for different names")
    void shouldReturnDifferentGreetingsForDifferentNames() {
        // Given
        String name1 = "Alice";
        String name2 = "Bob";

        // When
        String result1 = helloWorld.greet(name1);
        String result2 = helloWorld.greet(name2);

        // Then
        assertThat(result1)
            .isNotEqualTo(result2)
            .contains(name1);
        assertThat(result2)
            .contains(name2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Charlie", "Diana", "Eve", "Frank", "Grace"})
    @DisplayName("Should greet multiple people with correct format")
    void shouldGreetMultiplePeopleWithCorrectFormat(String name) {
        // When
        String result = helloWorld.greet(name);

        // Then
        assertThatGreetingHasCorrectFormat(result, name);
    }

    // ========================================
    // Test Helper Methods (DRY)
    // ========================================

    /**
     * Helper method to verify greeting is exactly as expected.
     * Eliminates duplication of assertion logic.
     */
    private void assertThatGreetingIsCorrect(String actualGreeting, String expectedGreeting) {
        assertThat(actualGreeting)
            .as("Greeting should match expected format")
            .isEqualTo(expectedGreeting);
    }

    /**
     * Helper method to verify greeting contains the name.
     * Reusable assertion for any test needing to check name presence.
     */
    private void assertThatGreetingContainsName(String greeting, String name) {
        assertThat(greeting)
            .as("Greeting should contain the name")
            .startsWith(GREETING_PREFIX)
            .contains(name)
            .endsWith(GREETING_SUFFIX);
    }

    /**
     * Helper method to verify greeting has correct format.
     * Checks prefix, suffix, name presence, and length.
     */
    private void assertThatGreetingHasCorrectFormat(String greeting, String name) {
        assertThatGreetingContainsName(greeting, name);
        assertThat(greeting)
            .as("Greeting should have correct length")
            .hasSize(name.length() + GREETING_PREFIX.length() + GREETING_SUFFIX.length());
    }

    /**
     * Helper method to verify invalid names throw IllegalArgumentException.
     * Eliminates duplication across multiple exception tests.
     */
    private void assertThatInvalidNameThrowsException(String invalidName) {
        assertThatThrownBy(() -> helloWorld.greet(invalidName))
            .as("Invalid name should throw IllegalArgumentException")
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(EXPECTED_ERROR_MESSAGE)
            .hasNoCause();
    }
}
