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

        Objects.requireNonNull(values, "Values cannot be null");

        int left = 0;
        int right = values.length - 1;

        while (left < right) {

            int sum = values[left] + values[right];

            if (sum == target) {
                return new int[]{left, right};
            }

            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}