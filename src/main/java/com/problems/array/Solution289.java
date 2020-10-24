package com.problems.array;

/**
 * According to the Wikipedia's article: "The Game of Life, also known simply as
 * Life, is a cellular automaton devised by the British mathematician John
 * Horton Conway in 1970." Given a board with m by n cells, each cell has an
 * initial state live (1) or dead (0). Each cell interacts with its eight
 * neighbors (horizontal, vertical, diagonal) using the following four rules
 * (taken from the above Wikipedia article): 
 * 
 * 1) Any live cell with fewer than two live neighbors dies, as if caused by under-population. 
 * 2) Any live cell with two or three live neighbors lives on to the next generation. 
 * 3) Any live cell with more than three live neighbors dies, as if by over-population.. 
 * 4) Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * 
 * IDEA: use newVal=2 and newVal-1 for transitions 0->1 and 1->0 appropriately 
 */
public class Solution289 {

    public void gameOfLife(int[][] board) {

        int[] neighbors = { 0, 1, -1 };

        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                // For each cell count the number of live neighbors.
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);

                            // Check the validity of the neighboring cell.
                            // and whether it was originally a live cell.        can be 1 (not processed) or -1 (processed with change)
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // Rule 1 or Rule 3
                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = -1;// 1 -> 0
                }
                // Rule 4
                if (board[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 2;// 0 -> 1
                }
            }
        }

        // clean up -1 and 2, -1 -> 0, 2 -> 1
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

}
