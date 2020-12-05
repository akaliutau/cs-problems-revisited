package com.problems.dp;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below). The robot can only move either down or right at any point
 * in time. The robot is trying to reach the bottom-right corner of the grid
 * (marked 'Finish' in the diagram below). Now consider if some obstacles are
 * added to the grids. How many unique paths would there be?
 * 
 * IDEA:
 * 
 */
public class Solution63 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;

        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] dp = new int[n][m];
        boolean obstacle = false;
        for (int i = 0; i < m; i++) {// first row
            obstacle = obstacle || obstacleGrid[0][i] == 1;
            dp[0][i] = obstacle ? 0 : 1;// if obstacle path blocked forever
        }
        obstacle = false;
        for (int i = 0; i < n; i++) {// first column
            obstacle = obstacle || obstacleGrid[i][0] == 1;
            dp[i][0] = obstacle ? 0 : 1;
        }
        for (int row = 1; row < n; row++) {
            for (int col = 1; col < m; col++) {
                int left = obstacleGrid[row][col - 1] == 1 ? 0 : dp[row][col - 1];// take # of paths from prev cell
                int up = obstacleGrid[row - 1][col] == 1 ? 0 : dp[row - 1][col];
                if (obstacleGrid[row][col] == 0) {
                    dp[row][col] = left + up;
                }
            }
        }

        return dp[n - 1][m - 1];

    }

}
