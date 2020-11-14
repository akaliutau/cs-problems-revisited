package com.problems.stack;

import java.util.Stack;

/**
 * Given an integer array nums, you need to find one continuous subarray that if
 * you only sort this subarray in ascending order, then the whole array will be
 * sorted in ascending order. Return the shortest such subarray and output its
 * leftgth. 
 * 
 * Example 1: Input: nums = [2,6,4,8,10,9,15] Output: 5 
 * 
 * Explanation: You
 * need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array
 * sorted in ascending order.
 * 
 * IDEA: split the array into 3 parts:
 * 
 * [inc][dec][inc]
 *     |     |
 *    left   right
 */
public class Solution581 {

    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> history = new Stack<>();// all elems in the past seen so far
        int n = nums.length;
        int right = 0;
        int left = n;
        for (int i = 0; i < n; i++) {// find the leftest possible boundary of dec seq, and before this seq - increase
        	//                         any elem in history
            while (!history.isEmpty() && nums[history.peek()] > nums[i]) {// exit if found increment:  6 -> 7 -> 10
                left = Math.min(left, history.pop());
            }
            history.push(i);
        }
        history.clear();
        for (int i = n - 1; i >= 0; i--) {// find the rightest possible boundary of inc seq
            while (!history.isEmpty() && nums[history.peek()] < nums[i]) {// exit if found decrement: 10 -> 8 -> 6
                right = Math.max(right, history.pop());
            }
            history.push(i);
        }
        return right - left > 0 ? right - left + 1 : 0;
    }

}
