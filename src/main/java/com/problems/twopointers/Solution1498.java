package com.problems.twopointers;

import java.util.Arrays;

/**
 * 
 * Given an array of integers nums and an integer target.
 * 
 * Return the number of non-empty subsequences of nums such that the sum of the
 * minimum and maximum element on it is less or equal to target. Since the
 * answer may be too large, return it modulo 109 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [3,5,6,7], target = 9 Output: 4 Explanation: There are 4
 * subsequences that satisfy the condition. 
 * [3] -> Min value + max value <= target (3 + 3 <= 9) 
 * [3,5] -> (3 + 5 <= 9) 
 * [3,5,6] -> (3 + 6 <= 9) 
 * [3,6] -> (3 + 6 <= 9) 
 * 
 * Example 2:
 * 
 * Input: nums = [3,3,6,8], target = 10 Output: 6 Explanation: There are 6
 * subsequences that satisfy the condition. (nums can have repeated numbers).
 * [3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
 * 
 * IDEA:
 * 0) 2-pointers approach
 * 1) sort the array
 * 2) for each index: track the (min,max,len) pair - calculate the number of possibilities dynamically
 * 
 * 
 * 
 * [3,5,6,7]
 *  |   |
 *  | and ending at index=2
 *  calc all arrays starting at index=0
 *  
 * calc number of possibilities on the range [l,r]:
 * 
 * [3,4,5,6]
 *  because [3,6] is valid:
 *  [3,5] is valid
 *  
 *  [3,4] & [4,5] are valid
 *  
 *  and so on
 *  
 *  total = 1 + 2 + 4 + ... = 2^n
 *  
 * 
 */
public class Solution1498 {
	
 	static long mod = 1_000_000_007;
    static int[] powt = new int[100000];
    
    static{
        long p = 1;
        for (int i = 0; i < 100000; i++){
            powt[i] = (int)p;
            p *= 2;
            p %= mod;
        }
    }
    

	public int numSubseq(int[] nums, int target) {
		
		int n = nums.length;
		long count = 0;
		int l = 0, r = n - 1;
		Arrays.parallelSort(nums);
        while (l <= r) {
		  if (nums[l] + nums[r] > target) {
		     r --;
		  } else {
			 count += powt[r - l];
			 count %= mod;
		     l ++;
          }
        }
		
		return (int) count;

	}

}
