package com.ariv.problemsolving.patterns.prefixsum;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Inserts a new interval into a list of non-overlapping intervals and merges any overlapping intervals.
 * <p>
 * Example:
 * <p>
 * Input:  intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 */
public final class InsertInterval {

    private InsertInterval() {
    }

    /**
     * Inserts a new interval into a list of non-overlapping intervals and merges any overlapping intervals.
     *
     * @param intervals   an array of non-overlapping intervals, where each interval is represented as an array of two integers [start, end]
     * @param newInterval the new interval to be inserted, represented as an array of two integers [start, end]
     * @return a new array of merged intervals after inserting the new interval
     * @throws NullPointerException if the input intervals or newInterval is null
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        Objects.requireNonNull(
                intervals,
                "Intervals cannot be null"
        );

        Objects.requireNonNull(
                newInterval,
                "New interval cannot be null"
        );

        List<int[]> result = new ArrayList<>();

        int index = 0;

        /*
         * Add intervals before new interval.
         */
        while (index < intervals.length && intervals[index][1] < newInterval[0]) {
            result.add(intervals[index]);
            index++;
        }

        /*
         * Merge overlapping intervals.
         */
        while (index < intervals.length && intervals[index][0] <= newInterval[1]) {

            newInterval[0] = Math.min(newInterval[0], intervals[index][0]);

            newInterval[1] = Math.max(newInterval[1], intervals[index][1]);

            index++;
        }

        result.add(new int[]{newInterval[0], newInterval[1]});

        /*
         * Add remaining intervals.
         */
        while (index < intervals.length) {
            result.add(intervals[index]);
            index++;
        }

        return result.toArray(new int [0][]);
    }
}