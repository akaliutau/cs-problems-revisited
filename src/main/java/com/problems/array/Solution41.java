package com.problems.array;

/**
 * Given an unsorted integer array nums, find the smallest missing positive
 * integer.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,0] Output: 3
 * 
 */
public class Solution41 {

	public int firstMissingPositive(int[] nums) {
		int n = nums.length;

		// Base case - find 1
		boolean hasOne = false;
		for (int i = 0; i < n; i++)
			if (nums[i] == 1) {
				hasOne = true;
				break;
			}

		if (!hasOne) {
			return 1;
		}

		// nums = [1]
		if (n == 1) {
			return 2;
		}

		// Replace negative numbers, zeros, and numbers larger than n by 1s.
		// After this nums will contain only positive numbers.
		for (int i = 0; i < n; i++) {
			if (nums[i] <= 0 || nums[i] > n) {
				nums[i] = 1;
			}
		}
		
		boolean[] present = new boolean[n + 1];
		
		// now nums is a map, which maps to itself.
		for (int i = 0; i < n; i++) {
			int idx = Math.abs(nums[i]);
			present[idx] = true;
		}

		// Now the index of the first positive number
		// is equal to first missing positive.
		for (int i = 1; i < n + 1; i++) {
			if (!present[i])
				return i;
		}

		return n + 1;
	}

}
