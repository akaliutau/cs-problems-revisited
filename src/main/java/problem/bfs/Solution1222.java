package problem.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * On an 8x8 chessboard, there can be multiple Black Queens and one White King.
 * 
 * Given an array of integer coordinates queens that represents the positions of
 * the Black Queens, and a pair of coordinates king that represent the position
 * of the White King, return the coordinates of all the queens (in any order)
 * that can attack the King.
 * 
 * Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0] Output:
 * [[0,1],[1,0],[3,3]]
 * 
 * Explanation:
 * 
 * The queen at [0,1] can attack the king cause they're in the same row.
 * 
 * The queen at [1,0] can attack the king cause they're in the same column.
 * 
 * The queen at [3,3] can attack the king cause they're in the same diagnal.
 * 
 * The queen at [0,4] can't attack the king cause it's blocked by the queen at
 * [0,1].
 * 
 * The queen at [4,0] can't attack the king cause it's blocked by the queen at
 * [1,0].
 * 
 * The queen at [2,4] can't attack the king cause it's not in the same
 * row/column/diagonal as the king.
 * 
 * IDEA:
 * 
 * start layered BFS with seed point at king, 
 * and try to spot the queens using direct rays starting from king
 *  
 * 
 */
public class Solution1222 {
	static int[] dirs = { 0, -1, 0, 1, -1, 0, 1, 0, -1, -1, 1, 1, 1, -1, -1, 1 };

	private static boolean valid(int x, int y) {
		return x >= 0 && x < 8 && y >= 0 && y < 8;
	}

	public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
		List<List<Integer>> res = new ArrayList<>();
		int n = queens.length;
		if (n == 0) {
			return res;
		}
		boolean[][] qPosition = new boolean[64][64];
		for (int[] queen : queens) {
			qPosition[queen[0]][queen[1]] = true;
		}
		boolean[] invalidDirections = new boolean[8];
		for (int k = 1; k < 8; k++) {// layer number
			for (int d = 0; d < 8; d++) {
				if (!invalidDirections[d]) {
					int x = king[0] + k * dirs[2 * d];
					int y = king[1] + k * dirs[2 * d + 1];
					if (valid(x, y) && qPosition[x][y]) {
						res.add(Arrays.asList(x, y));
						invalidDirections[d] = true;
					}
				}
			}

		}

		return res;
	}

}
