package problem.stack;

import java.util.ArrayDeque;
import java.util.Deque;

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
 * lets hist = {width, height}
 * in the beginning:
 * {1,2} {1,1} {1,5} {1,6} {1,2} {1,3}
 * 
 *    o
 *   oo
 *   oo
 *   oo o
 * o oooo
 * oooooo
 *  \  |
 *   | if we are here, we can use all held h's as the basis for rectangle and track back 
 *   \ 
 *    until we meet this elem   
 * 
 * the stack state:
 * [2]       <-- variants: 2X1
 * [1]
 * [1,5]
 * [1,5,6]
 * [1,2]     <-- variants: 6X1, 5X2, 1X3 - note the monotonic decrease of h and increase of w - this is the core of algorithm
 * [1,2,3]   <-- variants: 3X1, 2X2, 1X3
 * 
 * 
 * 
 */
public class Solution84 {
	
	public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        final int n = heights.length;
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();// stack to hold pointers to the heights array
        
        for (int curPos = 0; curPos <= n; curPos++) {
            int curHeight = curPos == n ? 0 : heights[curPos];  // add a 0 height bar in the end
            // While the current height is less than the height in stack, pop the element as calculate the area.
            while (!stack.isEmpty() && curHeight <= heights[stack.peek()]) {
                int h = heights[stack.pop()];  // Get height in the top stack.
                int j = stack.isEmpty() ? 0 : stack.peek() + 1;  // Get the most left bar that use h.
                int area = h * (curPos - j);
                res = Math.max(res, area);
            }
            stack.push(curPos);
        }
        return res;
    }

}
