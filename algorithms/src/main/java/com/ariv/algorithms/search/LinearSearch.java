package com.ariv.algorithms.search;

import java.util.Objects;

public final class LinearSearch {

    private LinearSearch() {
    }

    /**
     * Returns the index of the target.
     *
     * Returns -1 when not found.
     */
    public static <T> int search(
            T[] array,
            T target) {

        Objects.requireNonNull(
                array,
                "Array cannot be null"
        );

        for (int index = 0;
             index < array.length;
             index++) {

            if (Objects.equals(
                    array[index],
                    target)) {

                return index;
            }
        }

        return -1;
    }
}