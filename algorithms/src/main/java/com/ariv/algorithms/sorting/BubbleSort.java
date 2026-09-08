package com.ariv.algorithms.sorting;

import java.util.Objects;

/**
 * This class provides a method to perform an optimized bubble sort on an array.
 * It is a utility class and cannot be instantiated.
 */
public final class BubbleSort {

    private BubbleSort() {
    }

    /**
     * Sorts the supplied array in ascending order.
     *
     * Uses optimized bubble sort.
     */
    public static <
            T extends Comparable<? super T>>
    void sort(T[] array) {

        Objects.requireNonNull(
                array,
                "Array cannot be null"
        );

        if (array.length < 2) {
            return;
        }

        for (int pass = 0;
             pass < array.length - 1;
             pass++) {

            boolean swapped = false;

            for (int current = 0;
                 current < array.length - 1 - pass;
                 current++) {

                if (array[current].compareTo(
                        array[current + 1]
                ) > 0) {

                    swap(
                            array,
                            current,
                            current + 1
                    );

                    swapped = true;
                }
            }

            /*
             * Already sorted.
             */
            if (!swapped) {
                break;
            }
        }
    }

    private static <T> void swap(
            T[] array,
            int left,
            int right) {

        T temporary = array[left];

        array[left] = array[right];

        array[right] = temporary;
    }
}