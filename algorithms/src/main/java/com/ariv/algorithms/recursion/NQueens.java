package com.ariv.algorithms.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * The N-Queens problem is a classic backtracking problem that involves placing N queens on an N x N chessboard such that no two queens threaten each other. In chess, a queen can attack another piece if it is in the same row, column, or diagonal. The goal of the N-Queens problem is to find all possible arrangements of N queens on the board where they do not threaten each other.
 *
 * The solution to the N-Queens problem can be found using a backtracking algorithm. The algorithm starts by placing a queen in the first row and then recursively attempts to place queens in subsequent rows while checking for conflicts with previously placed queens. If a conflict arises, the algorithm backtracks and tries a different position for the queen in the previous row.
 *
 * The N-Queens problem has many applications in computer science, including constraint satisfaction problems, combinatorial optimization, and artificial intelligence. It is also a popular problem for teaching recursion and backtracking techniques.
 */
public final class NQueens {

    private static final char QUEEN = 'Q';
    private static final char EMPTY = '.';

    private NQueens() {
    }

    /**
     * Solves the N-Queens problem for a given board size.
     *
     * @param boardSize the size of the chessboard (N)
     * @return a list of all possible solutions, where each solution is represented as a list of strings
     * @throws IllegalArgumentException if the board size is less than or equal to zero
     */
    public static List<List<String>> solve(int boardSize) {

        if (boardSize <= 0) {
            throw new IllegalArgumentException(
                    "Board size must be greater than zero"
            );
        }

        char[][] board = createBoard(boardSize);

        List<List<String>> solutions = new ArrayList<>();

        backtrack(board, 0, solutions);

        return solutions;
    }

    /**
     * Backtracking function to find all possible arrangements of N queens on the chessboard.
     *
     * @param board     the current state of the chessboard
     * @param row       the current row to place a queen
     * @param solutions the list to store all valid solutions
     */
    private static void backtrack(char[][] board, int row, List<List<String>> solutions) {
        if (row == board.length) {
            solutions.add(buildSolution(board));
            return;
        }

        for (int column = 0; column < board.length; column++) {
            if (!isSafe(board, row, column)) {
                continue;
            }

            board[row][column] = QUEEN;

            backtrack(board, row + 1, solutions);

            board[row][column] = EMPTY;
        }
    }

    /**
     * Checks if it is safe to place a queen at the given position on the chessboard.
     *
     * @param board  the current state of the chessboard
     * @param row    the row index of the position to check
     * @param column the column index of the position to check
     * @return true if it is safe to place a queen at the given position, false otherwise
     */
    private static boolean isSafe(char[][] board, int row, int column) {
        /*
         * Column
         */
        for (int currentRow = 0; currentRow < row; currentRow++) {
            if (board[currentRow][column] == QUEEN) {
                return false;
            }
        }

        /*
         * Upper Left Diagonal
         */
        for (int currentRow = row - 1, currentColumn = column - 1; currentRow >= 0 && currentColumn >= 0;
             currentRow--,
             currentColumn--) {
            if (board[currentRow][currentColumn] == QUEEN) {

                return false;
            }
        }

        /*
         * Upper Right Diagonal
         */
        for (int currentRow = row - 1,
             currentColumn = column + 1;

             currentRow >= 0 && currentColumn < board.length;

             currentRow--,
             currentColumn++) {

            if (board[currentRow][currentColumn] == QUEEN) {
                return false;
            }
        }

        return true;
    }

    /**
     * Builds a solution representation from the current state of the chessboard.
     *
     * @param board the current state of the chessboard
     * @return a list of strings representing the solution
     */
    private static List<String> buildSolution(char[][] board) {

        List<String> solution = new ArrayList<>();

        for (char[] row : board) {
            solution.add(new String(row));
        }

        return solution;
    }

    /**
     * Creates an empty chessboard of the specified size.
     *
     * @param boardSize the size of the chessboard (N)
     * @return a 2D character array representing the empty chessboard
     */
    private static char[][] createBoard(int boardSize) {

        char[][] board = new char[boardSize][boardSize];

        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                board[row][column] = EMPTY;
            }
        }

        return board;
    }
}