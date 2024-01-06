package problem.backtracking;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * A sudoku solution must satisfy all of the following rules:
 * 
 * Each of the digits 1-9 must occur exactly once in each row. Each of the
 * digits 1-9 must occur exactly once in each column. Each of the digits 1-9
 * must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * 
 * 
 */
public class Solution37 {

	// sub box size
	int n = 3;
	// row size
	int dim = n * n;

	int[][] rows = new int[dim][dim + 1];
	int[][] columns = new int[dim][dim + 1];
	int[][] boxes = new int[dim][dim + 1];

	char[][] board;

	boolean sudokuSolved = false;

	public boolean couldPlace(int d, int row, int col) {
		/*
		 * Check if one could place a number d in (row, col) cell
		 */
		int idx = (row / n) * n + col / n;
		// the same number shouldn't appear in 1) row 2)column 3) box
		return rows[row][d] + columns[col][d] + boxes[idx][d] == 0;
	}

	public void placeNumber(int d, int row, int col) {
		/*
		 * Place a number d in (row, col) cell
		 */
		int idx = (row / n) * n + col / n;

		rows[row][d]++;
		columns[col][d]++;
		boxes[idx][d]++;
		board[row][col] = (char) (d + '0');
	}

	public void removeNumber(int d, int row, int col) {
		/*
		 * Remove a number which didn't lead to a solution
		 */
		int idx = (row / n) * n + col / n;
		rows[row][d]--;
		columns[col][d]--;
		boxes[idx][d]--;
		board[row][col] = '.';
	}

	public void placeNextNumbers(int row, int col) {
		/*
		 * Call backtrack function in recursion to continue to place numbers till the
		 * moment we have a solution
		 */
		// if we're in the last cell
		// that means we have the solution
		if ((col == dim - 1) && (row == dim - 1)) {
			sudokuSolved = true;
		}
		// if not yet
		else {
			if (col == dim - 1) {
				backtrack(row + 1, 0);
			}else {
				backtrack(row, col + 1);
			}
		}
	}

	public void backtrack(int row, int col) {
		/*
		 * Backtracking
		 */
		// if the cell is empty
		if (board[row][col] == '.') {
			// iterate over all numbers from 1 to 9
			for (int d = 1; d < 10; d++) {
				if (couldPlace(d, row, col)) {
					placeNumber(d, row, col);
					placeNextNumbers(row, col);
					// if sudoku is solved, there is no need to backtrack
					// since the single unique solution is promised
					if (!sudokuSolved)
						removeNumber(d, row, col);
				}
			}
		} else {
			placeNextNumbers(row, col);
		}
	}

	public void solveSudoku(char[][] board) {
		this.board = board;

		// init rows, columns and boxes
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				char num = board[i][j];
				if (num != '.') {
					int d = Character.getNumericValue(num);
					placeNumber(d, i, j);
				}
			}
		}
		backtrack(0, 0);
	}

}
