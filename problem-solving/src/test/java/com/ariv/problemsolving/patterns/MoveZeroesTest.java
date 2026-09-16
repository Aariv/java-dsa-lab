package com.ariv.problemsolving.patterns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("MoveZeroes")
class MoveZeroesTest {

    @Nested
    @DisplayName("move")
    class MoveTests {

        @Test
        @DisplayName("should move zeroes to the end")
        void shouldMoveZeroesToTheEnd() {

            int[] values = {0, 1, 0, 3, 12};

            MoveZeroes.move(values);

            assertArrayEquals(new int[]{1, 3, 12, 0, 0}, values);
        }

        @Test
        @DisplayName("should preserve order of non zero values")
        void shouldPreserveOrderOfNonZeroValues() {

            int[] values = {
                    4,
                    0,
                    5,
                    0,
                    6
            };

            MoveZeroes.move(values);

            assertArrayEquals(
                    new int[]{
                            4,
                            5,
                            6,
                            0,
                            0
                    },
                    values
            );
        }

        @Test
        @DisplayName("should handle array with no zeroes")
        void shouldHandleArrayWithNoZeroes() {

            int[] values = {
                    1,
                    2,
                    3
            };

            MoveZeroes.move(values);

            assertArrayEquals(
                    new int[]{
                            1,
                            2,
                            3
                    },
                    values
            );
        }

        @Test
        @DisplayName("should handle array with all zeroes")
        void shouldHandleArrayWithAllZeroes() {

            int[] values = {
                    0,
                    0,
                    0
            };

            MoveZeroes.move(values);

            assertArrayEquals(
                    new int[]{
                            0,
                            0,
                            0
                    },
                    values
            );
        }

        @Test
        @DisplayName("should handle empty array")
        void shouldHandleEmptyArray() {

            int[] values = {};

            MoveZeroes.move(values);

            assertArrayEquals(
                    new int[]{},
                    values
            );
        }

        @Test
        @DisplayName("should handle single element array")
        void shouldHandleSingleElementArray() {

            int[] values = {
                    10
            };

            MoveZeroes.move(values);

            assertArrayEquals(
                    new int[]{
                            10
                    },
                    values
            );
        }

        @Test
        @DisplayName("should handle single zero")
        void shouldHandleSingleZero() {

            int[] values = {
                    0
            };

            MoveZeroes.move(values);

            assertArrayEquals(
                    new int[]{
                            0
                    },
                    values
            );
        }

        @Test
        @DisplayName("should reject null array")
        void shouldRejectNullArray() {

            assertThrows(
                    NullPointerException.class,
                    () -> MoveZeroes.move(null)
            );
        }
    }
}