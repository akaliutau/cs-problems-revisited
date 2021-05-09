package problem.game;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be
 * validated according to the following rules:
 * 
 * Each row must contain the digits 1-9 without repetition. Each column must
 * contain the digits 1-9 without repetition. Each of the nine 3 x 3 sub-boxes
 * of the grid must contain the digits 1-9 without repetition.
 * 
 * 
 */
public class Solution36 {

	public boolean isValidSudoku(char[][] board) {
		// check rows
		boolean[][] rowMap = new boolean[9][9];// 9 blocks of 9 different numbers
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int col = (int) board[i][j] - '0' - 1;
					if (rowMap[i][col]) {
						return false;
					}
					rowMap[i][col] = true;
				}
			}
		}

		// check columns
		boolean[][] colMap = new boolean[9][9];
		for (int i = 0; i < 9; i++) {// column
			for (int j = 0; j < 9; j++) {
				if (board[j][i] != '.') {
					int row = (int) board[j][i] - '0' - 1;
					if (colMap[row][i]) {
						return false;
					}
					colMap[row][i] = true;
				}
			}
		}

		boolean[][] boxMap = new boolean[9][9];
		// check sub-bocies
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int col = (int) board[i][j] - '0' - 1;
					int box = 3 * (i / 3 - 1) + (j / 3 - 1) + 4;
					if (boxMap[box][col]) {
						return false;
					}
					boxMap[box][col] = true;
				}
			}
		}

		return true;

	}
}
