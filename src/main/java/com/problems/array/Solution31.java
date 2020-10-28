package com.problems.array;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers. If such an arrangement
 * is not possible, it must rearrange it as the lowest possible order (i.e.,
 * sorted in ascending order). The replacement must be in place and use only
 * constant extra memory. Example 1: Input: nums = [1,2,3] Output: [1,3,2]
 * 
 * [1,3,5,4]
 * [1,4,5,3] -> [1,4,3,5] reverse 
 * 
 */
public class Solution31 {
    
    void reverse(int[] nums, int from) {
        int i = from, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {// find an asc order from the tail - a peak
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {// find an elem greater or equal than found one 
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

 

}
