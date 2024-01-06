package problem.prefix;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * You are given an array colors, in which there are three colors: 1, 2 and 3.
 * 
 * You are also given some queries. Each query consists of two integers i and c,
 * return the shortest distance between the given index i and the target color
 * c. If there is no solution return -1.
 * 
 * Example 1:
 * 
 * Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]] Output:
 * [3,0,3] 
 * 
 * Explanation: The nearest 3 from index 1 is at index 4 (3 steps away).
 * The nearest 2 from index 2 is at index 2 itself (0 steps away). The nearest 1
 * from index 6 is at index 3 (3 steps away).
 *
 */
public class Solution1182 {
	public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
		int n = colors.length;
		long[][] right = new long[n][3];

		for (int i = 0; i < n; i++) {
			int col = colors[i] - 1;
			right[i][col] = 0;
			for (int c = 0; c < 3; c++) {
				if (c != col) {
					right[i][c] = i - 1 >= 0 ? right[i - 1][c] + 1l : Integer.MAX_VALUE;
				}
			}
		}

		long[][] left = new long[n][3];

		for (int i = n - 1; i >= 0; i--) {
			int col = colors[i] - 1;
			left[i][col] = 0;
			for (int c = 0; c < 3; c++) {
				if (c != col) {
					left[i][c] = i + 1 < n ? left[i + 1][c] + 1l : Integer.MAX_VALUE;
				}
			}
		}

		int m = queries.length;
		List<Integer> ans = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			int[] query = queries[i];
			int pos = query[0];
			int col = query[1] - 1;
			long res = Math.min(left[pos][col], right[pos][col]);
			if (res >= Integer.MAX_VALUE) {
				res = -1;
			}
			ans.add((int) res);
		}
		return ans;

	}

}
