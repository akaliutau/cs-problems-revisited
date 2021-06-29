package problem.game;

/**
 * 
 * Given a Tic-Tac-Toe board as a string array board, return true if and only if
 * it is possible to reach this board position during the course of a valid
 * tic-tac-toe game.
 * 
 * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The
 * ' ' character represents an empty square.
 * 
 * Here are the rules of Tic-Tac-Toe:
 * 
 * Players take turns placing characters into empty squares ' '. The first
 * player always places 'X' characters, while the second player always places
 * 'O' characters. 'X' and 'O' characters are always placed into empty squares,
 * never filled ones. The game ends when there are three of the same (non-empty)
 * character filling any row, column, or diagonal. The game also ends if all
 * squares are non-empty. No more moves can be played if the game is over.
 * 
 * IDEA: check 4 conditions: 1) numberOfX >= numberOfO && numberOfX - numberOfO
 * <= 1 2) no 2 sums == 3 for rows of X or O
 * 
 * ["XXX",
 *  "XOO",
 *  "OO "]
 */
public class Solution794 {
	public boolean validTicTacToe(String[] board) {
		int[][] bd = new int[3][3];
		int countO = 0;
		int countX = 0;
		for (int i = 0; i < 3; i++) {
			String row = board[i];
			for (int j = 0; j < 3; j++) {
				bd[i][j] = row.charAt(j) == 'O' ? -1 : row.charAt(j) == 'X' ? 1 : 0;
				if (bd[i][j] == -1) {
					countO++;
				} else if (bd[i][j] == 1) {
					countX++;
				}
			}
		}
		if (countX >= countO && countX - countO <= 1) {
			int winnerX = 0;
			int winnerO = 0;
			for (int i = 0; i < 3; i++) {
				int sum = 0, sumv = 0;
				;
				for (int j = 0; j < 3; j++) {
					sum += bd[i][j];
					sumv += bd[j][i];
				}
				if (sum == -3 || sumv == -3) {
					winnerO++;
				}
				if (sum == 3 || sumv == 3) {
					winnerX++;
				}
			}
			int diag1 = 0;
			int diag2 = 0;
			for (int i = 0; i < 3; i++) {
				diag1 += bd[i][i];
				diag2 += bd[2 - i][i];
			}
			if (diag1 == -3 || diag2 == -3) {
				winnerO++;
			}
			if (diag1 == 3 || diag2 == 3) {
				winnerX++;
			}

			if ((winnerO > 0 && winnerX == 0 && countO == countX) || (winnerO == 0 && winnerX > 0 && countO < countX) || (winnerX == 0 && winnerO == 0)) {
				return true;
			} 
			
		}
		return false;

	}

}
