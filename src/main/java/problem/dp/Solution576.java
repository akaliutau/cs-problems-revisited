package problem.dp;

import java.util.Arrays;

/**
 * 
 * There is an m x n grid with a ball. The ball is initially at the position
 * [startRow, startColumn]. You are allowed to move the ball to one of the four
 * adjacent four cells in the grid (possibly out of the grid crossing the grid
 * boundary). You can apply at most maxMove moves to the ball.
 * 
 * Given the five integers m, n, maxMove, startRow, startColumn, return the
 * number of paths to move the ball out of the grid boundary. Since the answer
 * can be very large, return it modulo 109 + 7.
 * 
 * IDEA:
 * when we shift position (i,j) to (i-1,j) we get the subproblem with moves-1
 * 
 * 1. use recursive function findPaths to sum up all paths
 * 
 * O(m * n * l), where l = limit of moves
 * 
 */
public class Solution576 {
	int M = 1_000_000_007;

	long findPaths(int m, int n, int moves, int i, int j, int[][][] memo) {
		if (i == m || j == n || i < 0 || j < 0) // we detected shifting outside area - count it!
			return 1;
		
		if (moves == 0) // end of game - as a result all terminal cells will be marked by 0 in memo table
			return 0;
		
		if (memo[i][j][moves] >= 0)
			return memo[i][j][moves];
		
		long res = (
				findPaths(m, n, moves - 1, i - 1, j, memo) + 
				findPaths(m, n, moves - 1, i + 1, j, memo) + 
				findPaths(m, n, moves - 1, i, j - 1, memo) + 
				findPaths(m, n, moves - 1, i, j + 1, memo)) % M;
		
		memo[i][j][moves] = (int) res;
		
		return memo[i][j][moves];
	}

	public int findPaths(int m, int n, int moves, int i, int j) {
		int[][][] memo = new int[m][n][moves + 1];
		for (int[][] l : memo) {
			for (int[] sl : l) {
				Arrays.fill(sl, -1);
			}
		}
		return (int) findPaths(m, n, moves, i, j, memo);
	}

}
