package com.ariv.algorithms.sorting;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class provides a method to perform a merge sort on an array.
 * It is a utility class and cannot be instantiated.
 */
public final class MergeSort {

    private MergeSort() {
    }

    /**
     * Sorts the supplied array in ascending order.
     *
     * Uses merge sort.
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

        mergeSort(
                array,
                0,
                array.length - 1
        );
    }

    private static <
            T extends Comparable<? super T>>
    void mergeSort(
            T[] array,
            int left,
            int right) {

        if (left >= right) {
            return;
        }

        int middle =
                left + (right - left) / 2;

        mergeSort(
                array,
                left,
                middle
        );

        mergeSort(
                array,
                middle + 1,
                right
        );

        merge(
                array,
                left,
                middle,
                right
        );
    }

    private static <
            T extends Comparable<? super T>>
    void merge(
            T[] array,
            int left,
            int middle,
            int right) {

        T[] leftArray = Arrays.copyOfRange(
                array,
                left,
                middle + 1
        );

        T[] rightArray = Arrays.copyOfRange(
                array,
                middle + 1,
                right + 1
        );

        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = left;

        while (leftIndex < leftArray.length
                && rightIndex < rightArray.length) {

            if (leftArray[leftIndex]
                    .compareTo(
                            rightArray[rightIndex]
                    ) <= 0) {

                array[mergedIndex] =
                        leftArray[leftIndex];

                leftIndex++;
            }
            else {

                array[mergedIndex] =
                        rightArray[rightIndex];

                rightIndex++;
            }

            mergedIndex++;
        }

        while (leftIndex < leftArray.length) {

            array[mergedIndex] =
                    leftArray[leftIndex];

            leftIndex++;
            mergedIndex++;
        }

        while (rightIndex < rightArray.length) {

            array[mergedIndex] =
                    rightArray[rightIndex];

            rightIndex++;
            mergedIndex++;
        }
    }
}