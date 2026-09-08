package com.ariv.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpperBoundBinarySearchTest {

    @Test
    void shouldReturnUpperBound() {

        Integer[] values = {
                10,
                20,
                20,
                20,
                30,
                40
        };

        assertEquals(
                4,
                UpperBoundBinarySearch.search(
                        values,
                        20
                )
        );
    }

    @Test
    void shouldReturnNextGreaterElement() {

        Integer[] values = {
                10,
                20,
                30,
                40
        };

        assertEquals(
                2,
                UpperBoundBinarySearch.search(
                        values,
                        25
                )
        );
    }

    @Test
    void shouldReturnMinusOne() {

        Integer[] values = {
                10,
                20,
                30
        };

        assertEquals(
                -1,
                UpperBoundBinarySearch.search(
                        values,
                        100
                )
        );
    }
}
