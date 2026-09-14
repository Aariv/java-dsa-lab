package com.ariv.problemsolving.patterns.slidingwindow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("LongestRepeatingCharacterReplacement")
class LongestRepeatingCharacterReplacementTest {

    @Nested
    @DisplayName("findLength")
    class FindLengthTests {

        @Test
        @DisplayName("should find longest repeating character replacement")
        void shouldFindLongestRepeatingCharacterReplacement() {

            assertEquals(
                    4,
                    LongestRepeatingCharacterReplacement
                            .findLength(
                                    "AABABBA",
                                    1
                            )
            );
        }

        @Test
        @DisplayName("should handle all identical characters")
        void shouldHandleAllIdenticalCharacters() {

            assertEquals(
                    4,
                    LongestRepeatingCharacterReplacement
                            .findLength(
                                    "AAAA",
                                    1
                            )
            );
        }

        @Test
        @DisplayName("should handle single character")
        void shouldHandleSingleCharacter() {

            assertEquals(
                    1,
                    LongestRepeatingCharacterReplacement
                            .findLength(
                                    "A",
                                    0
                            )
            );
        }

        @Test
        @DisplayName("should handle empty string")
        void shouldHandleEmptyString() {

            assertEquals(
                    0,
                    LongestRepeatingCharacterReplacement
                            .findLength(
                                    "",
                                    2
                            )
            );
        }

        @Test
        @DisplayName("should handle zero replacements")
        void shouldHandleZeroReplacements() {

            assertEquals(
                    2,
                    LongestRepeatingCharacterReplacement
                            .findLength(
                                    "AABBA",
                                    0
                            )
            );
        }

        @Test
        @DisplayName("should allow replacing entire window")
        void shouldAllowReplacingEntireWindow() {

            assertEquals(
                    4,
                    LongestRepeatingCharacterReplacement
                            .findLength(
                                    "ABCD",
                                    3
                            )
            );
        }

        @Test
        @DisplayName("should find longest window for mixed characters")
        void shouldFindLongestWindowForMixedCharacters() {

            assertEquals(
                    4,
                    LongestRepeatingCharacterReplacement
                            .findLength(
                                    "ABAB",
                                    2
                            )
            );
        }

        @Test
        @DisplayName("should handle repeated pattern")
        void shouldHandleRepeatedPattern() {

            assertEquals(
                    5,
                    LongestRepeatingCharacterReplacement
                            .findLength(
                                    "BAAAB",
                                    2
                            )
            );
        }

        @Test
        @DisplayName("should reject null value")
        void shouldRejectNullValue() {

            assertThrows(
                    NullPointerException.class,
                    () -> LongestRepeatingCharacterReplacement
                            .findLength(
                                    null,
                                    1
                            )
            );
        }

        @Test
        @DisplayName("should reject negative replacements")
        void shouldRejectNegativeReplacements() {

            assertThrows(
                    IllegalArgumentException.class,
                    () -> LongestRepeatingCharacterReplacement
                            .findLength(
                                    "ABC",
                                    -1
                            )
            );
        }
    }
}