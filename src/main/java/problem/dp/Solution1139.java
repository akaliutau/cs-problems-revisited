package problem.dp;

/**
 * Given a 2D rid of 0s and 1s, return the number of elements in the largest
 * square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't
 * exist in the grid.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]] Output: 9
 * 
 */
public class Solution1139 {

	public int largest1BorderedSquare(int[][] grid) {
		int res = 0, m = grid.length, n = grid[0].length;
		int[][][] dp = new int[m + 1][n + 1][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                	continue;
                }
                dp[i+1][j+1][0] += dp[i][j+1][0] + 1;
                dp[i+1][j+1][1] += dp[i+1][j][1] + 1;
                int pre = Math.min(dp[i+1][j+1][0], dp[i+1][j+1][1]);
                if (pre <= res) {
                	continue;
                }
                for (int k = pre; k > res; k--) {
                    if (dp[i + 1 - k + 1][j + 1][1] >= k && dp[i + 1][j + 1 - k + 1][0] >= k) {
                        res = k;
                        break;
                    }
                }
            }
        }
        return res * res;
	}

}
