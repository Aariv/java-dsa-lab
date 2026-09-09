package com.ariv.algorithms.recursion;

/**
 * This class provides a method to calculate the nth Fibonacci number using recursion.
 * It is a utility class and cannot be instantiated.
 */
public final class Fibonacci {

    private Fibonacci() {
    }

    /**
     * Calculates the nth Fibonacci number.
     *
     * @param n the position in the Fibonacci sequence (0-based index)
     * @return the nth Fibonacci number
     * @throws IllegalArgumentException if n is negative
     */
    public static long calculate(int n) {

        if (n < 0) {
            throw new IllegalArgumentException(
                    "Fibonacci is undefined for negative numbers"
            );
        }

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return calculate(n - 1) + calculate(n - 2);
    }
}