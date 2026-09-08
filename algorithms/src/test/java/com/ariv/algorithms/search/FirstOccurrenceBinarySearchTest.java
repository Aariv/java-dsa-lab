package com.ariv.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstOccurrenceBinarySearchTest {

    @Test
    void shouldReturnFirstOccurrence() {

        Integer[] values = {
                10,
                20,
                20,
                20,
                30
        };

        int index = FirstOccurrenceBinarySearch.search(values,20);

        assertEquals(1, index);
    }

    @Test
    void shouldReturnSingleOccurrence() {

        Integer[] values = {
                10,
                20,
                30
        };

        assertEquals(
                1,
                FirstOccurrenceBinarySearch
                        .search(
                                values,
                                20
                        )
        );
    }

    @Test
    void shouldReturnFirstElement() {

        Integer[] values = {
                10,
                10,
                10,
                20
        };

        assertEquals(
                0,
                FirstOccurrenceBinarySearch
                        .search(
                                values,
                                10
                        )
        );
    }

    @Test
    void shouldReturnMinusOneWhenMissing() {

        Integer[] values = {
                10,
                20,
                30
        };

        assertEquals(
                -1,
                FirstOccurrenceBinarySearch
                        .search(
                                values,
                                99
                        )
        );
    }
}
