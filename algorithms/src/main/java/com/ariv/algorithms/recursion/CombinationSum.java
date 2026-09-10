package com.ariv.algorithms.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * This class provides a method to find all unique combinations of candidate numbers that sum up to a target value.
 * Each candidate number can be used multiple times in the combinations.
 * The order of values within a combination does not matter.
 */
public final class CombinationSum {

    private CombinationSum() {
    }

    /**
     * Finds all unique combinations whose values add up to the target.
     *
     * Each candidate may be selected multiple times.
     * The order of values within a combination does not matter.
     *
     * Example:
     *
     * candidates = [2, 3, 6, 7]
     * target = 7
     *
     * result = [[2, 2, 3], [7]]
     *
     * @param candidates positive candidate values
     * @param target target sum
     * @return all unique combinations that sum to the target
     */
    public static List<List<Integer>> findCombinations(
            int[] candidates,
            int target) {

        Objects.requireNonNull(
                candidates,
                "Candidates cannot be null"
        );

        if (target < 0) {
            throw new IllegalArgumentException(
                    "Target cannot be negative"
            );
        }

        validateCandidates(candidates);

        int[] sortedCandidates =
                Arrays.copyOf(
                        candidates,
                        candidates.length
                );

        Arrays.sort(sortedCandidates);

        List<List<Integer>> combinations =
                new ArrayList<>();

        backtrack(
                sortedCandidates,
                target,
                0,
                new ArrayList<>(),
                combinations
        );

        return combinations;
    }

    private static void backtrack(
            int[] candidates,
            int remainingTarget,
            int startIndex,
            List<Integer> currentCombination,
            List<List<Integer>> combinations) {

        /*
         * A valid combination has been formed.
         */
        if (remainingTarget == 0) {
            combinations.add(
                    new ArrayList<>(currentCombination)
            );

            return;
        }

        for (int index = startIndex;
             index < candidates.length;
             index++) {

            int candidate = candidates[index];

            /*
             * Skip duplicate candidate values at the same
             * recursion level.
             *
             * Example:
             *
             * candidates = [2, 2, 3]
             *
             * Without this condition, [2, 2, 3] could be
             * generated through multiple identical branches.
             */
            if (index > startIndex
                    && candidate == candidates[index - 1]) {

                continue;
            }

            /*
             * Because candidates are sorted, every candidate after
             * this one will also be greater than the remaining target.
             */
            if (candidate > remainingTarget) {
                break;
            }

            /*
             * Choose.
             */
            currentCombination.add(candidate);

            /*
             * Explore.
             *
             * Pass index rather than index + 1 because the same
             * candidate may be reused.
             */
            backtrack(
                    candidates,
                    remainingTarget - candidate,
                    index,
                    currentCombination,
                    combinations
            );

            /*
             * Undo the choice.
             */
            currentCombination.remove(
                    currentCombination.size() - 1
            );
        }
    }

    private static void validateCandidates(
            int[] candidates) {

        for (int candidate : candidates) {
            if (candidate <= 0) {
                throw new IllegalArgumentException(
                        "Candidates must contain only positive values"
                );
            }
        }
    }
}