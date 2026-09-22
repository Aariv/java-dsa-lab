package com.ariv.problemsolving.patterns.prefixsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Merges overlapping intervals in a given array of intervals.
 * <p>
 * Example:
 * <p>
 * Input:  [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, they are merged into [1,6].
 */
public final class MergeIntervals {

    private MergeIntervals() {
    }

    /**
     * Merges overlapping intervals in a given array of intervals.
     *
     * @param intervals an array of intervals, where each interval is represented as an array of two integers [start, end]
     * @return a new array of merged intervals
     * @throws NullPointerException if the input array is null
     */
    public static int[][] merge(int[][] intervals) {

        Objects.requireNonNull(intervals, "Intervals cannot be null");

        if (intervals.length == 0) {
            return new int[0][];
        }

        int[][] sortedIntervals = Arrays.copyOf(intervals, intervals.length);

        Arrays.sort(sortedIntervals,
                (left, right) ->
                        Integer.compare(
                                left[0],
                                right[0]
                        )
        );

        List<int[]> mergedIntervals = new ArrayList<>();

        int[] currentInterval = Arrays.copyOf(sortedIntervals[0], 2);

        for (int index = 1; index < sortedIntervals.length; index++) {
            int[] nextInterval = sortedIntervals[index];

            if (nextInterval[0] <= currentInterval[1]) {
                currentInterval[1] = Math.max(currentInterval[1], nextInterval[1]);
            } else {
                mergedIntervals.add(currentInterval);
                currentInterval = Arrays.copyOf(nextInterval, 2);
            }
        }

        mergedIntervals.add(currentInterval);
        return mergedIntervals.toArray(new int[0][]);
    }
}