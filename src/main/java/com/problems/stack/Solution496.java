package com.problems.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s
 * elements are subset of nums2. Find all the next greater numbers for nums1's
 * elements in the corresponding places of nums2.
 * 
 * The Next Greater Number of a number x in nums1 is the first greater number to
 * its right in nums2. If it does not exist, output -1 for this number.
 * 
 * Example 1: Input: 
 * nums1 = [4,1,2], 
 * nums2 = [1,3,4,2] <-- a bigger set, use it build map
 * 
 * Output: [-1,3,-1]
 * 
 * Explanation: For number 4 in the first array, you cannot find the next
 * greater number for it in the second array, so output -1. For number 1 in the
 * first array, the next greater number for it in the second array is 3. For
 * number 2 in the first array, there is no next greater number for it in the
 * second array, so output -1.
 * 
 * O(2n) for building map
 * O(n + m)
 * IDEA:
 *  1) use a reverse approach to build the next map:
 *     take the current and go though the stack of elems seen so far 
 *     and then perform check: which this elem is the biggest for
 *   [1,5,4,5]
 *   
 *   arr       stack - seen elems in the past
 *   -----------------------------------------
 *   []          []
 *   [1]         [1]        
 *   [1,5]       [5], one can remove 1, due to found match: add to map 1 => 5
 *   [1,5,4]     [5,4] cannot construct mapping 5 => 4
 *   [1,5,4,5]   [5,5], one can remove 4, due to found match: add to map 5 => 5  
 *      |   |
 *     will be left on the stack 
 *  
 *  NOTE 1: the last elem always will be on stack   
 *  NOTE 2: for iso-morphic sets (findNums === nums), one can more simple approach similar to problem #503
 *     
 */
public class Solution496 {

	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Stack<Integer> stack = new Stack<>();
		HashMap<Integer, Integer> next = new HashMap<>();// next bigger elem to the key
		int n = findNums.length;

		// rearrange numbers from the bigger set:
		// build a map for chain: nums[i] => next bigger elem
		for (int i = 0; i < nums.length; i++) {
			int cur = nums[i];
			// this loop is used to find all elems on stack which are smaller than current one
			while (!stack.empty() && cur > stack.peek()) { // if stack bigger, do not map
				next.put(stack.pop(), cur);// set smaller => nextbigger
			}
			stack.push(cur);// and push cur elem to to stack
		}
		// all the rest do not have a bigger one
		while (!stack.empty()) {
			next.put(stack.pop(), -1);
		}

		// business logic: apply next map to input array
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			res[i] = next.get(findNums[i]);// always exists because it is a subset
		}
		return res;
	}

	

}
