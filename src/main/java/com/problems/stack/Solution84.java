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
 */
public class Solution84 {

	public int largestRectangleArea(int[] heights) {
		Stack<Integer> barIndex = new Stack<>();
		barIndex.push(-1);// index of the highest leftmost bar
		
		int maxarea = 0;
		for (int i = 0; i < heights.length; ++i) {
			while (barIndex.peek() != -1 && heights[barIndex.peek()] >= heights[i]) {// find a bar higher or equal than current one
                int idx = barIndex.pop();
				maxarea = Math.max(maxarea, heights[idx] * (i - barIndex.peek() - 1));
				
			}
			barIndex.push(i);
		}
		while (barIndex.peek() != -1) {
            int idx = barIndex.pop();
			maxarea = Math.max(maxarea, heights[idx] * (heights.length - barIndex.peek() - 1));
			
		}
		return maxarea;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
