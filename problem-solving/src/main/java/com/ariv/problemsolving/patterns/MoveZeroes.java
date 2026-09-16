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

        // Validate that the input array is not null
        Objects.requireNonNull(values, "Values cannot be null");

        // Initialize a write index to keep track of the position to write non-zero values
        int writeIndex = 0;

        // Iterate through the array and move non-zero values to the front
        for (int readIndex = 0; readIndex < values.length; readIndex++) {

            // If the current value is not zero, write it to the write index and increment the write index
            if (values[readIndex] != 0) {
                // Move the non-zero value to the write index
                values[writeIndex] = values[readIndex];
                // Increment the write index for the next non-zero value
                writeIndex++;
            }
        }
        // After all non-zero values have been moved, fill the remaining positions in the array with zeroes
        while (writeIndex < values.length) {
            values[writeIndex] = 0;
            writeIndex++;
        }
    }
}