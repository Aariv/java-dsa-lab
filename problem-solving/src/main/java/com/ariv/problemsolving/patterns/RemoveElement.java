package com.ariv.problemsolving.patterns;

import java.util.Objects;

/**
 * A utility class that provides a method to remove all occurrences of a specified value from an array.
 */
public final class RemoveElement {

    private RemoveElement() {
    }

    /**
     * Removes all occurrences of the specified value from the given array.
     *
     * @param values        the array of integers
     * @param valueToRemove the value to be removed from the array
     * @return the new length of the array after removal
     * @throws NullPointerException if the input array is null
     */
    public static int remove(int[] values, int valueToRemove) {

        // Validate that the input array is not null
        Objects.requireNonNull(values, "Values cannot be null");

        // Initialize a write index to keep track of the position to write the next non-matching value
        int writeIndex = 0;

        // Iterate through the array using a read index to examine each value
        for (int readIndex = 0; readIndex < values.length; readIndex++) {

            // If the current value does not match the value to remove, write it to the position indicated by writeIndex
            if (values[readIndex] != valueToRemove) {
                // Write the non-matching value to the writeIndex position
                values[writeIndex] = values[readIndex];
                // Increment the writeIndex to prepare for the next non-matching value
                writeIndex++;
            }
        }

        return writeIndex;
    }
}