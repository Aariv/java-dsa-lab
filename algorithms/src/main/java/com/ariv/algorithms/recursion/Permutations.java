package com.ariv.algorithms.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class provides a method to generate all possible permutations of a given array of values using recursion.
 * It is a utility class and cannot be instantiated.
 */
public final class Permutations {

    private Permutations() {
    }

    /**
     * Generates all possible permutations of the given array of values.
     *
     * @param values the array of values for which to generate permutations
     * @param <T>    the type of elements in the array
     * @return a list containing all possible permutations of the given values
     * @throws NullPointerException if the values array is null
     */
    public static <T> List<List<T>> generate(T[] values) {

        Objects.requireNonNull(
                values,
                "Values cannot be null"
        );

        List<List<T>> result = new ArrayList<>();

        boolean[] used = new boolean[values.length];

        backtrack(values, used, new ArrayList<>(), result);

        return result;
    }

    /**
     * Helper method to perform backtracking and generate permutations.
     *
     * @param values  the array of values
     * @param used    a boolean array indicating which elements have been used in the current permutation
     * @param current the current permutation being constructed
     * @param result  the list to store all generated permutations
     * @param <T>     the type of elements in the array
     */
    private static <T> void backtrack(T[] values, boolean[] used, List<T> current, List<List<T>> result) {

        if (current.size() == values.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int index = 0; index < values.length; index++) {
            if (used[index]) {
                continue;
            }
            used[index] = true;
            current.add(values[index]); // Choose
            backtrack(values, used, current, result); // Explore
            current.remove(current.size() - 1); // Undo
            used[index] = false;
        }
    }
}