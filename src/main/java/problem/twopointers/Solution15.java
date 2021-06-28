package problem.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Given an array nums of n integers, are there elements a, b, c in nums such
 * that a + b + c = 0? Find all unique triplets in the array which gives the sum
 * of zero.
 * 
 * Notice that the solution set must not contain duplicate triplets.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [-1,0,1,2,-1,-4] Output: [[-1,-1,2],[-1,0,1]]
 * 
 * IDEA:
 * fix the 3rd number and find the other 2 using 2sum case on the sorted array
 * 
 */
public class Solution15 {

	void twoSums(int[] nums, int i, List<List<Integer>> results) {
		int constant = nums[i];
		int lo = i + 1;// 2nd number - right next after constant
		int hi = nums.length - 1;// 3rd number
		while (lo < hi) {
			int sum = constant + nums[lo] + nums[hi];
			if (sum < 0) {// too small, increase the lowest one
				++lo;
			} else if (sum > 0) {// too big, decrease the highest one
				--hi;
			} else {
				results.add(Arrays.asList(constant, nums[lo], nums[hi]));
				lo++;
				hi--;
				while (lo < hi && nums[lo] == nums[lo - 1]) {// omit equal numbers
					++lo;
				}
			}
		}
	}

	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < nums.length && nums[i] <= 0; ++i) {// the 3rd number must be < 0 because sum=0
			if (i == 0 || nums[i - 1] != nums[i]) {
				twoSums(nums, i, res);
			}
		}
		return res;
	}

}
