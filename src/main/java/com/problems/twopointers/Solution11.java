package com.problems.twopointers;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point
 * at coordinate (i, ai). 
 * 
 * n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). 
 * 
 * Find two lines, which, together with
 * the x-axis forms a container, such that the container contains the most
 * water.
 * 
 * IDEA: traverse bars in narrowing order
 * 
 * 
 *    |
 * |  |     |
 * |  |  |  |
 * |  |  |  |
 * 
 * 0  1  2  3
 * 
 * 
 */
public class Solution11 {

	public int maxArea(int[] height) {
		int maxarea = 0;
		int l = 0, r = height.length - 1;
		while (l < r) {
			maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
			if (height[l] < height[r])
				l++;
			else
				r--;
		}
		return maxarea;
	}



}
