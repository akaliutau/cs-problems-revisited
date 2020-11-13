package com.problems.stack;

import java.util.Stack;

/**
 * Given an array of n integers nums, a 132 pattern is a subsequence of three
 * integers nums[i], nums[j] and nums[k] such that 
 * i < j < k and 
 * nums[i] < nums[k] < nums[j].
 * 
 * Return true if there is a 132 pattern in nums, otherwise return false.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,4] Output: false Explanation: There is no 132 pattern in
 * the sequence.
 * 
 * IDEA:
 * [1,4,5,2]
 * 
 * look on the stack in 2 directions
 * for each i
 * one direction
 *  -->
 * min number seen so far TO THE LEFT of i - check out min[i]
 * 
 * <--
 * use filtered stack which contains:
 * 1) values bigger than min[i]
 * 
 * finally we have:
 * 
 * nums  1   4  5  2
 * min   1   1  1  1
 * stack           2
 *       1    < 5> 2     
 *  
 *  
 * 
 */
public class Solution456 {

	public boolean find132pattern(int[] nums) {
		int n = nums.length;
		if (n < 3)
			return false;
		
		Stack<Integer> stackKthElem = new Stack<>();
		int[] min = new int[n];// min[i] == minimal elem on [0,i] inclusive
		min[0] = nums[0];
		
		for (int i = 1; i < n; i++) {
			min[i] = Math.min(min[i - 1], nums[i]);
		}
		
		for (int j = n - 1; j >= 0; j--) {
			int min0j = min[j];
			if (nums[j] > min0j) {
				// find elem on stackKthElem which is > min0j
				while (!stackKthElem.isEmpty() && stackKthElem.peek() <= min0j) {// first, find number on stack which satisfy j > i
					stackKthElem.pop();
				}
				// stackKthElem is 1) empty OR 2) contains elem > min0j
				if (!stackKthElem.isEmpty() && nums[j] > stackKthElem.peek()) {// min0j < stackKthElem < nums[j], guaranteed that k > j
					return true;
				}
				stackKthElem.push(nums[j]);
			}
		}
		return false;
	}

	

}
