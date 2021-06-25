package problem.backtracking;

/**
 * 
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cells,
 * where "adjacent" cells are horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * 
 * IDEA:
 *  start at any point of board and investigate in all directions
 * 
 */
public class Solution79 {

	int[] rowOffsets = { 0, 1, 0, -1 };
	int[] colOffsets = { 1, 0, -1, 0 };

	public boolean backtrack(char[][] board, int row, int col, int rows, int cols, String word, int index) {
		// exit condition for the case of found word
		if (index >= word.length()) {
			return true;
		}
		
		// exit condition for the case if smth went wrong
		if (row < 0 || row == rows || col < 0 || col == cols || board[row][col] != word.charAt(index)) {
			return false;
		}

		boolean found = false;
		// backtracking: mark the path before the next exploration in order to avoid repetition
		char curLet = word.charAt(index);
		board[row][col] = '#';

		for (int d = 0; d < 4; ++d) {
			found = backtrack(board, row + rowOffsets[d], col + colOffsets[d], rows, cols, word, index + 1);
			if (found) {
				break;
			}
		}

		board[row][col] = curLet;
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


}
