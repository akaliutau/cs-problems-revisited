package com.problems.design;

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
 */
public class Solution348 {

	class TicTacToe {
		int[] r;
		int[] c;
		int diag;
		int antidiag;
		int n;

		public TicTacToe(int n) {
			r = new int[n];
			c = new int[n];
			diag = 0;
			antidiag = 0;
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
			r[row] += num;
			c[col] += num;
			if (row == col) {
				diag += num;
			}
			if (row + col == n - 1) {
				antidiag += num;
			}
			if (r[row] == n || c[col] == n || diag == n || antidiag == n) {
				return 1;
			} else if (r[row] == -n || c[col] == -n || diag == -n || antidiag == -n) {
				return 2;
			} else {
				return 0;
			}
		}
	}

	public static void main(String[] arg) {

		System.out.println();

	}

}
