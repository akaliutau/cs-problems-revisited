package com.problems.stack;

import java.util.Stack;

/**
 * 
 * Stack
 * 
 * Input:  [1,2,1] (circular array)
 * Output: [2,-1,2]
 * 
 * T(2n + n)
 * 
 */
public class Solution503 {

	public int[] nextGreaterElements(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		Stack<Integer> stack = new Stack<>();// contains only the biggest elements found so far, worst case - O(n) 
		// go through array twice starting from tail
		for (int i = 2 * n - 1; i > -1; --i) {
			int curIdx = i % n;
			while (!stack.empty() && nums[stack.peek()] <= nums[curIdx]) {// remove all (smaller then cur) elements from stack 
				stack.pop();
			}
			res[curIdx] = stack.empty() ? -1 : nums[stack.peek()];
			stack.push(curIdx);// left on the stack only the biggest
		}
		return res;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
