package com.problems.array;

/**
 * Given a non-empty array of integers, return the third maximum number in this
 * array. If it does not exist, return the maximum number. The time complexity
 * must be in O(n). 
 * 
 * Example 1: Input: [3, 2, 1] Output: 1 Explanation: The third
 * maximum is 1.
 * 
 * IDEA:
 * introduce a limit for max function
 * 
 */
public class Solution414 {
    
	// returns the biggest number with limit set to ceil
    static long find(int[] nums, long ceil) {
        long maxVal = Long.MIN_VALUE;
        for (int num : nums) {
            if (maxVal < num && num < ceil) {
                maxVal = num;
            }
        }
        return maxVal;
    }

    public int thirdMax(int[] nums) {
        long first = find(nums, Long.MAX_VALUE);
        long second = find(nums, first);
        if (second == Long.MIN_VALUE) {
            return (int)first;
        }
        long third = find(nums, second);
        if (third == Long.MIN_VALUE) {
            return (int)first;
        }
        return (int)third;
    }

}
