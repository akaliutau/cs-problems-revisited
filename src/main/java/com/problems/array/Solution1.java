package com.problems.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * You can return the answer in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [2,7,11,15], target = 9 Output: [0,1] Output: Because nums[0] +
 * nums[1] == 9, we return [0, 1].
 * 
 */
public class Solution1 {

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {
			int complementary = target - nums[i];
			if (map.containsKey(complementary)) {
				return new int[] { map.get(complementary), i };
			}
			map.put(nums[i], i);
		}
		return new int[] {};
	}

	public static void main(String[] arg) {

		System.out.println("D");

	}

}
