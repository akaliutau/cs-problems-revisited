package com.problems.array;

import java.util.Arrays;

/**
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form. Return 0 if the array contains less than 2
 * elements. 
 * 
 * Example 1: Input: [3,6,9,1] Output: 3 Explanation: The sorted form
 * of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference
 * 3.
 * 
 * IDEA:
 * sort and compare pairs [i] and [i+1]
 */
public class Solution164 {

    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {// edge case
            return 0;
        }

        int i = 1;
        Arrays.sort(nums);
        int diff = nums[1] - nums[0];
        while (i < n - 1) {
            diff = Math.max(diff, nums[i + 1] - nums[i]);
            i++;
        }
        return diff;
    }

}
