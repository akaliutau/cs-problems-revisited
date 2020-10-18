package com.problems.stack;

import java.util.Stack;

/**
 * Given a list of daily temperatures T, return a list such that, for each day
 * in the input, tells you how many days you would have to wait until a warmer
 * temperature. 
 * 
 * If there is no future day for which this is possible, put 0
 * instead.
 * 
 * For example, given the list of temperatures 
 * T = [73, 74, 75, 71, 69, 72, 76, 73], 
 *     [1,  1,  4,  2,  1,  1,  0,  0].
 * 
 * O(2n)
 */
public class Solution739 {

	public int[] dailyTemperatures(int[] t) {
		int n = t.length;
		int[] ans = new int[n];
		Stack<Integer> dayIndex = new Stack<>();
		for (int i = n - 1; i >= 0; --i) {
			while (!dayIndex.isEmpty() && t[i] >= t[dayIndex.peek()]) {// find a warmer day 
				dayIndex.pop();
			}
			ans[i] = dayIndex.isEmpty() ? 0 : dayIndex.peek() - i;// save diff between cur moment and the index of future
			dayIndex.push(i);
		}
		return ans;
	}


}
