package com.problems.backtracking;

/**
 * 
 * Backtracking
 * 
 */
public class Solution79 {

	int[] rowOffsets = { 0, 1, 0, -1 };
	int[] colOffsets = { 1, 0, -1, 0 };


	public boolean backtrack(char[][] board, int row, int col, int rows, int cols, String word, int index) {
		if (index >= word.length()) {
			return true;
		}

		if (row < 0 || row == rows || col < 0 || col == cols || board[row][col] != word.charAt(index)) {
			return false;
		}

		boolean found = false;
		// mark the path before the next exploration
		board[row][col] = '#';

		for (int d = 0; d < 4; ++d) {
			found = backtrack(board, row + rowOffsets[d], col + colOffsets[d], rows, cols, word, index + 1);
			if (found) {
				break;
			}
		}

		board[row][col] = word.charAt(index);
		return found;
	}


	boolean exist(char[][] board, String word) {
		int rows = board.length;
		int cols = 0;
		if (rows > 0) {
			cols = board[0].length;
		}

		for (int row = 0; row < rows; ++row) {
			for (int col = 0; col < cols; ++col) {
				if (backtrack(board, row, col, rows, cols, word, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
