package com.problems.binarysearch;

/**
 * Given an array of integers nums sorted in ascending order, find the starting
 * and ending position of a given target value. If target is not found in the
 * array, return [-1, -1].
 */
public class Solution34 {

    static void search(int[] nums, int left, int right, int target, int[] result) {
        if (left >= right) {
            if (nums[left] == target) {
                result[0] = Math.min(result[0], left);
                result[1] = Math.max(result[1], left);
            }
            return;
        }
        int center = (left + right - 1) / 2;
        search(nums, left, center, target, result);
        search(nums, center + 1, right, target, result);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int n = nums.length;
        res[0] = n;
        res[1] = -1;
        
        if (n > 0) {
            search(nums, 0, n - 1, target, res);
        } 
        
        if (res[0] == n || res[1] == -1) {
            res[0] = -1;
            res[1] = -1;
        }

        return res;

    }

}
