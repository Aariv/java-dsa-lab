package com.ariv.problemsolving.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A utility class that provides a method to find all unique triplets in an array that sum up to zero.
 */
public final class ThreeSum {

    private ThreeSum() {
    }

    /**
     * Finds all unique triplets in the given array that sum up to zero.
     *
     * @param values the array of integers
     * @return a list of lists containing the unique triplets that sum to zero
     * @throws NullPointerException if the input array is null
     */
    public static List<List<Integer>> findTriplets(int[] values) {

        Objects.requireNonNull(values, "Values cannot be null");

        List<List<Integer>> triplets = new ArrayList<>();

        if (values.length < 3) {
            return triplets;
        }

        int[] sortedValues = Arrays.copyOf(values, values.length);

        Arrays.sort(sortedValues);

        for (int index = 0;
             index < sortedValues.length - 2;
             index++) {

            if (index > 0 && sortedValues[index] == sortedValues[index - 1]) {
                continue;
            }

            int left = index + 1;
            int right = sortedValues.length - 1;

            while (left < right) {

                int sum = sortedValues[index] + sortedValues[left] + sortedValues[right];

                if (sum == 0) {

                    triplets.add(List.of(sortedValues[index], sortedValues[left], sortedValues[right]));

                    left++;
                    right--;

                    while (left < right && sortedValues[left] == sortedValues[left - 1]) {
                        left++;
                    }

                    while (left < right && sortedValues[right] == sortedValues[right + 1]) {
                        right--;
                    }

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }
}