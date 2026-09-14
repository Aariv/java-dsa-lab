package com.ariv.problemsolving.patterns.slidingwindow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("LongestSubstringWithoutRepeatingCharacters")
class LongestSubstringWithoutRepeatingCharactersTest {

    @Nested
    @DisplayName("findLength")
    class FindLengthTests {

        @Test
        @DisplayName("should find longest substring")
        void shouldFindLongestSubstring() {

            assertEquals(
                    3,
                    LongestSubstringWithoutRepeatingCharacters
                            .findLength(
                                    "abcabcbb"
                            )
            );
        }

        @Test
        @DisplayName("should handle all duplicates")
        void shouldHandleAllDuplicates() {

            assertEquals(
                    1,
                    LongestSubstringWithoutRepeatingCharacters
                            .findLength(
                                    "bbbbb"
                            )
            );
        }

        @Test
        @DisplayName("should handle mixed characters")
        void shouldHandleMixedCharacters() {

            assertEquals(
                    3,
                    LongestSubstringWithoutRepeatingCharacters
                            .findLength(
                                    "pwwkew"
                            )
            );
        }

        @Test
        @DisplayName("should handle empty string")
        void shouldHandleEmptyString() {

            assertEquals(
                    0,
                    LongestSubstringWithoutRepeatingCharacters
                            .findLength("")
            );
        }

        @Test
        @DisplayName("should handle single character")
        void shouldHandleSingleCharacter() {

            assertEquals(
                    1,
                    LongestSubstringWithoutRepeatingCharacters
                            .findLength("a")
            );
        }

        @Test
        @DisplayName("should reject null value")
        void shouldRejectNullValue() {

            assertThrows(
                    NullPointerException.class,
                    () -> LongestSubstringWithoutRepeatingCharacters
                            .findLength(null)
            );
        }
    }
}