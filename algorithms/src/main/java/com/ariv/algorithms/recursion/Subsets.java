package com.ariv.algorithms.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class provides a method to generate all possible subsets of a given array of values using recursion.
 * It is a utility class and cannot be instantiated.
 */
public final class Subsets {

    private Subsets() {
    }

    /**
     * Generates all possible subsets of the given array of values.
     *
     * @param values the array of values for which to generate subsets
     * @param <T>    the type of elements in the array
     * @return a list containing all possible subsets of the given values
     * @throws NullPointerException if the values array is null
     */
    public static <T> List<List<T>> generate(T[] values) {

        Objects.requireNonNull(
                values,
                "Values cannot be null"
        );

        List<List<T>> result = new ArrayList<>();

        backtrack(values, 0, new ArrayList<>(), result);

        return result;
    }

    /**
     * Helper method to perform backtracking and generate subsets.
     *
     * @param values        the array of values
     * @param index         the current index in the values array
     * @param currentSubset the current subset being constructed
     * @param result        the list to store all generated subsets
     * @param <T>           the type of elements in the array
     */
    private static <T> void backtrack(T[] values, int index, List<T> currentSubset, List<List<T>> result) {

        if (index == values.length) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }

        /*
         * Choice 1:
         * Skip current element
         */
        backtrack(values, index + 1, currentSubset, result);

        /*
         * Choice 2:
         * Include current element
         */
        currentSubset.add(values[index]);

        backtrack(values, index + 1, currentSubset, result);

        /*
         * Backtrack
         */
        currentSubset.remove(currentSubset.size() - 1); // real backtracking operation
    }
}