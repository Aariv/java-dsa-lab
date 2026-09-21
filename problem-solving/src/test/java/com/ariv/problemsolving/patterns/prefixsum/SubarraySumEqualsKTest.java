package com.ariv.problemsolving.patterns.prefixsum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubarraySumEqualsKTest {

    @Test
    void shouldCountSubarrays() {

        assertEquals(
                2,
                SubarraySumEqualsK.countSubarrays(
                        new int[]{
                                1,
                                1,
                                1
                        },
                        2
                )
        );
    }

    // No matching subarray
    @Test
    void shouldReturnZeroWhenNoMatchingSubarray() {

        assertEquals(
                0,
                SubarraySumEqualsK.countSubarrays(
                        new int[]{
                                1,
                                2,
                                3
                        },
                        7
                )
        );
    }

    // Negative numbers
    @Test
    void shouldCountSubarraysWithNegativeNumbers() {

        assertEquals(
                4,
                SubarraySumEqualsK.countSubarrays(
                        new int[]{
                                1,
                                -1,
                                0,
                                1,
                                -1
                        },
                        0
                )
        );
    }
}
