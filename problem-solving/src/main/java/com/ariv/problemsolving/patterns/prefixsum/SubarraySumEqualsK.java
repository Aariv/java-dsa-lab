package com.ariv.problemsolving.patterns.prefixsum;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Problem:
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 *
 * Explanation:
 * The subarrays that sum to 2 are [1,1] (starting at index 0) and [1,1] (starting at index 1).
 *
 * Approach:
 * We can use a prefix sum approach with a hash map to keep track of the counts of prefix sums we have seen so far.
 * For each element in the array, we calculate the current prefix sum and check if there exists a prefix sum that,
 * when subtracted from the current prefix sum, equals k. If such a prefix sum exists, it means we have found a subarray
 * that sums to k. We then update our hash map with the current prefix sum.
 */
public final class SubarraySumEqualsK {

    private SubarraySumEqualsK() {
    }

    /**
     * Counts the number of continuous subarrays whose sum equals to the target value.
     *
     * @param values the array of integers
     * @param target the target sum
     * @return the count of continuous subarrays that sum to the target
     */
    public static int countSubarrays(int[] values, int target) {

        Objects.requireNonNull(values, "Values cannot be null");

        // Create a hash map to store the counts of prefix sums
        Map<Integer, Integer> prefixCounts = new HashMap<>();

        // Initialize the count of prefix sum 0 to 1 to account for subarrays that start from the beginning
        prefixCounts.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        // Iterate through the array and calculate the prefix sum
        for (int value : values) {
            // Update the prefix sum with the current value
            prefixSum += value;

            // Check if there exists a prefix sum that, when subtracted from the current prefix sum, equals the target
            count += prefixCounts.getOrDefault(prefixSum - target, 0);

            // Update the count of the current prefix sum in the hash map
            prefixCounts.put(prefixSum, prefixCounts.getOrDefault(prefixSum, 0) + 1);
        }

        // Return the total count of continuous subarrays that sum to the target
        return count;
    }
}