package com.ariv.problemsolving.patterns;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveDuplicatesSortedArrayTest {

    @Test
    void shouldRemoveDuplicates() {

        int[] values = {1,1,2,2,2,3,4,4};

        int uniqueCount = RemoveDuplicatesSortedArray.removeDuplicates(values);

        assertAll(
                () -> assertEquals(4, uniqueCount),

                () -> assertArrayEquals(
                        new int[]{1, 2, 3, 4}, Arrays.copyOf(values, uniqueCount)
                )
        );
    }
}
