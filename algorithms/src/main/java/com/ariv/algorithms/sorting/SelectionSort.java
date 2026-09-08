package com.ariv.algorithms.sorting;

import java.util.Objects;

/**
 * This class provides a method to perform a selection sort on an array.
 * It is a utility class and cannot be instantiated.
 */
public final class SelectionSort {

    private SelectionSort() {
    }

    /**
     * Sorts the supplied array in ascending order.
     *
     * Uses selection sort.
     */
    public static <
            T extends Comparable<? super T>>
    void sort(T[] array) {

        Objects.requireNonNull(
                array,
                "Array cannot be null"
        );

        for (int current = 0;
             current < array.length - 1;
             current++) {

            int minimumIndex = current;

            for (int candidate = current + 1;
                 candidate < array.length;
                 candidate++) {

                if (array[candidate]
                        .compareTo(
                                array[minimumIndex]
                        ) < 0) {

                    minimumIndex = candidate;
                }
            }

            if (minimumIndex != current) {
                swap(
                        array,
                        current,
                        minimumIndex
                );
            }
        }
    }

    private static <T> void swap(
            T[] array,
            int left,
            int right) {

        T temp = array[left];

        array[left] = array[right];

        array[right] = temp;
    }
}