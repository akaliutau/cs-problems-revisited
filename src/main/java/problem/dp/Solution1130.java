package problem.dp;

import java.util.Arrays;

/**
 * Given an array arr of positive integers, consider all binary trees such that:
 * 
 * Each node has either 0 or 2 children; The values of arr correspond to the
 * values of each leaf in an in-order traversal of the tree. (Recall that a node
 * is a leaf if and only if it has 0 children.) The value of each non-leaf node
 * is equal to the product of the largest leaf value in its left and right
 * subtree respectively. Among all possible binary trees considered, return the
 * smallest possible sum of the values of each non-leaf node. It is guaranteed
 * this sum fits into a 32-bit integer.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: arr = [6,2,4] Output: 32 
 * Explanation: There are two possible trees.
 * The first has non-leaf node sum 36, and the second has non-leaf node sum 32.
 * 
 *          24             24 
 *         /  \           /  \ 
 *        12   4         6    8 
 *       / \                 / \ 
 *      6   2               2   4
 * 
 */
public class Solution1130 {

	public int mctFromLeafValues(int[] arr) {
		int n = arr.length;
		// dp[i][j] smallest possible sum of the values for [i, j] subtree
		int[][] dp = new int[n][n];
		for (int[] row : dp) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		for (int i = 0; i < n; i++) {
			dp[i][i] = 0;
			if (i + 1 < n - 1) {
				dp[i][i + 1] = arr[i] * arr[i + 1];
			}
		}
		
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				for (int k = i; k < j; k++) {
					int max1 = 1;
					for (int p = i; p <= k; p++) {
						max1 = Math.max(max1, arr[p]);// max value in left branch
					}
					int max2 = 1;
					for (int p = k + 1; p <= j; p++) {
						max2 = Math.max(max2, arr[p]);// max value in right branch
					}
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + max1 * max2);
				}
			}
		}
		return dp[0][n - 1];
	}

	

}
