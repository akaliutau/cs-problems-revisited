package com.problems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, are there elements
 * a, b, c, and d in nums such that a + b + c + d = target? 
 * Find all unique quadruples in the array which gives the sum of target. 
 * 
 * Notice that the
 * solution set must not contain duplicate quadruples. 
 * 
 * Example 1: Input: nums =
 * [1,0,-1,0,-2,2], target = 0 Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 
 * IDEA:
 * reduce the problem to n-1 case, then n-2 down to n = 2 for a list of sorted numbers
 */
public class Solution18 {
    
    int len;

    List<List<Integer>> twoSum(int[] nums, int target, int start) {//Note: search on the sorted array
        List<List<Integer>> res = new ArrayList<>();
        int left = start, right = len - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target || (left > start && nums[left] == nums[left - 1])) {// on smaller sum or on equal elems
                ++left;
            } else if (sum > target || (right < len - 1 && nums[right] == nums[right + 1])) {
                --right;
            } else {// found it
                res.add(Arrays.asList(nums[left++], nums[right--]));
            }
        }
        return res;
    }
    
    List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == len || nums[start] * k > target || target > nums[len - 1] * k)
            return res;
        if (k == 2) {// use > optimal way
            return twoSum(nums, target, start);
        }
        for (int i = start; i < len; ++i) {
            if (i == start || nums[i - 1] != nums[i]) {// this block is triggered only on the first elem or on change
                for (List<Integer> set : kSum(nums, target - nums[i], i + 1, k - 1)) {// recursive call to decrease problem n-1 => n-2
                    List<Integer> found = new ArrayList<>(Arrays.asList(nums[i]));
                    found.addAll(set);
                    res.add(found);
                }
            }
        }
        return res;
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
 
}
