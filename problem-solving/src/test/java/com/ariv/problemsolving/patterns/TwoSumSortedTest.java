package com.ariv.problemsolving.patterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TwoSumSorted")
class TwoSumSortedTest {

    @Nested
    @DisplayName("findPair")
    class FindPairTests {

        @Test
        @DisplayName("should find pair")
        void shouldFindPair() {
            int[] result = TwoSumSorted.findPair(new int[]{
                                    1,
                                    2,
                                    4,
                                    7,
                                    11,
                                    15
                            },
                            15
                    );
            assertArrayEquals(new int[]{2, 4}, result);
        }

        @Test
        @DisplayName("should return minus one pair when not found")
        void shouldReturnMinusOnePairWhenNotFound() {

            int[] result =
                    TwoSumSorted.findPair(
                            new int[]{
                                    1,
                                    2,
                                    3
                            },
                            100
                    );

            assertArrayEquals(
                    new int[]{
                            -1,
                            -1
                    },
                    result
            );
        }

        @Test
        @DisplayName("should handle empty array")
        void shouldHandleEmptyArray() {

            int[] result =
                    TwoSumSorted.findPair(
                            new int[]{},
                            10
                    );

            assertArrayEquals(
                    new int[]{
                            -1,
                            -1
                    },
                    result
            );
        }

        @Test
        @DisplayName("should handle single element")
        void shouldHandleSingleElement() {

            int[] result =
                    TwoSumSorted.findPair(
                            new int[]{10},
                            10
                    );

            assertArrayEquals(
                    new int[]{
                            -1,
                            -1
                    },
                    result
            );
        }

        @Test
        @DisplayName("should find first and last element")
        void shouldFindFirstAndLastElement() {

            int[] result =
                    TwoSumSorted.findPair(
                            new int[]{
                                    1,
                                    2,
                                    3,
                                    9
                            },
                            10
                    );

            assertArrayEquals(
                    new int[]{
                            0,
                            3
                    },
                    result
            );
        }

        @Test
        @DisplayName("should reject null array")
        void shouldRejectNullArray() {

            assertThrows(
                    NullPointerException.class,
                    () -> TwoSumSorted.findPair(
                            null,
                            10
                    )
            );
        }
    }
}