package problem.game;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be
 * validated according to the following rules:
 * 
 * Each row must contain the digits 1-9 without repetition. Each column must
 * contain the digits 1-9 without repetition. Each of the nine 3 x 3 sub-boxes
 * of the grid must contain the digits 1-9 without repetition.
 * 
 * IDEA:
 * 1. check every single row contains unique numbers
 * 2. check every single column contains unique numbers
 * 3. check every box 3x3 contains unique numbers
 */
public class Solution36 {

    public boolean isValidSudoku(char[][] board) {
		// check rows
		boolean[][] rowMap = new boolean[9][9];// 9 blocks of 9 different numbers

		// check columns
		boolean[][] colMap = new boolean[9][9];

		// check sub-bocies
		boolean[][] boxMap = new boolean[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int num = (int) board[i][j] - '0' - 1;
					if (rowMap[i][num]) {// vector for row i
						return false;
					}
					rowMap[i][num] = true;
                }
				if (board[j][i] != '.') {
					int num = (int) board[j][i] - '0' - 1;
					if (colMap[i][num]) {// vector for column i
						return false;
					}
					colMap[i][num] = true;
                }
				if (board[i][j] != '.') {
					int num = (int) board[i][j] - '0' - 1;
					int box = 3 * (i / 3) + j / 3 ; // id for specific box
					if (boxMap[box][num]) {// vector for box
						return false;
					}
					boxMap[box][num] = true;
				}
			}
		}
		return true;

	}
}
