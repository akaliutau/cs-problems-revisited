package com.problems.greedy;

/**
 * 
 *  DP, Greedy
 *  
 *  [2,3,1,1,4]
 *  2: 0 -> 3 -> 4
 */
public class Solution45 {

	public int jump(int[] nums) {
		int n = nums.length;
		if (n < 2) {
			return 0;
		}
		// max position one could reach from 0
		int maxReachablePos = nums[0];
		// max number of 1-steps one could do
		// inside this jump
		int maxPosForLastJump = nums[0];

		int jumps = 1;
		for (int i = 1; i < n; ++i) {
			// if to reach this point
			// one needs one more jump
			if (maxPosForLastJump < i) {
				++jumps;// need one more jump
				maxPosForLastJump = maxReachablePos;
			}
			maxReachablePos = Math.max(maxReachablePos, nums[i] + i);
		}
		return jumps;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
