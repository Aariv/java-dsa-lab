package com.ariv.problemsolving.patterns.slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinimumWindowSubstringTest {

    @Test
    void shouldFindMinimumWindow() {

        assertEquals(
                "BANC",
                MinimumWindowSubstring
                        .findMinimumWindow(
                                "ADOBECODEBANC",
                                "ABC"
                        )
        );
    }

    @Test
    void shouldReturnExactMatch() {

        assertEquals(
                "ABC",
                MinimumWindowSubstring
                        .findMinimumWindow(
                                "ABC",
                                "ABC"
                        )
        );
    }

    @Test
    void shouldReturnEmptyWhenNoWindowExists() {

        assertEquals(
                "",
                MinimumWindowSubstring
                        .findMinimumWindow(
                                "ABC",
                                "XYZ"
                        )
        );
    }

    @Test
    void shouldHandleEmptySource() {

        assertEquals(
                "",
                MinimumWindowSubstring
                        .findMinimumWindow(
                                "",
                                "ABC"
                        )
        );
    }

    @Test
    void shouldRejectNullSource() {

        assertThrows(
                NullPointerException.class,
                () -> MinimumWindowSubstring
                        .findMinimumWindow(
                                null,
                                "ABC"
                        )
        );
    }
}
