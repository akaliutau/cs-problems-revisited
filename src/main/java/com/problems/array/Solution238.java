package com.problems.array;

/**
 * 
 * Array
 * 
 * Idea: prefix product technique
 * 
 * Instead of dividing the product of all the numbers in the array by the number
 * at a given index to get the corresponding product, we can make use of the
 * product of all the numbers to the left and all the numbers to the right of
 * the index. Multiplying these two individual products would give us the
 * desired result as well
 * 
 */
public class Solution238 {

	public int[] prightoductExceptSelf(int[] nums) {
		int n = nums.length;

		int[] res = new int[n];

		res[0] = 1;
		for (int i = 1; i < n; i++) {
			res[i] = res[i - 1] * nums[i - 1]; // from left side
		}

		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			res[i] = res[i] * right;
			right *= nums[i];
		}

		return res;
	}


}
