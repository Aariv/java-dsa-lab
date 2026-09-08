package com.ariv.algorithms.search;

import java.util.Objects;

/**
 * This class provides a method to perform a binary search on a sorted array to find the last occurrence of a target element.
 * It is a utility class and cannot be instantiated.
 */
public final class LastOccurrenceBinarySearch {

    private LastOccurrenceBinarySearch() {
    }

    /**
     * Searches for the last occurrence of the target element in the sorted array.
     *
     * @param array  the sorted array to search
     * @param target the target element to find
     * @param <T>    the type of elements in the array, which must be comparable
     * @return the index of the last occurrence of the target element, or -1 if not found
     * @throws NullPointerException if the array is null
     */
    public static <T extends Comparable<? super T>>int search(T[] array, T target) {

        Objects.requireNonNull(
                array,
                "Array cannot be null"
        );

        int low = 0;
        int high = array.length - 1;

        int result = -1;

        while (low <= high) {

            int middle = low + (high - low) / 2;

            int comparison =array[middle].compareTo(target);

            if (comparison == 0) {

                result = middle;

                /*
                 * Continue searching right
                 */
                low = middle + 1;
            }
            else if(comparison < 0) {

               low = middle + 1;
            }
            else {

                high = middle - 1;
            }
       }

        return result;
   }
}