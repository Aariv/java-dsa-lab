package com.ariv.algorithms.recursion;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PermutationsTest {

    @Test
    void shouldGeneratePermutationForEmptyArray() {

        List<List<Integer>> result =
                Permutations.generate(
                        new Integer[]{}
                );

        assertEquals(1, result.size());

        assertTrue(result.contains(List.of()));
    }

    @Test
    void shouldGenerateSinglePermutation() {

        List<List<Integer>> result =
                Permutations.generate(
                        new Integer[]{1}
                );

        assertEquals(1, result.size());

        assertTrue(
                result.contains(
                        List.of(1)
                )
        );
    }

    @Test
    void shouldGenerateTwoPermutations() {

        List<List<Integer>> result =
                Permutations.generate(
                        new Integer[]{1,2}
                );

        assertEquals(2, result.size());

        assertTrue(
                result.contains(
                        List.of(1,2)
                )
        );

        assertTrue(
                result.contains(
                        List.of(2,1)
                )
        );
    }

    @Test
    void shouldGenerateSixPermutations() {

        List<List<Integer>> result =
                Permutations.generate(
                        new Integer[]{1,2,3}
                );

        assertEquals(
                6,
                result.size()
        );
    }

    @Test
    void shouldRejectNullInput() {

        assertThrows(
                NullPointerException.class,
                () -> Permutations.generate(null)
        );
    }

}
