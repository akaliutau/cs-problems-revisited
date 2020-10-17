package com.problems.stack;

import java.util.Stack;

/**
 * 
 * Given a circular array (the next element of the last element is the first
 * element of the array), print the Next Greater Number for every element. The
 * Next Greater Number of a number x is the first greater number to its
 * traversing-order next in the array, which means you could search circularly
 * to find its next greater number. If it doesn't exist, output -1 for this
 * number.
 * 
 * 
 * Input: [1,2,1] (circular array) Output: [2,-1,2]
 * 
 * Explanation: The first 1's next greater number is 2; The number 2 can't find
 * next greater number; The second 1's next greater number needs to search
 * circularly, which is also 2.
 * 
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
			while (!stack.empty() && nums[stack.peek()] <= nums[curIdx]) {// remove all (smaller then cur) elements from
																			// stack
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
