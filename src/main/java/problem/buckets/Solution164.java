package problem.buckets;

import java.util.Arrays;

/**
 * 
 * Given an integer array nums, return the maximum difference between two
 * successive elements in its sorted form. If the array contains less than two
 * elements, return 0.
 * 
 * You must write an algorithm that runs in linear time and uses linear extra
 * space.
 * 
 * Example 1:
 * 
 * Input: nums = [3,6,9,1] Output: 3 
 * 
 * Explanation: The sorted form of the array
 * is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 * 
 * IDEA:
 * 
 * 1. maximum gap must be greater or equal to  [(max - min ) / (n - 1)], because the smallest value can be achieved on equal buckets; and inequality leads to disbalance
 * 2. map all numbers to the buckets; collect only max and min values in minBucket and maxBucket arrays
 * 3. iterate through all buckets and calc difference () as gap 
 * 
 * Why it's working:
 * basically we are ignoring differences INSIDE buckets, calculating instead gap between max and min like so:
 * 
 *  [min, ... max]  [min, ..., max] => gap = min[i] - max[i-1]
 */
public class Solution164 {
	public int maximumGap(int[] nums) {
		int min = nums[0], max = nums[0], n = nums.length;
		for (int num : nums) {
			min = Math.min(min, num);
			max = Math.max(max, num);
		}
		
		if (min == max) {
			return 0; // All elements are the same
		}
		
		int bucketSize = (int) Math.ceil((double) (max - min) / (n - 1));
		int[] minBucket = new int[n];
		int[] maxBucket = new int[n];
		Arrays.fill(minBucket, Integer.MAX_VALUE);
		Arrays.fill(maxBucket, Integer.MIN_VALUE);
		
		for (int num : nums) {
			int idx = (num - min) / bucketSize;
			minBucket[idx] = Math.min(num, minBucket[idx]);
			maxBucket[idx] = Math.max(num, maxBucket[idx]);
		}
		
		int maxGap = bucketSize; // Maximum gap is always greater or equal to bucketSize
		int lastMax = maxBucket[0];
		for (int i = 1; i < n; i++) {
			if (minBucket[i] == Integer.MAX_VALUE)
				continue; 
			maxGap = Math.max(maxGap, minBucket[i] - lastMax);
            lastMax = maxBucket[i];
		}
		 return maxGap;
	}
}
