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

        // If the array has fewer than 3 elements, return an empty list as no triplets can be formed
        if (values.length < 3) {
            return triplets;
        }

        // Create a copy of the input array and sort it to facilitate the two-pointer approach
        int[] sortedValues = Arrays.copyOf(values, values.length);

        // Sort the copied array to prepare for the two-pointer technique
        Arrays.sort(sortedValues);

        // Iterate through the sorted array, treating each element as a potential first element of a triplet
        for (int index = 0; index < sortedValues.length - 2; index++) {

            // Skip duplicate elements to avoid counting the same triplet multiple times
            if (index > 0 && sortedValues[index] == sortedValues[index - 1]) {
                continue;
            }

            // Initialize two pointers, one starting just after the current index and the other at the end of the array
            int left = index + 1;
            // The right pointer starts at the last index of the sorted array
            int right = sortedValues.length - 1;

            // Use a while loop to find pairs that, along with the current element, sum to zero
            while (left < right) {

                // Calculate the sum of the current triplet
                int sum = sortedValues[index] + sortedValues[left] + sortedValues[right];

                // Check if the sum of the triplet is zero
                if (sum == 0) {

                    // If a valid triplet is found, add it to the list of triplets
                    triplets.add(List.of(sortedValues[index], sortedValues[left], sortedValues[right]));

                    // Move both pointers inward to look for other potential pairs
                    left++;
                    right--;

                    // Skip over duplicate elements to ensure unique triplets are recorded
                    while (left < right && sortedValues[left] == sortedValues[left - 1]) {
                        left++;
                    }

                    // Skip over duplicate elements on the right side as well
                    while (left < right && sortedValues[right] == sortedValues[right + 1]) {
                        right--;
                    }

                    // Continue searching for other pairs that may form a valid triplet with the current index
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