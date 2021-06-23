package problem.recursion;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * You are given an integer array nums and an integer target.
 * 
 * You want to build an expression out of nums by adding one of the symbols '+'
 * and '-' before each integer in nums and then concatenate all the integers.
 * 
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1
 * and concatenate them to build the expression "+2-1". Return the number of
 * different expressions that you can build, which evaluates to target.
 * 
 * Example 1:
 * 
 * Input: nums = [1,1,1,1,1], target = 3 Output: 5 Explanation: There are 5 ways
 * to assign symbols to make the sum of nums be target 3.
 * 
 * -1 + 1 + 1 + 1 + 1 = 3
 * 
 * +1 - 1 + 1 + 1 + 1 = 3
 * 
 * +1 + 1 - 1 + 1 + 1 = 3
 * 
 * +1 + 1 + 1 - 1 + 1 = 3
 * 
 * +1 + 1 + 1 + 1 - 1 = 3
 * 
 * IDEA:
 * use optimized Brute Force with memoization:
 * 1. start from sum == 0 and the 1st number: it could be added to the final sum either with + or -
 *    calculate 2 choices, advancing the index of number on each iteration and calculating the running sum
 * 2. terminal operation: compare the running sum with target and return 1 (found one more solution) or 0
 *
 * Note about memo data structure:
 * memoize the state, which is fully defined by 2 params: i & sum
 * 
 */
public class Solution494 {

	int calculate(int[] nums, int i, int sum, int tgt, Map<Long,Integer> memo) {
		if (i == nums.length) {
			return (sum == tgt) ? 1 : 0;
		} else {
			long key = i * 10000 + sum;
			if (memo.containsKey(key)) {
				return memo.get(key);
			}
			// investifate 2 choices
			int add   =    calculate(nums, i + 1, sum + nums[i], tgt, memo);
			int subtract = calculate(nums, i + 1, sum - nums[i], tgt, memo);
			memo.put(key, add + subtract);
			return add + subtract;
		}
	}

	public int findTargetSumWays(int[] nums, int S) {
		Map<Long,Integer> memo = new HashMap<>();
		return calculate(nums, 0, 0, S, memo);
	}

}
