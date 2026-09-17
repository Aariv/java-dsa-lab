package com.ariv.problemsolving.patterns;

import java.util.Objects;

/**
 * A utility class that provides a method to calculate the maximum area of water that can be contained
 * between two lines represented by an array of heights.
 */
public final class ContainerWithMostWater {

    private ContainerWithMostWater() {
    }

    /**
     * Calculates the maximum area of water that can be contained between two lines represented by the given array of heights.
     *
     * @param heights an array of integers representing the heights of the lines
     * @return the maximum area of water that can be contained
     * @throws NullPointerException if the input array is null
     */
    public static int maxArea(int[] heights) {

        Objects.requireNonNull(heights, "Heights cannot be null");

        // If the array has less than two heights, no container can be formed, so return 0
        if (heights.length < 2) {
            return 0;
        }

        // Initialize two pointers, one at the beginning and one at the end of the array
        int left = 0;
        // Initialize the right pointer to the last index of the array
        int right = heights.length - 1;

        // Initialize a variable to keep track of the maximum area found so far
        int maximumArea = 0;

        // Use a two-pointer approach to find the maximum area
        while (left < right) {

            // Calculate the width between the two pointers
            int width = right - left;

            // Calculate the height of the container, which is determined by the shorter line
            int height = Math.min(heights[left], heights[right]);

            // Calculate the area of the container formed by the two lines
            int area = width * height;

            // Update the maximum area if the current area is greater than the previously recorded maximum area
            maximumArea = Math.max(maximumArea, area);

            // Move the pointer corresponding to the shorter line inward, as this may lead to a taller line and potentially a larger area
            if (heights[left] < heights[right]) {
                // Move the left pointer inward to potentially find a taller line
                left++;
                // Move the right pointer inward to potentially find a taller line
            } else {
                right--;
            }
        }
        return maximumArea;
    }
}