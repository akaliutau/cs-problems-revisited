package com.problems.twopointers;

/**
 * https://en.wikipedia.org/wiki/Dutch_national_flag_problem Given an array nums
 * with n objects colored red, white, or blue, sort them in-place so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white, and blue. Here, we will use the integers 0, 1, and 2 to represent the
 * color red, white, and blue respectively. Follow up: Could you solve this
 * problem without using the library's sort function? Could you come up with a
 * one-pass algorithm using only O(1) constant space? 
 * 
 * Example 1: 
 * Input: nums = [2,0,2,1,1,0] 
 * Output:       [0,0,1,1,2,2]
 * 
 * 
 */
public class Solution75 {
    
    static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void sortColors(int[] nums) {
        // for all idx < i : nums[idx < i] = 0
        // j is an index of element under consideration
        int col0 = 0, cur = 0;
        // for all idx > k : nums[idx > k] = 2
        int col2 = nums.length - 1;

        while (cur <= col2) {
            if (nums[cur] == 0) {
                // swap col0-th and cur-th elements
                // i++ and j++
                swap(nums, col0, cur);
                col0 ++;
                cur ++;
            } else if (nums[cur] == 2) {
                // swap k-th and cur-th elements
                // col2--
                swap(nums, col2, cur);
                col2 --;
            } else {// cur = col1
                cur++;
            }
        }
    }

 

}
