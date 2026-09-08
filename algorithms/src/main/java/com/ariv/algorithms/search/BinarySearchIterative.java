package com.ariv.algorithms.search;

import java.util.Objects;

/**
 * Binary search is a fast search algorithm with run-time complexity of Ο(log n).
 * This search algorithm works on the principle of divide and conquer.
 * For this algorithm to work properly, the data collection should be in sorted form.
 */
public final class BinarySearchIterative {

    private BinarySearchIterative() {
    }

    /**
     * Searches a sorted array.
     *
     * Returns index when found.
     * Returns -1 when not found.
     */
    public static <T extends Comparable<? super T>> int search(T[] array, T target) {
        Objects.requireNonNull(
                array,
                "Array cannot be null"
        );

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {

            // because large indexes can overflow in some languages and environments.
            int middle = low + (high - low) / 2;

            int comparison =
                    array[middle]
                            .compareTo(target);

            if (comparison == 0) {
                return middle;
            }

            if (comparison < 0) {

                low = middle + 1;

            } else {

                high = middle - 1;
            }
        }

        return -1;
    }
}