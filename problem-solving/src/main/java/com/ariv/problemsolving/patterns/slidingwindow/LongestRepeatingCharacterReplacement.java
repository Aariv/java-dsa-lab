package com.ariv.problemsolving.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class provides a method to find the length of the longest substring that can be obtained
 * by replacing at most a given number of characters in the input string.
 */
public final class LongestRepeatingCharacterReplacement {

    private LongestRepeatingCharacterReplacement() {
    }

    /**
     * Finds the length of the longest substring that can be obtained by replacing at most a given number of characters.
     *
     * @param value        the input string
     * @param replacements the maximum number of character replacements allowed
     * @return the length of the longest substring after replacements
     * @throws NullPointerException     if the input string is null
     * @throws IllegalArgumentException if the number of replacements is negative
     */
    public static int findLength(String value, int replacements) {

        Objects.requireNonNull(value, "Value cannot be null");

        if (replacements < 0) {
            throw new IllegalArgumentException(
                    "Replacements cannot be negative"
            );
        }

        Map<Character, Integer> frequencies = new HashMap<>();

        int left = 0;
        int maximumLength = 0;
        int maximumFrequency = 0;

        for (int right = 0; right < value.length(); right++) {

            char currentCharacter = value.charAt(right);

            int frequency = frequencies.getOrDefault(currentCharacter, 0) + 1;

            frequencies.put(currentCharacter, frequency);

            maximumFrequency = Math.max(maximumFrequency, frequency);

            while ((right - left + 1) - maximumFrequency > replacements) {
                char leftCharacter = value.charAt(left);
                frequencies.put(leftCharacter, frequencies.get(leftCharacter) - 1);
                left++;
            }

            maximumLength = Math.max(maximumLength, right - left + 1);
        }

        return maximumLength;
    }
}