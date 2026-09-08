package com.ariv.dsa.problemsolving.search;

import java.util.Objects;

public final class FirstOccurrenceBinarySearch {

    private FirstOccurrenceBinarySearch() {
    }

    public static <
            T extends Comparable<? super T>>
    int search(
            T[] array,
            T target) {

        Objects.requireNonNull(
                array,
                "Array cannot be null"
        );

        int low = 0;
        int high = array.length - 1;

        int result = -1;

        while (low <= high) {

            int middle =
                    low + (high - low) / 2;

            int comparison =
                    array[middle]
                            .compareTo(target);

            if (comparison == 0) {

                result = middle;

                /*
                 * Continue left
                 */
                high = middle - 1;
            }
            else if (comparison < 0) {

                low = middle + 1;
            }
            else {

                high = middle - 1;
            }
        }

        return result;
    }
}