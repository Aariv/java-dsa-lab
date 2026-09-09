package com.ariv.algorithms.recursion;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubsetsTest {

    @Test
    void shouldGenerateSubsetForEmptyArray() {

        List<List<Integer>> result =
                Subsets.generate(
                        new Integer[]{}
                );

        assertEquals(
                1,
                result.size()
        );

        assertTrue(
                result.contains(
                        List.of()
                )
        );
    }

    @Test
    void shouldGenerateSubsetsForSingleElement() {

        List<List<Integer>> result =
                Subsets.generate(
                        new Integer[]{1}
                );

        assertEquals(2, result.size());

        assertTrue(result.contains(List.of()));
        assertTrue(result.contains(List.of(1)));
    }

    @Test
    void shouldGenerateSubsetsForTwoElements() {

        List<List<Integer>> result =
                Subsets.generate(
                        new Integer[]{1, 2}
                );

        assertEquals(4, result.size());

        assertTrue(result.contains(List.of()));
        assertTrue(result.contains(List.of(1)));
        assertTrue(result.contains(List.of(2)));
        assertTrue(result.contains(List.of(1, 2)));
    }

    @Test
    void shouldGenerateAllSubsets() {

        List<List<Integer>> result =
                Subsets.generate(
                        new Integer[]{1,2,3}
                );

        assertEquals(
                8,
                result.size()
        );
    }

    @Test
    void shouldRejectNullInput() {

        assertThrows(
                NullPointerException.class,
                () -> Subsets.generate(null)
        );
    }
}
