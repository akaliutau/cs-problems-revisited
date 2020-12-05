package com.problems.dp;

/**
 * You may recall that an array arr is a mountain array if and only if:
 * 
 * arr.length >= 3 There exists some index i (0-indexed) with 0 < i < arr.length
 * - 1 such that: arr[0] < arr[1] < ... < arr[i - 1] < arr[i] arr[i] > arr[i +
 * 1] > ... > arr[arr.length - 1] Given an integer array nums​​​, return the
 * minimum number of elements to remove to make nums​​​ a mountain array.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,3,1] Output: 0 
 * 
 * Explanation: The array itself is a mountain
 * array so we do not need to remove any elements. Example 2:
 * 
 * Input: nums = [2,1,1,5,6,2,3,1] Output: 3 
 * 
 * Explanation: One solution is to
 * remove the elements at indices 0, 1, and 5, making the array nums =
 * [1,5,6,3,1].
 * 
 * 
 */
public class Solution5559 {

	public void lengthOfLIS(int[] nums, int[] dp) {
		int n = nums.length;
		dp[0] = 1;
		for (int i = 1; i < n; i++) {
			int maxval = 0;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					maxval = Math.max(maxval, dp[j]);
				}
			}
			dp[i] = maxval + 1;
		}
	}

	public void lengthOfLDS(int[] nums, int[] dp) {
		int n = nums.length;
		dp[n - 1] = 1;
		for (int i = n - 1; i > -1; i--) {
			int maxval = 0;
			for (int j = n - 1; j > i; j--) {
				if (nums[i] > nums[j]) {
					maxval = Math.max(maxval, dp[j]);
				}
			}
			dp[i] = maxval + 1;
		}
	}

	public int minimumMountainRemovals(int[] nums) {
		int n = nums.length;
		int[] left = new int[n];
		int[] right = new int[n];
		lengthOfLIS(nums, left);
		lengthOfLDS(nums, right);
		int len = 0;
		for (int i = 1; i < n - 1; i++) {
			len = Math.max(len, left[i] + right[i] - 1);
		}
		return n - len;

	}



}
