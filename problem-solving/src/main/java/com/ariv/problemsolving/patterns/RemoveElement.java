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

        Objects.requireNonNull(values, "Values cannot be null");

        int writeIndex = 0;

        for (int readIndex = 0; readIndex < values.length; readIndex++) {

            if (values[readIndex] != valueToRemove) {
                values[writeIndex] = values[readIndex];
                writeIndex++;
            }
        }

        return writeIndex;
    }
}