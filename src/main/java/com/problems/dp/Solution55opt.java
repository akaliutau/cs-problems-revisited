package com.problems.dp;

/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [2,3,1,1,4] Output: true Explanation: Jump 1 step from index 0
 * to 1, then 3 steps to the last index. 
 * 
 * Example 2:
 * 
 * Input: nums = [3,2,1,0,4] Output: false Explanation: You will always arrive
 * at index 3 no matter what. Its maximum jump length is 0, which makes it
 * impossible to reach the last index.
 * 
 * O(n) + O(k)
 */
public class Solution55opt {

	public boolean canJump(int[] nums) {
		int n = nums.length;
		int maxNumberCovered = 0;
		if (n == 0) {
			return false;
		}
		if (n == 1) {
			return nums[0] >= 0;
		} else if (nums[0] == 0) {
			return false;
		}

		for (int i = 0; i < n - 1; i++) {
			int dist = nums[i];
			if (dist == 0) {// check wall presence
				if (maxNumberCovered <= i) {
					return false;
				}
			}
			int maxJump = i + dist;
			if (maxJump >= n - 1) {
				return true;
			}
			maxNumberCovered = Math.max(maxNumberCovered, maxJump);

		}
		return maxNumberCovered >= n - 1;

	}

	public static void main(String[] arg) {

		System.out.println();

	}

}
