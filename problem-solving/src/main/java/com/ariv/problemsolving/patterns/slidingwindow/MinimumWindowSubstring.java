package com.ariv.problemsolving.patterns.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code MinimumWindowSubstring} class provides a method to find the minimum window substring in a given source string that contains all characters of a target string.
 * <p>
 * This implementation uses a sliding window approach to efficiently find the minimum window substring.
 * </p>
 */
public final class MinimumWindowSubstring {

    private MinimumWindowSubstring() {
    }

    public static String findMinimumWindow(String source, String target) {

        Objects.requireNonNull(
                source,
                "Source cannot be null"
        );

        Objects.requireNonNull(
                target,
                "Target cannot be null"
        );

        if (source.isEmpty()
                || target.isEmpty()) {

            return "";
        }

        Map<Character, Integer> targetCounts =
                new HashMap<>();

        for (char character
                : target.toCharArray()) {

            targetCounts.put(
                    character,
                    targetCounts.getOrDefault(
                            character,
                            0
                    ) + 1
            );
        }

        Map<Character, Integer> windowCounts =
                new HashMap<>();

        int requiredMatches =
                targetCounts.size();

        int currentMatches = 0;

        int left = 0;
        int minimumLength =
                Integer.MAX_VALUE;

        int minimumStart = 0;

        for (int right = 0;
             right < source.length();
             right++) {

            char character =
                    source.charAt(right);

            windowCounts.put(
                    character,
                    windowCounts.getOrDefault(
                            character,
                            0
                    ) + 1
            );

            if (targetCounts.containsKey(character)
                    &&
                    targetCounts.get(character)
                            .equals(
                                    windowCounts.get(character)
                            )) {

                currentMatches++;
            }

            while (currentMatches
                    == requiredMatches) {

                int currentLength =
                        right - left + 1;

                if (currentLength
                        < minimumLength) {

                    minimumLength =
                            currentLength;

                    minimumStart =
                            left;
                }

                char leftCharacter =
                        source.charAt(left);

                windowCounts.put(
                        leftCharacter,
                        windowCounts.get(leftCharacter)
                                - 1
                );

                if (targetCounts.containsKey(leftCharacter)
                        &&
                        windowCounts.get(leftCharacter)
                                < targetCounts.get(leftCharacter)) {

                    currentMatches--;
                }

                left++;
            }
        }

        if (minimumLength
                == Integer.MAX_VALUE) {

            return "";
        }

        return source.substring(
                minimumStart,
                minimumStart + minimumLength
        );
    }
}