package com.problems.dp;

/**
 * Given an array of non-negative integers nums, you are initially positioned at
 * the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * You can assume that you can always reach the last index.
  * 
 * Example 1:
 * 
 * Input: nums = [2,3,1,1,4] Output: 2 
 * 
 * Explanation: The minimum number of jumps
 * to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to
 * the last index.
 * 
 * IDEA:
 * [2,3,1,1,4]
 *  |   |
 *      |
 *      reachedLimit - which can be reached from i = 0
 *  there is no need to update jumps UNTIL pointer = i meets  reachedLimit
 *  
 *  When we reach this point, update reachedLimit to the maxPos which must be tracked separately
 *       
 */
public class Solution45 {

	public int jump(int[] nums) {
		int n = nums.length;
		if (n < 2) {
			return 0;
		}
		// max position one could reach from 0
		int maxPos = nums[0];

		int reachedLimit = nums[0];

		int jumps = 1;
		for (int i = 1; i < n; ++i) {
			// if to reach this point
			// one needs one more jump
			if (reachedLimit < i) {
				++jumps;
				reachedLimit = maxPos;
			}
			maxPos = Math.max(maxPos, nums[i] + i);
		}
		return jumps;
	}
	
}
