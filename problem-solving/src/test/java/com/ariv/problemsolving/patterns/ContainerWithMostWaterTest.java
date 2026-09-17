package com.ariv.problemsolving.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerWithMostWaterTest {

    @Test
    void shouldCalculateMaximumArea() {
        int result = ContainerWithMostWater.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});

        assertEquals(49, result);
    }

    @Test
    void shouldHandleTwoElements() {
        int result = ContainerWithMostWater.maxArea(new int[]{1, 1});
        assertEquals(1, result);
    }

    @Test
    void shouldHandleIncreasingHeights() {

        int result =
                ContainerWithMostWater
                        .maxArea(
                                new int[]{
                                        1,
                                        2,
                                        3,
                                        4,
                                        5
                                }
                        );

        assertEquals(
                6,
                result
        );
    }

    @Test
    void shouldHandleSingleElement() {

        int result =
                ContainerWithMostWater
                        .maxArea(
                                new int[]{
                                        5
                                }
                        );

        assertEquals(
                0,
                result
        );
    }

    @Test
    void shouldHandleEmptyArray() {

        int result =
                ContainerWithMostWater
                        .maxArea(
                                new int[]{}
                        );

        assertEquals(
                0,
                result
        );
    }

    @Test
    void shouldRejectNullArray() {

        assertThrows(
                NullPointerException.class,
                () -> ContainerWithMostWater
                        .maxArea(null)
        );
    }
}