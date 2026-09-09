package com.ariv.algorithms.recursion;

/**
 * This class provides a method to calculate the factorial of a non-negative integer using recursion.
 * It is a utility class and cannot be instantiated.
 */
public final class Factorial {

    private Factorial() {
    }

    /**
     * Calculates the factorial of a non-negative integer n.
     *
     * @param n the non-negative integer for which to calculate the factorial
     * @return the factorial of n
     * @throws IllegalArgumentException if n is negative
     */
    public static long calculate(int n) {

        if (n < 0) {
            throw new IllegalArgumentException(
                    "Factorial is undefined for negative numbers"
            );
        }

        if (n <= 1) {
            return 1;
        }

        return n * calculate(n - 1);
    }
}