package problem.design;

/**
 * Assume the following rules are for the tic-tac-toe game on an n x n board
 * between two players:
 * 
 * A move is guaranteed to be valid and is placed on an empty block. Once a
 * winning condition is reached, no more moves are allowed. A player who
 * succeeds in placing n of their marks in a horizontal, vertical, or diagonal
 * row wins the game. Implement the TicTacToe class:
 * 
 * TicTacToe(int n) Initializes the object the size of the board n. int move(int
 * row, int col, int player) Indicates that player with id player plays at the
 * cell (row, col) of the board. The move is guaranteed to be a valid move.
 * 
 * IDEA:
 * each player either increase by 1 the cell or descrease it
 * if some row| column | diag  reach sum with norm == ||n|| someone won
 * 
 */
public class Solution348 {

	class TicTacToe {
		int[] rSum;
		int[] cSum;
		int diagSum;
		int antidiagSum;
		int n;

		public TicTacToe(int n) {
			rSum = new int[n];
			cSum = new int[n];
			diagSum = 0;
			antidiagSum = 0;
			this.n = n;
		}

		/**
		 * Player {player} makes a move at ({row}, {col}).
		 * 
		 * @param row    The row of the board.
		 * @param col    The column of the board.
		 * @param player The player, can be either 1 or 2.
		 * @return The current winning condition, can be either: 
		 *  0: No one wins. 
		 *  1: Player 1 wins. 
		 *  2: Player 2 wins.
		 */
		public int move(int row, int col, int player) {
			int num = player == 1 ? 1 : -1;
			rSum[row] += num;
			cSum[col] += num;
			if (row == col) {
				diagSum += num;
			}
			if (row + col == n - 1) {
				antidiagSum += num;
			}
			if (rSum[row] == n || cSum[col] == n || diagSum == n || antidiagSum == n) {
				return 1;
			} else if (rSum[row] == -n || cSum[col] == -n || diagSum == -n || antidiagSum == -n) {
				return 2;
			} else {
				return 0;
			}
		}
	}


}
