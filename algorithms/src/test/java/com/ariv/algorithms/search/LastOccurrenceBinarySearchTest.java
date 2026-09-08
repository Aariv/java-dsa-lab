package com.ariv.algorithms.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LastOccurrenceBinarySearchTest {

    @Test
    void shouldReturnFirstOccurrence() {

        Integer[] values = {
                10,
                20,
                20,
                20,
                30
        };

        int index = LastOccurrenceBinarySearch.search(values,20);

        assertEquals(3, index);
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
                LastOccurrenceBinarySearch
                        .search(
                                values,
                                20
                        )
        );
    }

    @Test
    void shouldReturnThirdElement() {

        Integer[] values = {
                10,
                10,
                10,
                20
        };

        assertEquals(
                2,
                LastOccurrenceBinarySearch
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
                LastOccurrenceBinarySearch
                        .search(
                                values,
                                99
                        )
        );
    }
}
