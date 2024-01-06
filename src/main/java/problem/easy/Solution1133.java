package problem.easy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * Given an array of integers A, return the largest integer that only occurs
 * once.
 * 
 * If no integer occurs once, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [5,7,3,9,4,9,8,3,1] Output: 8 Explanation: The maximum integer in the
 * array is 9 but it is repeated. The number 8 occurs only once, so it's the
 * answer.
 */
public class Solution1133 {

	public int largestUniqueNumber(int[] a) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int num : a) {
			freq.compute(num, (k, v) -> v == null ? 1 : v + 1);
		}
		List<Integer> largest = freq.entrySet().stream().filter(entry -> entry.getValue() == 1)
				.map(entry -> entry.getKey()).collect(Collectors.toList());
		if (largest.size() == 0) {
			return -1;
		}
		int max = Integer.MIN_VALUE;
		for (int num : largest) {
			max = Math.max(max, num);
		}
		return max;
	}

}
