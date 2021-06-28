package problem.array;

/**
 * Given an unsorted integer array nums, find the smallest missing positive
 * integer.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,0] Output: 3
 * 
 * IDEA:
 * 1. analyze edge cases (<0, >n)
 * 2. use hashmap to track all numbers
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

		// nums length is 1, f.e  [1] - note it is ALWAYS 1 (due to the previous step)
		if (n == 1) {
			return 2;
		}

		// Replace negative numbers, zeros, and numbers larger than n by 1s.
		// After this nums will contain only positive numbers >= 1.
		for (int i = 0; i < n; i++) {
			if (nums[i] <= 0 || nums[i] > n) {
				nums[i] = 1;
			}
		}
		
		boolean[] present = new boolean[n + 1];
		
		// now we have only positive numbers >= 1
		// also now nums is a map, which maps to itself (pigeonhole approach)
		for (int i = 0; i < n; i++) {
			int idx = nums[i];
			present[idx] = true;
		}

		// Now the index of the first positive number
		// is equal to first missing positive.
		for (int i = 1; i < n + 1; i++) {// iterate through all possible numbers
			if (!present[i])
				return i;
		}

		return n + 1;
	}

}
