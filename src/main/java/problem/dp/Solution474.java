package problem.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of binary strings strs and two integers m and n.
 * 
 * Return the size of the largest subset of strs such that there are at most m
 * 0's and n 1's in the subset.
 * 
 * A set x is a subset of a set y if all elements of x are also elements of y.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3 Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10",
 * "0001", "1", "0"}, so the answer is 4. Other valid but smaller subsets
 * include {"0001", "1"} and {"10", "1", "0"}. {"111001"} is an invalid subset
 * because it contains 4 1's, greater than the maximum of 3.
 * 
 * @author akaliutau
 *
 */
public class Solution474 {

	int dp(String[] strs, int m, int n, int i, Map<String, Integer> map) {

		if (m == 0 && n == 0 || i >= strs.length) // base condition
			return 0;

		StringBuilder sb = new StringBuilder();
		String key = sb.append(m).append(',').append(n).append(',').append(i).toString(); 

		if (map.containsKey(key))
			return map.get(key);

		int zeros = 0, ones = 0, res;
		for (char a : strs[i].toCharArray()) // counting 0's and 1's in the str at index i
			if (a == '0') {
				zeros++;
			}else {
				ones++;
			}

		if (zeros > m || ones > n) {
			res = dp(strs, m, n, i + 1, map); // skip the current string at index i, if it crosses the limit of m or n
		}else {
		   // typical knapsack	condition
			res = Math.max(dp(strs, m, n, i + 1, map), 1 + dp(strs, m - zeros, n - ones, i + 1, map)); 
		}																				
		map.put(key, res); // memoization

		return res;
	}

	public int findMaxForm(String[] strs, int m, int n) {

		return dp(strs, m, n, 0, new HashMap<>());
	}

}
