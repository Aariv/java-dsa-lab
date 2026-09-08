package com.ariv.algorithms.sorting;

import java.util.Objects;

/**
 * This class provides a method to perform a quick sort on an array.
 * It is a utility class and cannot be instantiated.
 */
public final class QuickSort {

    private QuickSort() {
    }

    /**
     * Sorts the supplied array in ascending order.
     *
     * Uses quick sort.
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

        quickSort(
                array,
                0,
                array.length - 1
        );
    }

    private static <
            T extends Comparable<? super T>>
    void quickSort(
            T[] array,
            int low,
            int high) {

        if (low >= high) {
            return;
        }

        int pivotIndex =
                partition(
                        array,
                        low,
                        high
                );

        quickSort(
                array,
                low,
                pivotIndex - 1
        );

        quickSort(
                array,
                pivotIndex + 1,
                high
        );
    }

    private static <
            T extends Comparable<? super T>>
    int partition(
            T[] array,
            int low,
            int high) {

        T pivot = array[high];

        int smallerElementIndex =
                low - 1;

        for (int current = low;
             current < high;
             current++) {

            if (array[current]
                    .compareTo(pivot) <= 0) {

                smallerElementIndex++;

                swap(
                        array,
                        smallerElementIndex,
                        current
                );
            }
        }

        swap(
                array,
                smallerElementIndex + 1,
                high
        );

        return smallerElementIndex + 1;
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