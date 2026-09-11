package com.ariv.algorithms.recursion;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NQueensTest {

    @Test
    void shouldSolveOneQueen() {

        List<List<String>> solutions =
                NQueens.solve(1);

        assertEquals(1, solutions.size());
    }

    @Test
    void shouldReturnTwoSolutionsForFourQueens() {

        List<List<String>> solutions =
                NQueens.solve(4);

        assertEquals(2, solutions.size());
    }

    @Test
    void shouldReturnNoSolutionForTwoQueens() {

        List<List<String>> solutions =
                NQueens.solve(2);

        assertTrue(
                solutions.isEmpty()
        );
    }

    @Test
    void shouldReturnNoSolutionForThreeQueens() {

        List<List<String>> solutions =
                NQueens.solve(3);

        assertTrue(
                solutions.isEmpty()
        );
    }

    @Test
    void shouldRejectInvalidBoardSize() {

        assertThrows(
                IllegalArgumentException.class,
                () -> NQueens.solve(0)
        );
    }
}
