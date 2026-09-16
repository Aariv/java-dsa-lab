package com.ariv.problemsolving.patterns;

import java.util.Arrays;
import java.util.Objects;

/**
 * A utility class that provides methods to remove duplicates from a sorted array.
 */
public final class RemoveDuplicatesSortedArray {

    private RemoveDuplicatesSortedArray() {
    }

    /**
     * Removes duplicates from a sorted array of integers in-place.
     *
     * @param values the sorted array of integers
     * @return the new length of the array after removing duplicates
     * @throws NullPointerException if the input array is null
     */
    public static int removeDuplicates(int[] values) {

        // Validate that the input array is not null
        Objects.requireNonNull(values, "Values cannot be null");

        // If the array is empty, return 0 as there are no elements to process
        if (values.length == 0) {
            return 0;
        }

        // Initialize a write index to keep track of the position to write the next unique value
        int writeIndex = 0;

        // Iterate through the array starting from the second element (index 1)
        for (int readIndex = 1; readIndex < values.length; readIndex++) {
            // If the current value is different from the last unique value, write it to the position indicated by writeIndex
            if (values[readIndex] != values[writeIndex]) {
                // Increment the writeIndex to prepare for the next unique value
                writeIndex++;
                // Write the unique value to the writeIndex position
                values[writeIndex] = values[readIndex];
            }
        }
        return writeIndex + 1;
    }

    public static int[] uniqueValues(int[] values) {

        Objects.requireNonNull(
                values,
                "Values cannot be null"
        );

        int[] copy = Arrays.copyOf(values, values.length);

        int uniqueCount = removeDuplicates(copy);

        return Arrays.copyOf(copy, uniqueCount);
    }

    public static boolean isSorted(int[] values) {

        Objects.requireNonNull(values, "Values cannot be null");

        for (int index = 1; index < values.length; index++) {

            if (values[index] < values[index - 1]) {
                return false;
            }
        }
        return true;
    }
}