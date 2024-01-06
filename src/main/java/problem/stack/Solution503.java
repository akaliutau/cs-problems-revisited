package problem.stack;

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
 * IDEA:
 * use stack to hold only those elements which are > current
 * 
 * array              stack     result
 * -------------------------------------------
 * [1, 2,  1]        [1]        [0,0,-1]
 *         |
 *         
 * [1, 2,  1]        [2]       [0,-1,-1] <-- '1' has been removed, update result, then '2' was added
 *     |
 *         
 * [1, 2,  1]        [2,1]     [2,-1,-1] <-- first hit, '1' was added during normal cycle
 *  |
 *  
 * In order to get the correct answer, the 2nd round is needed
 *  
 * [1, 2,  1]        [2]     [2,-1, 2] <-- second hit, first '1' has been removed, update result, then last '1' was added
 *         |
 *  and so on
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
			
			//loop: a filter to remove all (smaller than cur) elements from stack
			while (!stack.empty() && nums[stack.peek()] <= nums[curIdx]) {
				stack.pop();
			}
			
			res[curIdx] = stack.empty() ? -1 : nums[stack.peek()];
			stack.push(curIdx);// add current elem on the stack
		}
		return res;
	}



}
