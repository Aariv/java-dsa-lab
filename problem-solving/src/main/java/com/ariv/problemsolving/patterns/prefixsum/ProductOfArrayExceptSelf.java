package com.ariv.problemsolving.patterns.prefixsum;

import java.util.Objects;

/**
 * Given an array nums of n integers where n > 1, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * <p>
 * Note: Please solve it without division and in O(n).
 */
public final class ProductOfArrayExceptSelf {

    private ProductOfArrayExceptSelf() {
    }

    /**
     * Calculates the product of all elements in the array except for the element at the current index.
     *
     * @param values an array of integers
     * @return an array where each element is the product of all elements in the input array except for the element at that index
     * @throws NullPointerException if the input array is null
     */
    public static int[] calculate(int[] values) {

        Objects.requireNonNull(values, "Values cannot be null");

        int length = values.length;

        int[] result = new int[length];

        result[0] = 1;

        /*
         * Prefix products
         */
        for (int index = 1; index < length; index++) {
            result[index] = result[index - 1] * values[index - 1];
        }

        /*
         * Suffix products
         */
        int suffixProduct = 1;
        for (int index = length - 1; index >= 0; index--) {
            result[index] = result[index] * suffixProduct;
            suffixProduct *= values[index];
        }

        return result;
    }
}