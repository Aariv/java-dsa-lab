package com.ariv.problemsolving.patterns;

import java.util.Objects;

/**
 * A utility class that provides a method to find a pair of indices in a sorted array
 * whose corresponding values sum up to a given target.
 */
public final class TwoSumSorted {

    private TwoSumSorted() {
    }

    /**
     * Finds a pair of indices in the sorted array `values` such that the sum of the values
     * at those indices equals the specified `target`.
     *
     * @param values the sorted array of integers
     * @param target the target sum to find
     * @return an array containing the two indices if a pair is found; otherwise, returns [-1, -1]
     * @throws NullPointerException if the input array is null
     */
    public static int[] findPair(int[] values, int target) {

        // Validate that the input array is not null
        Objects.requireNonNull(values, "Values cannot be null");

        // Initialize two pointers, one at the start and one at the end of the array
        int left = 0;
        // Initialize the right pointer to the last index of the array
        int right = values.length - 1;

        // Use a while loop to iterate until the two pointers meet
        while (left < right) {

            // Calculate the sum of the values at the left and right pointers
            int sum = values[left] + values[right];

            // Check if the sum matches the target
            if (sum == target) {
                // If a pair is found, return the indices of the two values
                return new int[]{left, right};
            }

            // If the sum is less than the target, move the left pointer to the right
            if (sum < target) {
                // Increment the left pointer to increase the sum
                left++;
            } else {
                // If the sum is greater than the target, move the right pointer to the left
                right--;
            }
        }
        // If no pair is found that sums to the target, return [-1, -1]
        return new int[]{-1, -1};
    }
}