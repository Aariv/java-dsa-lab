package com.ariv.algorithms.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MazeTraversalTest {

    @Test
    void shouldFindPath() {

        char[][] maze = {
                {'S', '.', '.'},
                {'X', '.', 'X'},
                {'.', '.', 'E'}
        };

        assertTrue(MazeTraversal.hasPath(maze));
    }

    @Test
    void shouldReturnFalseWhenPathBlocked() {

        char[][] maze = {
                {'S', 'X'},
                {'X', 'E'}
        };

        assertFalse(
                MazeTraversal.hasPath(maze)
        );
    }

    @Test
    void shouldRejectMazeWithoutStart() {

        char[][] maze = {
                {'.', '.'},
                {'.', 'E'}
        };

        assertThrows(
                IllegalArgumentException.class,
                () -> MazeTraversal.hasPath(maze)
        );
    }
}
