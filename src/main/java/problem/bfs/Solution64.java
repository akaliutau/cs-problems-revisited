package problem.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Example:
 * 
 * Input: 
 * [ 
 * [1,3,1], 
 * [1,5,1], 
 * [4,2,1] 
 * ] Output: 7 
 * Explanation: Because the path
 * 1->3->1->1->1 minimizes the sum
 * 
 * IDEA:
 * for each cell:
 *  1) calc path sum as cur_cell_val + grid_cell_val
 *  2) update next cell it to optimal value
 *  3) add to queue if and only if this cell was not processed previously
 */
public class Solution64 {

	public int minPathSum(int[][] grid) {
		int n = grid.length;
		int m = 0;
		if (n > 0) {
			m = grid[0].length;
		}
		if (n == 0 || m == 0) {
			return 0;
		}
		int[][] sum = new int[n][m];
		boolean[][] processed = new boolean[n][m];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 0, 0 });
		sum[0][0] = grid[0][0];
		while (!q.isEmpty()) {
			int[] cell = q.poll();
			int c = cell[1];
			int r = cell[0];

			if (c + 1 < m) {
				int sum1 = sum[r][c] + grid[r][c + 1];
				sum[r][c + 1] = sum[r][c + 1] == 0 ? sum1 : Math.min(sum[r][c + 1], sum1);// the second condition is in the case of 2nd path availability
				if (!processed[r][c + 1]) {
					q.add(new int[] { r, c + 1 });
					processed[r][c + 1] = true;
				}
			}
			if (r + 1 < n) {
				int sum1 = sum[r][c] + grid[r + 1][c];
				sum[r + 1][c] = sum[r + 1][c] == 0 ? sum1 : Math.min(sum[r + 1][c], sum1);
				if (!processed[r + 1][c]) {
					q.add(new int[] { r + 1, c });
					processed[r + 1][c] = true;
				}
			}
		}

		return sum[n - 1][m - 1];
	}

	

}
