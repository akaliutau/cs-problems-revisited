package problem.priorityqueue;

import java.util.Arrays;

/**
 * You are given a 0-indexed integer array nums and an integer k.
 * 
 * You are initially standing at index 0. In one move, you can jump at most k
 * steps forward without going outside the boundaries of the array. That is, you
 * can jump from index i to any index in the range [i + 1, min(n - 1, i + k)]
 * inclusive.
 * 
 * You want to reach the last index of the array (index n - 1). Your dp is
 * the sum of all nums[j] for each index j you visited in the array.
 * 
 * Return the maximum dp you can get.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,-1,-2,4,-7,3], k = 2 Output: 7 
 *                -  -    -    -
 *                
 * Explanation: You can choose
 * your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is
 * 7.
 * 
 * IDEA:
 * NOTE: this solution is not optimal one (results in TLE error)
 * 
 *   [1,-1,-2,4,-7,3]  
 *       0 -1
 *         -1 4
 *            4 -8
 *              -3 7    
 */
public class Solution1696a {
	public int maxResult(int[] nums, int k) {
		int n = nums.length;
		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MIN_VALUE);
		if (k == 0) {
			return 0;
		}
		dp[0] = nums[0];
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= i + k && j < n; j++) {
				dp[j] = Math.max(dp[j], nums[j] + dp[i]);
			}
		}
		return dp[n - 1];
	}
}
