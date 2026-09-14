package com.ariv.problemsolving.patterns.slidingwindow;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class provides a method to find the length of the longest substring without repeating characters.
 */
public final class LongestSubstringWithoutRepeatingCharacters {

    private LongestSubstringWithoutRepeatingCharacters() {
    }

    /**
     * Finds the length of the longest substring without repeating characters in the given string.
     *
     * @param value the input string
     * @return the length of the longest substring without repeating characters
     * @throws NullPointerException if the input string is null
     */
    public static int findLength(String value) {

        Objects.requireNonNull(value, "Value cannot be null");

        Set<Character> window = new HashSet<>();

        int left = 0;
        int maximumLength = 0;

        for (int right = 0; right < value.length(); right++) {

            char currentCharacter = value.charAt(right);

            while (window.contains(currentCharacter)) {
                window.remove(value.charAt(left));
                left++;
            }

            window.add(currentCharacter);

            maximumLength = Math.max(maximumLength, right - left + 1);
        }

        return maximumLength;
    }
}