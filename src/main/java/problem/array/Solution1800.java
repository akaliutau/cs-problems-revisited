package problem.array;

/**
 * Given an array of positive integers nums, return the maximum possible sum of
 * an ascending subarray in nums.
 * 
 * A subarray is defined as a contiguous sequence of numbers in an array.
 * 
 * A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i
 * where l <= i < r, numsi < numsi+1. Note that a subarray of size 1 is
 * ascending.
 * 
 * Example 1:
 * 
 * Input: nums = [10,20,30,5,10,50] Output: 65 Explanation: [5,10,50] is the
 * ascending subarray with the maximum sum of 65.
 * 
 * IDEA:
 * 
 * iterate once and calculate the running sum
 *
 */
public class Solution1800 {
	public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int runningSum = 0;
        int maxSum = 0;

        for (int i = 0; i < n; ++i) {
        	runningSum += nums[i];
            if (i < n-1 && nums[i] >= nums[i+1]) {// end of contiguous sequence detected
                maxSum = Math.max(runningSum, maxSum);
                runningSum = 0;
            }
        }

        return Math.max(runningSum, maxSum);
    }
}
