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
 * combination of prefix array and stack filtering
 * [1,4,5,2]
 *  |   | \
 * min  |  at stack
 *     cur
 * 
 * the idea is to find a leftmost element (any) using a min prefix array 
 * and an inverted pair (j, k) - use stack to hold all past values (== elem k) and current elem to find j
 * 
 * 1) min prefix array guarantee that there exists an elem which is smaller than the prefix elem at position i  
 * 2) stack guarantees the index of element on it is always bigger than the current one (past elements)
 * 3) for each element from tail check the condition elem > elem@stack
 * 
 * 
 * 
 * 1) first build a minimal stack
 * look on the stack in 2 directions
 * for each i
 * one direction
 *  -->
 * min number seen so far TO THE LEFT of i - check out min[i]
 * 
 * 
 * 
 * <--
 * 2) use filtered stack which contains:
 *  values bigger than min[i]
 * 
 * 
 * 
 */
public class Solution456 {

	public boolean find132pattern(int[] nums) {
		int n = nums.length;
		if (n < 3)
			return false;
		
		Stack<Integer> stackForKthElem = new Stack<>();
		int[] min = new int[n];// min[i] == minimal elem on [0,i] inclusive
		min[0] = nums[0];
		
		for (int i = 1; i < n; i++) {
			min[i] = Math.min(min[i - 1], nums[i]);
		}
		
		for (int j = n - 1; j >= 0; j--) {
			int leftElem = min[j];
			int curElem = nums[j];
			if (curElem > leftElem) {// [some elem on 0..j][stack(k)][j]
				// find elem on stack which is > min0j
				while (!stackForKthElem.isEmpty() && stackForKthElem.peek() <= leftElem) {// first, find number on stack which satisfy stack > left_elem
					stackForKthElem.pop();
				}
				// filter out all that smaller than min on left
				// f.e. 247[1]5
				
				// stackKthElem is 1) empty OR 2) contains elem > min0j
				if (!stackForKthElem.isEmpty() && curElem > stackForKthElem.peek()) {// min0j < stackKthElem < nums[j], guaranteed that k > j
					return true;
				}
				stackForKthElem.push(curElem);
			}
		}
		return false;
	}

	

}
