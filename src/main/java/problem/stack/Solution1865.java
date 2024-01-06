package problem.stack;

import java.util.Stack;

/**
 * The min-product of an array is equal to the minimum value in the array
 * multiplied by the array's sum.
 * 
 * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 *
 * (3+2+5) = 2 * 10 = 20. Given an array of integers nums, return the maximum
 * min-product of any non-empty subarray of nums. Since the answer may be large,
 * return it modulo 109 + 7.
 * 
 * Note that the min-product should be maximized before performing the modulo
 * operation. Testcases are generated such that the maximum min-product without
 * modulo will fit in a 64-bit signed integer.
 * 
 * A subarray is a contiguous part of an array.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,2] Output: 14 
 * 
 * Explanation: The maximum min-product is
 * achieved with the subarray [2,3,2] (minimum value is 2). 2 * (2+3+2) = 2 * 7 = 14.
 * 
 * IDEA:
 * 0. Consider edge case - the array of non-decreasing numbers, and reduce the problem to processing of this cases
 * 1. use stack to hold references to the array elements (not values themselves), 
 *    in non-decreasing order => top elem is always a min of sub array
 * 2. process peaks as edge cases in purge loop phases - IMPORTANT: because peaks may be more optimal solution
 * 
 * Simulation:
 * 
 * [1,2,3,2,2]
 * 
 * iteration stack
 *  1         [1]      <-- added 1 because stack is empty 
 *  2         [1,2]    <-- 2 > 1, so omit purge loop and add it
 *  3         [1,2,3]  <-- 3 > 2, so omit purge loop and add it
 *  4         [1,2,2]  <-- 2 not > 3, purge stack until 2 has been reached
 *  5         [1,2,2,2]   <-- last iteration - process all elems
 *  
 *      o
 *    o o o o
 *  o o o o o
 * [1,2,3,2,2]
 *    | |
 *    res1 
 *      
 *    o o o
 *  o o o o
 * [1,2,2,2]
 *  |     |
 *  res2, res3, res4 - one can peek the top as a minimal element, because the stack is non-decreasing
 *  
 *  General and more complex example
 *  
 *          o
 *      o   o o
 *    o o o o o
 *  o o o o o o
 * [1,2,3,2,4,3]
 *    / \
 *       ends here
 *        / \
 *           ends here        
 *        
 *        o
 *    o o o
 *  o o o o
 * [1,2,2,3]
 * /       \ 
 *         ends here
 */
public class Solution1865 {
	
	public int maxSumMinProduct(int[] nums) {
		int n = nums.length;
		
		Stack<Integer> stack = new Stack<>();

		long sum[] = new long[n + 1], res = 0;
		
		// running sum for acceleration
	    for (int i = 0; i < n; ++i)
	       sum[i + 1] = sum[i] + nums[i];
	    
	    
	    for (int i = 0; i <= n; ++i) {
	    	// this block is executed only on max elems in array
	        while (!stack.empty() && (i == n || nums[stack.peek()] > nums[i])) {
	            int excessNumRef = stack.pop();
	            int excessNum = nums[excessNumRef];
	            long runSum = sum[i] - sum[stack.empty() ? 0 : stack.peek() + 1];
	            res = Math.max(res, runSum * excessNum);
	        }
	        
	        stack.push(i);
	    }
	    return (int)(res % 1000000007);
		
    }

}
