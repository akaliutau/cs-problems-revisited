package problem.matrix;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given a matrix and a target, return the number of non-empty submatrices that
 * sum to target.
 * 
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x
 * <= x2 and y1 <= y <= y2.
 * 
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if
 * they have some coordinate that is different: for example, if x1 != x1'.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix = 
 * [ 
 * [0,1,0], 
 * [1,1,1], 
 * [0,1,0] 
 * ], target = 0 Output: 4
 * 
 * Diagonal sum:
 * [5,4,1], 
 * [4,3,1], 
 * [1,1,0]
 * 
 * 
 * 
 * 
 * Explanation: The four 1x1 submatrices that only contain 0.
 * 
 * IDEA:
 * 
 * BF: one-time scan cost - O(n^2) Total time complexity O(n^4 X n^2) ~ O(n^6)
 * 
 * Use pre-calculated 2D sums for each (i,j)
 * preSum[j][i] == sum of all elements which are higher than row=i at column j
 * (obviously for the 1st row all sums = 0)
 * 
 * [0,1,0] -\    [0,0,0]
 * [1,1,1]   \-  [0,1,0]
 * [0,1,0]       [1,2,1]
 *               [1,3,1]
 *               
 * check all sqs:
 * 
 * #  ##  ###
 * 
 * #  ##  ###
 * #  ##  ###
 * 
 * 
 * 
 */
public class Solution1074 {

	public int numSubmatrixSumTarget(int[][] matrix, int target) {
		int n = matrix.length;
		int m = matrix[0].length;
		int[][] preSum = new int[n + 1][m];

		// calculating pre-sums
		// preSum[j][i]
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				preSum[j + 1][i] = preSum[j][i] + matrix[j][i];
			}
		}

		int result = 0;
		Map<Integer, Integer> map = new HashMap<>();

		// for each row range [i,j] for specific column:
		// 1) reset the map
		// 2) iterate through all columns
		for (int top = 0; top < n; top++) {
			for (int bottom = top; bottom < n; bottom++) {// all points () which are higher than diagonal
				map.clear();// reset
				int area = 0;
				for (int col = 0; col < m; col++) {
					// at this point j is always >=i
					area += preSum[bottom + 1][col] - preSum[top][col];// sum for the column [j+1 -- i][k]

					// try to build a square with top-bottom between [i,j+1)
					if (area == target) {// if hit, update result counter
						result++;
					}

					int delta = area - target;
					if (map.containsKey(delta)) {// in order to avoid O(n^2) for all columns pairs - NOTE: map is reset on each top-bottom change!
						result += map.get(delta);
					}
					map.compute(area, (u, v) -> v == null ? 1 : v + 1);
				}
			}
		}
		return result;
	}
}
