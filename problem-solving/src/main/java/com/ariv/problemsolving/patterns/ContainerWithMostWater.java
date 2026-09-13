package com.ariv.problemsolving.patterns;

import java.util.Objects;

/**
 * A utility class that provides a method to calculate the maximum area of water that can be contained
 * between two lines represented by an array of heights.
 */
public final class ContainerWithMostWater {

    private ContainerWithMostWater() {
    }

    public static int maxArea(
            int[] heights) {

        Objects.requireNonNull(
                heights,
                "Heights cannot be null"
        );

        if (heights.length < 2) {
            return 0;
        }

        int left = 0;
        int right = heights.length - 1;

        int maximumArea = 0;

        while (left < right) {

            int width =
                    right - left;

            int height =
                    Math.min(
                            heights[left],
                            heights[right]
                    );

            int area =
                    width * height;

            maximumArea =
                    Math.max(
                            maximumArea,
                            area
                    );

            if (heights[left]
                    < heights[right]) {

                left++;

            } else {

                right--;
            }
        }

        return maximumArea;
    }
}