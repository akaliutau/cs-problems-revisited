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
 * 1. use stack to hold references to the array elements (not values themselves)
 * 2. 
 *
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
	            int maxRef = stack.pop();
	            int maxNum = nums[maxRef];
	            long runSum = sum[i] - sum[stack.empty() ? 0 : stack.peek() + 1];
	            res = Math.max(res, runSum * maxNum);
	        }
	        
	        stack.push(i);
	    }
	    return (int)(res % 1000000007);
		
    }

}
