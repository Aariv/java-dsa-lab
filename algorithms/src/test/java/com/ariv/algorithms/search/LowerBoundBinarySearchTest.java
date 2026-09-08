package com.ariv.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LowerBoundBinarySearchTest {

    @Test
    void shouldReturnExactMatch() {

        Integer[] values = {
                10,20,30,40
        };

        assertEquals(
                1,
                LowerBoundBinarySearch
                        .search(
                                values,
                                20
                        )
        );
    }

    @Test
    void shouldReturnNextGreaterElement() {

        Integer[] values = {
                10,20,30,40
        };

        assertEquals(
                2,
                LowerBoundBinarySearch
                        .search(
                                values,
                                25
                        )
        );
    }

    @Test
    void shouldReturnFirstDuplicate() {

        Integer[] values = {
                10,
                20,
                20,
                20,
                30
        };

        assertEquals(
                1,
                LowerBoundBinarySearch
                        .search(
                                values,
                                20
                        )
        );
    }

    @Test
    void shouldReturnFirstElement() {

        Integer[] values = {
                10,20,30
        };

        assertEquals(
                0,
                LowerBoundBinarySearch
                        .search(
                                values,
                                5
                        )
        );
    }

    @Test
    void shouldReturnMinusOne() {

        Integer[] values = {
                10,20,30
        };

        assertEquals(
                -1,
                LowerBoundBinarySearch
                        .search(
                                values,
                                100
                        )
        );
    }
}
