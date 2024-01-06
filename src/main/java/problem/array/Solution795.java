package problem.array;

/**
 * 
 * We are given an array nums of positive integers, and two positive integers
 * left and right (left <= right).
 * 
 * Return the number of (contiguous, non-empty) subarrays such that the value of
 * the maximum array element in that subarray is at least left and at most
 * right.
 * 
 * Example: Input: nums = [2, 1, 4, 3] left = 2 right = 3 Output: 3 
 * 
 * Explanation:
 * There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * 
 * IDEA:
 * 1. count the total amount of arrays lower then upper_boundary:
 * 
 *  [2, 1, 4, 3]
 *   ----     --  <3
 *   
 *   
 *
 */
public class Solution795 {

	/**
	 * count all possible combinations of arrays
	 * which are cut of by <= bound condition 
	 */
	int count(int[] arr, int bound) {
		int ans = 0, cur = 0;
		for (int num : arr) {
			if (num <= bound){
				cur ++;
			}else {
				cur = 0;// reset on each level breach
			}
			ans += cur;
		}
		return ans;
	}

	public int numSubarrayBoundedMax(int[] a, int left, int right) {
		return count(a, right) - count(a, left - 1);
	}

}
