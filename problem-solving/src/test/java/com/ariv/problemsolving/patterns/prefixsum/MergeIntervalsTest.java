package com.ariv.problemsolving.patterns.prefixsum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeIntervalsTest {

    @Test
    void shouldMergeIntervals() {

        int[][] result = MergeIntervals.merge(new int[][]{{1,3}, {2,6}, {8,10}, {15,18}});

        assertArrayEquals(
                new int[][]{
                        {1,6},
                        {8,10},
                        {15,18}
                },
                result
        );
    }
}
