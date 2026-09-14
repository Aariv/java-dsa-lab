package com.ariv.problemsolving.patterns.slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumSumSubarrayOfSizeKTest {

    @Test
    public void testFindMaximumSum_StandardExample() {
        int[] values = {2, 1, 5, 1, 3, 2};
        int windowSize = 3;
        int expected = 9; // The maximum sum of subarray of size 3 is 5 + 1 + 3 = 9
        int actual = MaximumSumSubarrayOfSizeK.findMaximumSum(values, windowSize);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindMaximumSum_WindowSizeOne() {
        int[] values = {2, 1, 5, 1, 3, 2};
        int windowSize = 1;
        int expected = 5; // The maximum sum of subarray of size 1 is the maximum element itself
        int actual = MaximumSumSubarrayOfSizeK.findMaximumSum(values, windowSize);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindMaximumSum_WindowSizeArrayLength() {
        int[] values = {2, 1, 5, 1, 3, 2};
        int windowSize = values.length;
        int expected = 14; // The maximum sum of subarray of size equal to array length is the sum of all elements
        int actual = MaximumSumSubarrayOfSizeK.findMaximumSum(values, windowSize);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindMaximumSum_SingleElementArray() {
        int[] values = {5};
        int windowSize = 1;
        int expected = 5; // The maximum sum of subarray of size 1 is the only element itself
        int actual = MaximumSumSubarrayOfSizeK.findMaximumSum(values, windowSize);
        assertEquals(expected, actual);
    }

    @Test
    public void testFindMaximumSum_EmptyArray() {
        int[] values = {};
        int windowSize = 1;
        try {
            MaximumSumSubarrayOfSizeK.findMaximumSum(values, windowSize);
        } catch (IllegalArgumentException e) {
            assertEquals("Window size cannot exceed array length", e.getMessage());
        }
    }

    @Test
    public void testFindMaximumSum_InvalidWindowSize() {
        int[] values = {2, 1, 5, 1, 3, 2};
        int windowSize = 0;
        try {
            MaximumSumSubarrayOfSizeK.findMaximumSum(values, windowSize);
        } catch (IllegalArgumentException e) {
            assertEquals("Window size must be greater than zero", e.getMessage());
        }
    }

    @Test
    public void testFindMaximumSum_NullInput() {
        int[] values = null;
        int windowSize = 3;
        try {
            MaximumSumSubarrayOfSizeK.findMaximumSum(values, windowSize);
        } catch (NullPointerException e) {
            assertEquals("Values cannot be null", e.getMessage());
        }
    }

}
