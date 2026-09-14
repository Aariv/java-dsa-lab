package com.ariv.problemsolving.patterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RemoveElement")
class RemoveElementTest {

    @Nested
    @DisplayName("remove")
    class RemoveTests {

        @Test
        @DisplayName("should remove matching values")
        void shouldRemoveMatchingValues() {

            int[] values = {
                    3,
                    2,
                    2,
                    3
            };

            int newLength =
                    RemoveElement.remove(
                            values,
                            3
                    );

            assertAll(
                    () -> assertEquals(
                            2,
                            newLength
                    ),

                    () -> assertArrayEquals(
                            new int[]{
                                    2,
                                    2
                            },
                            Arrays.copyOf(
                                    values,
                                    newLength
                            )
                    )
            );
        }

        @Test
        @DisplayName("should remove multiple occurrences")
        void shouldRemoveMultipleOccurrences() {

            int[] values = {
                    0,
                    1,
                    2,
                    2,
                    3,
                    0,
                    4,
                    2
            };

            int newLength =
                    RemoveElement.remove(
                            values,
                            2
                    );

            assertAll(
                    () -> assertEquals(
                            5,
                            newLength
                    ),

                    () -> assertArrayEquals(
                            new int[]{
                                    0,
                                    1,
                                    3,
                                    0,
                                    4
                            },
                            Arrays.copyOf(
                                    values,
                                    newLength
                            )
                    )
            );
        }

        @Test
        @DisplayName("should handle no matching values")
        void shouldHandleNoMatchingValues() {

            int[] values = {
                    1,
                    2,
                    3
            };

            int newLength =
                    RemoveElement.remove(
                            values,
                            99
                    );

            assertAll(
                    () -> assertEquals(
                            3,
                            newLength
                    ),

                    () -> assertArrayEquals(
                            new int[]{
                                    1,
                                    2,
                                    3
                            },
                            Arrays.copyOf(
                                    values,
                                    newLength
                            )
                    )
            );
        }

        @Test
        @DisplayName("should remove all values")
        void shouldRemoveAllValues() {

            int[] values = {
                    5,
                    5,
                    5,
                    5
            };

            int newLength =
                    RemoveElement.remove(
                            values,
                            5
                    );

            assertEquals(
                    0,
                    newLength
            );
        }

        @Test
        @DisplayName("should handle empty array")
        void shouldHandleEmptyArray() {

            int[] values = {};

            int newLength =
                    RemoveElement.remove(
                            values,
                            1
                    );

            assertEquals(
                    0,
                    newLength
            );
        }

        @Test
        @DisplayName("should handle single element kept")
        void shouldHandleSingleElementKept() {

            int[] values = {
                    10
            };

            int newLength =
                    RemoveElement.remove(
                            values,
                            5
                    );

            assertAll(
                    () -> assertEquals(
                            1,
                            newLength
                    ),

                    () -> assertArrayEquals(
                            new int[]{
                                    10
                            },
                            Arrays.copyOf(
                                    values,
                                    newLength
                            )
                    )
            );
        }

        @Test
        @DisplayName("should handle single element removed")
        void shouldHandleSingleElementRemoved() {

            int[] values = {
                    10
            };

            int newLength =
                    RemoveElement.remove(
                            values,
                            10
                    );

            assertEquals(
                    0,
                    newLength
            );
        }

        @Test
        @DisplayName("should reject null array")
        void shouldRejectNullArray() {

            assertThrows(
                    NullPointerException.class,
                    () -> RemoveElement.remove(
                            null,
                            1
                    )
            );
        }
    }
}