package com.ariv.problemsolving.patterns;

import java.util.Objects;

/**
 * A utility class that provides a method to move all zeroes in an array to the end while maintaining the order of non-zero elements.
 */
public final class MoveZeroes {

    private MoveZeroes() {
    }

    /**
     * Moves all zeroes in the given array to the end while maintaining the order of non-zero elements.
     *
     * @param values the array of integers
     * @throws NullPointerException if the input array is null
     */
    public static void move(int[] values) {

        Objects.requireNonNull(values, "Values cannot be null");

        int writeIndex = 0;

        for (int readIndex = 0; readIndex < values.length; readIndex++) {

            if (values[readIndex] != 0) {
                values[writeIndex] = values[readIndex];
                writeIndex++;
            }
        }
        while (writeIndex < values.length) {
            values[writeIndex] = 0;
            writeIndex++;
        }
    }
}