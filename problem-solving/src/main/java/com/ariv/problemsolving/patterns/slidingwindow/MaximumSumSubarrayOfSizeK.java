package com.ariv.problemsolving.patterns.slidingwindow;

import java.util.Objects;

public final class MaximumSumSubarrayOfSizeK {

    private MaximumSumSubarrayOfSizeK() {
    }

    public static int findMaximumSum(
            int[] values,
            int windowSize) {

        Objects.requireNonNull(
                values,
                "Values cannot be null"
        );

        if (windowSize <= 0) {
            throw new IllegalArgumentException(
                    "Window size must be greater than zero"
            );
        }

        if (windowSize > values.length) {
            throw new IllegalArgumentException(
                    "Window size cannot exceed array length"
            );
        }

        int windowSum = 0;

        for (int index = 0;
             index < windowSize;
             index++) {

            windowSum += values[index];
        }

        int maximumSum = windowSum;

        for (int index = windowSize;
             index < values.length;
             index++) {

            windowSum =
                    windowSum
                            - values[index - windowSize]
                            + values[index];

            maximumSum =
                    Math.max(
                            maximumSum,
                            windowSum
                    );
        }

        return maximumSum;
    }
}
