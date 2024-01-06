package problem.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Given three integers x, y, and bound, return a list of all the powerful
 * integers that have a value less than or equal to bound.
 * 
 * An integer is powerful if it can be represented as xi + yj for some integers
 * i >= 0 and j >= 0.
 * 
 * You may return the answer in any order. In your answer, each value should
 * occur at most once.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: x = 2, y = 3, bound = 10 Output: [2,3,4,5,7,9,10] Explanation:
 * 
 * 2 = 2^0 + 3^0
 * 
 * 3 = 21 + 30
 * 
 * 4 = 20 + 31
 * 
 * 5 = 21 + 31
 * 
 * 7 = 22 + 31
 * 
 * 9 = 23 + 30
 * 
 * 10 = 20 + 32
 * 
 * IDEA:
 * Brute force
 * 1. fix the 1st number, iterate all possible powers for the 2nd one
 */
public class Solution970 {

	public List<Integer> powerfulIntegers(int x, int y, int bound) {
		Set<Integer> res = new HashSet<>();
		int[] nums = { x, y };
		Arrays.parallelSort(nums);
		int base = 1;
		while (base < bound) {
			int curBound = bound - base;
			int num = 1;
			while (curBound >= num) {
				res.add(num + base);
				num *= nums[0];
				if (num == 1) {
					break;
				}
			}
			base *= nums[1];
			if (base == 1) {
				break;
			}
		}

		return new ArrayList<>(res);

	}

}
