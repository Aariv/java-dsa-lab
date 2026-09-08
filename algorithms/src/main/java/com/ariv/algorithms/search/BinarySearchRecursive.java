package com.ariv.algorithms.search;

import java.util.Objects;

/**
 * Binary search is a fast search algorithm with run-time complexity of Ο(log n).
 * This search algorithm works on the principle of divide and conquer.
 * For this algorithm to work properly, the data collection should be in sorted form.
 */
public final class BinarySearchRecursive {

    private BinarySearchRecursive() {
    }

    /**
     * Searches a sorted array.
     *
     * Returns index when found.
     * Returns -1 when not found.
     */
    public static <T extends Comparable<? super T>> int search(
            T[] array,
            T target) {

        Objects.requireNonNull(
                array,
                "Array cannot be null"
        );

        return search(
                array,
                target,
                0,
                array.length - 1
        );
    }

    /**
     * Searches a sorted array.
     *
     * Returns index when found.
     * Returns -1 when not found.
     */
    private static < T extends Comparable<? super T>> int search(
            T[] array,
            T target,
            int low,
            int high) {

        if (low > high) {
            return -1;
        }

        int middle = low + (high - low) / 2;

        int comparison = array[middle].compareTo(target);

        if (comparison == 0) {
            return middle;
        }

        if (comparison < 0) {
            return search(
                    array,
                    target,
                    middle + 1,
                    high
            );
        }

        return search(
                array,
                target,
                low,
                middle - 1
        );
    }
}