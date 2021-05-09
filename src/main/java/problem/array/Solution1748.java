package problem.array;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an integer array nums. The unique elements of an array are the
 * elements that appear exactly once in the array.
 * 
 * Return the sum of all the unique elements of nums.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,2,3,2] Output: 4 Explanation: The unique elements are [1,3],
 * and the sum is 4.
 *
 */
public class Solution1748 {

	public int sumOfUnique(int[] nums) {
		Set<Integer> seen = new HashSet<>();
		Set<Integer> unique = new HashSet<>();
		for (int num : nums) {
			unique.add(num);
			if (seen.contains(num)) {
				unique.remove(num);
			}
			seen.add(num);
		}

		int sum = 0;
		for (Integer num : unique) {
			sum += num;
		}
		return sum;
	}
}
