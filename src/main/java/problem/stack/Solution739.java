package problem.stack;

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
 *                  |   |   |    \_ \ 
 *                  |   |   |        no need to hold stack 
 *                  |   |   hold ref to 76
 *                  |  hold ref to 72
 *                 hold ref to 72, NOTE: 71 > 69, => index(69) can be dropped
 *                  
 * O(2n)
 * 
 * IDEA:
 *   there is a need to hold only refs to the days with tempr > cur    
 *      
 *   1) save the REFERENCE to the day with specific temperature, not temperature itself
 *   2) dynamically remove all cooler days
 */
public class Solution739 {

	public int[] dailyTemperatures(int[] t) {
		int n = t.length;
		int[] ans = new int[n];
		
		Stack<Integer> dayIndex = new Stack<>();// used to hold references to the all seen days
		for (int i = n - 1; i >= 0; --i) {
			while (!dayIndex.isEmpty() && t[i] >= t[dayIndex.peek()]) {// find a warmer day 
				dayIndex.pop();//remove all cooler days
			}
			ans[i] = dayIndex.isEmpty() ? 0 : dayIndex.peek() - i;// save diff between cur moment and the index of future
			dayIndex.push(i);
		}
		
		return ans;
	}


}
