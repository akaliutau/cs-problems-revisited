package com.problems.stack;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * Example:
 * 
 * Input: [2,1,5,6,2,3] Output: 10
 * 
 *     o
 *    oo
 *   ooo
 *  oooo
 * ooooo
 * 
 * 
 *    o
 *   oo
 *   oo
 *   oo o
 * o oooo
 * oooooo
 * 
 * One of the situation:
 *    o
 *   oo
 *   oo
 *   oo o
 *   oooo
 *  ooooo
 * 
 * Can truncate from left to right, because bars in the middle are the HIGHEST 
 * 6X1 = 6
 * 5X2 = 10
 * 
 * 
 * after
 * 
 *   o
 *  oo
 * ooo
 * 
 * 
 * 012345
 * 
 * stack:
 * [-1,0]
 * 
 * 
 * IDEA: truncate asc seq of bars
 * 
 * In this approach, we maintain a stack. Initially, we push a -1 onto the stack to mark the end. 
 * We start with the leftmost bar and keep pushing the current bar's index onto the stack until we get two successive numbers in descending order, 
 * i.e. until we get a[i−1] > a[i]. Now, we start popping the numbers from the stack until we hit a number stack[j] on the stack 
 * such that a [stack[j]]≤a[i]. Every time we pop, we find out the area of rectangle formed using the current element as the height of the rectangle
 *  and the difference between the the current element's index pointed to in the original array and the element stack[top−1]−1 as the width
 * 
 * 
 */
public class Solution84 {

	public int largestRectangleArea(int[] heights) {
		Stack<Integer> barIndex = new Stack<>();
		// index of the highest leftmost bar
		// stack is used to collect only the bars in asc order by their heights
		barIndex.push(-1);
		
		int maxarea = 0;
		int n = heights.length;
		for (int i = 0; i < n; ++i) {
			// 1st it stack: {2}
			// 2nd it stack: {1,5,6}
			while (barIndex.peek() != -1 && heights[barIndex.peek()] >= heights[i]) {// iterate until find a bar lower than current one (i.e. truncate)
                int idx = barIndex.pop();
                int h = heights[idx];
                int w = i - barIndex.peek() - 1;// width excluding the first elem, if no elems then w = i
				maxarea = Math.max(maxarea, h * w);// the height of right bar is higher => area is defined by left
			}
			// here we have a normalized stack, all bars lower than ith
			barIndex.push(i);
		}
		// n it stack: {1,2,3}
		while (barIndex.peek() != -1) {
            int idx = barIndex.pop();
            int w = n - 1 - barIndex.peek();
			maxarea = Math.max(maxarea, heights[idx] * w);
			
		}
		return maxarea;
	}


}
