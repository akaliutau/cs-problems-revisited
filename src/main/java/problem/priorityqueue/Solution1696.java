package problem.priorityqueue;

import java.util.PriorityQueue;

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
 * the most optimal sequence is that consisting from the biggest elements
 *  * 
 * [1,-1,-2,4,-7,3]
 *          |
 *    {   }look up the biggest elem in the range [i-k, i-1]
 */
public class Solution1696 {
	public int maxResult(int[] nums, int k) {
		int n = nums.length;
		int[] dp = new int[n];
		dp[0] = nums[0];
		
		// used to hold containers [(num), (its index)], where biggest ones are on the top
		// contains the biggest numbers in path seen so far
		PriorityQueue<int[]> pq = new PriorityQueue<>((o, p) -> p[0] - o[0]);
		pq.add(new int[] { nums[0], 0 });
		
		for (int i = 1; i < n; i++) {
			int tooFarTh = i - k; 
			while (pq.peek()[1] < tooFarTh) {// clean up queue from too distant elements
				pq.remove();
			}
			dp[i] = nums[i] + dp[pq.peek()[1]];
			pq.add(new int[] { dp[i], i });
		}
		return dp[n - 1];
	}
}
