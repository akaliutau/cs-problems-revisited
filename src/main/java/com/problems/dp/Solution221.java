package com.problems.dp;

/**
 * 
 * DP
 * 
 * Build solution from blocks 2x2
 */
public class Solution221 {

	static int dp(int[][] dp, int i, int j) {
		if (i >= 0 && j >= 0) {
			return dp[i][j];
		}
		return 0;
	}

	public int maximalSquare(char[][] matrix) {
		int rows = matrix.length;
		int cols = 0;
		if (rows > 0) {
			cols = matrix[0].length;
		}
		int[][] dp = new int[rows][cols];
		int size = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				dp[i][j] = matrix[i][j] - '0';// 1 || 0
				if (dp[i][j] == 1) {
					// == [number of 1 in col/row] + cur status
					// min method will guarantee the reset on any bigger than 2x2 square
					dp[i][j] = Math.min(dp(dp, i - 1, j), Math.min(dp(dp, i, j - 1), dp(dp, i - 1, j - 1))) + 1;
					size = Math.max(size, dp[i][j]);
				}
			}
		}
		return size * size;

	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}