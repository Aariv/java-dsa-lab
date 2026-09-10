package com.ariv.algorithms.recursion;

import java.util.Objects;

/**
 * This class provides a method to determine if there is a path from the start cell to the end cell in a maze.
 * The maze is represented as a 2D character array, where 'S' is the start cell, 'E' is the end cell, and 'X' represents walls.
 * The method uses depth-first search (DFS) to explore possible paths in the maze.
 */
public final class MazeTraversal {

    private static final char START = 'S';
    private static final char END = 'E';
    private static final char WALL = 'X';

    private MazeTraversal() {
    }

    /**
     * Determines if there is a path from the start cell to the end cell in the maze.
     *
     * @param maze a 2D character array representing the maze
     * @return true if a path exists, false otherwise
     * @throws NullPointerException if the maze is null
     * @throws IllegalArgumentException if the maze does not contain a start cell
     */
    public static boolean hasPath(char[][] maze) {

        Objects.requireNonNull(maze, "Maze cannot be null");

        int[] start = findStart(maze);

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        return dfs(maze, start[0], start[1], visited);
    }

    /**
     * Performs a depth-first search (DFS) to explore the maze and find a path from the current cell to the end cell.
     *
     * @param maze    a 2D character array representing the maze
     * @param row     the current row index
     * @param column  the current column index
     * @param visited a 2D boolean array to track visited cells
     * @return true if a path to the end cell is found, false otherwise
     */
    private static boolean dfs(char[][] maze, int row, int column, boolean[][] visited) {

        if (!isValid(maze, row, column)) {
            return false;
        }

        if (visited[row][column]) {
            return false;
        }

        if (maze[row][column] == WALL) {
            return false;
        }

        if (maze[row][column] == END) {
            return true;
        }

        visited[row][column] = true;

        return dfs(maze, row - 1, column, visited)
                ||
                dfs(maze, row + 1, column, visited)
                ||
                dfs(maze, row, column - 1, visited)
                ||
                dfs(maze, row, column + 1, visited);
    }

    /**
     * Finds the coordinates of the start cell in the maze.
     *
     * @param maze a 2D character array representing the maze
     * @return an array containing the row and column indices of the start cell
     * @throws IllegalArgumentException if the maze does not contain a start cell
     */
    private static int[] findStart(char[][] maze) {

        for (int row = 0; row < maze.length; row++) {

            for (int column = 0; column < maze[row].length; column++) {

                if (maze[row][column] == START) {
                    return new int[]{row, column};
                }
            }
        }

        throw new IllegalArgumentException(
                "Maze must contain a start cell"
        );
    }

    private static boolean isValid(char[][] maze, int row, int column) {
        return row >= 0 && row < maze.length && column >= 0 && column < maze[0].length;
    }
}