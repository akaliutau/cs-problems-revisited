package problem.twopointers;

import java.util.Arrays;

/**
 * Given an array arr of integers and integer K, return the maximum S such that
 * there exists i < j with arr[i] + arr[j] = S and S < K. 
 * If no i, j exist satisfying this equation, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: arr = [34,23,1,24,75,33,54,8], K = 60 Output: 58 Explanation: We can
 * use 34 and 24 to sum 58 which is less than 60.
 * 
 * IDEA:
 * sort array and advance either left or right boundary
 * 
 */
public class Solution1099 {

	public int twoSumLessThanK(int[] arr, int k) {
		int s = -1;
		Arrays.sort(arr);
		int lo = 0, hi = arr.length - 1;
		while (lo < hi) {
			if (arr[lo] + arr[hi] < k) {
				s = Math.max(s, arr[lo] + arr[hi]);
				++lo;
			} else
				--hi;
		}
		return s;
	}

	
}
