package problem.dp;

import java.util.Arrays;

/**
 * Given an array nums which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays.
 * 
 * Write an algorithm to minimize the largest sum among these m subarrays.
 * 
 * Example 1:
 * 
 * Input: nums = [7,2,5,10,8], m = 2 Output: 18 
 * 
 * Explanation: There are four ways
 * to split nums into two subarrays. The best way is to split it into [7,2,5]
 * and [10,8], where the largest sum among the two subarrays is only 18.
 * 
 * IDEA:
 * 
 * [7, 2, 5, 10, 8]
 * 
 * for each array []:
 *   -investigate all possible splits up to remaining m parts
 * [7,2]   [5,10,8]  <-- cut off [7,2], repeat the procedure recursively on  [5,10,8] with parameters: start = i + 1 and len - 1
 *    \      \ 
 *     i      i+1
 *     
 * [7,2,5]   [10,8]  <-- remember the result for block with composite key: start, len
 * 
 */
public class Solution410 {
	int[] sums;

	int walk(int[] nums, int[][] memo, int start, int len) {
		if (len == 1) {
			int sum = sums[len] - sums[start];
			memo[start][len] = sum;
			return sum;
		}

		if (memo[start][len] != -1) {
			return memo[start][len];
		}

		int n = nums.length;
		int sum = Integer.MAX_VALUE;
		
		for (int i = start; i < n; i++) {
			int curSum = sums[i + 1] - sums[start];
			int futureSum = walk(nums, memo, i + 1, len - 1);
			int largestSum = Math.max(curSum, futureSum);
			sum = Math.min(sum, largestSum);
		}

		memo[start][len] = sum;
		return sum;
	}

	public int splitArray(int[] nums, int m) {
		int[][] memo = new int[nums.length][m + 1];

		for (int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1);
		}

		return walk(nums, memo, 0, m);
	}

}
