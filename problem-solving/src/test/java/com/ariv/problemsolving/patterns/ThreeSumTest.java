package com.ariv.problemsolving.patterns;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ThreeSumTest {

    @Test
    void shouldFindTriplets() {

        List<List<Integer>> result = ThreeSum.findTriplets(
                        new int[]{
                                -1,
                                0,
                                1,
                                2,
                                -1,
                                -4
                        }
                );

        assertEquals(2, result.size());

        assertTrue(result.contains(List.of(-1, -1, 2)));

        assertTrue(result.contains(List.of(-1, 0, 1)));
    }

    @Test
    void shouldReturnEmptyWhenNoTripletExists() {

        List<List<Integer>> result =
                ThreeSum.findTriplets(
                        new int[]{
                                1,
                                2,
                                3
                        }
                );

        assertTrue(
                result.isEmpty()
        );
    }

    @Test
    void shouldReturnSingleTripletForAllZeroes() {

        List<List<Integer>> result =
                ThreeSum.findTriplets(
                        new int[]{
                                0,
                                0,
                                0,
                                0
                        }
                );

        assertEquals(
                1,
                result.size()
        );

        assertTrue(
                result.contains(
                        List.of(0,0,0)
                )
        );
    }

    @Test
    void shouldRejectNullArray() {

        assertThrows(
                NullPointerException.class,
                () -> ThreeSum.findTriplets(null)
        );
    }
}
