package com.ariv.algorithms.sorting;

import java.util.Objects;

/**
 * This class provides a method to perform an insertion sort on an array.
 * It is a utility class and cannot be instantiated.
 */
public final class InsertionSort {

    private InsertionSort() {
    }

    /**
     * Sorts the supplied array in ascending order.
     *
     * Uses insertion sort.
     */
    public static <
            T extends Comparable<? super T>>
    void sort(T[] array) {

        Objects.requireNonNull(
                array,
                "Array cannot be null"
        );

        for (int current = 1;
             current < array.length;
             current++) {

            T valueToInsert = array[current];

            int position = current - 1;

            while (position >= 0 && array[position].compareTo(valueToInsert) > 0) {
                array[position + 1] =
                        array[position];
                position--;
            }

            array[position + 1] = valueToInsert;
        }
    }
}