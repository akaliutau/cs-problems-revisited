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
 * nums2 = [1,3,4,2]. 
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
 *  take the current and go though the stack of elems seen so far to check whom this elem is the biggest for
 * 
 */
public class Solution496 {

	public int[] nextGreaterElement(int[] findNums, int[] nums) {
		Stack<Integer> stack = new Stack<>();
		HashMap<Integer, Integer> map = new HashMap<>();// next bigger elem to the key
		int n = findNums.length;

		// rearrange numbers from main set:
		// build a map for chain: nums[i] => next bigger elem
		for (int i = 0; i < nums.length; i++) {
			int biggest = nums[i];
			// find all elems on stack which are smaller than current one
			while (!stack.empty() && biggest > stack.peek()) { // if stack bigger, do not map
				map.put(stack.pop(), biggest);// set smaller => nextbigger
			}
			stack.push(biggest);// and push cur elem to to stack
		}
		// all the rest do not have a bigger one
		while (!stack.empty()) {
			map.put(stack.pop(), -1);
		}

		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			res[i] = map.get(findNums[i]);// always exists because it is a subset
		}
		return res;
	}

	

}
