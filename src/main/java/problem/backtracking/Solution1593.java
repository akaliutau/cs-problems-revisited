package problem.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Given a string s, return the maximum number of unique substrings that the
 * given string can be split into.
 * 
 * You can split string s into any list of non-empty substrings, where the
 * concatenation of the substrings forms the original string. However, you must
 * split the substrings such that all of them are unique.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * Example 1:
 * 
 * Input: s = "ababccc" Output: 5 Explanation: One way to split maximally is
 * ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc']
 * is not valid as you have 'a' and 'b' multiple times.
 * 
 * Example 2:
 * 
 * Input: s = "aba" Output: 2 Explanation: One way to split maximally is ['a', 'ba'].
 * 
 * IDEA:
 * 
 * Optimized brute force on the basis of DFS (backtracking)
 * 
 * ababccc
 * 
 * 
 * Try to perform all possible cuts:
 * 					a -> babccc - 2 unique
 *                  a -> {(b, abccc), (ba, bccc), (bab, ccc)}
 * 
 */
public class Solution1593 {

	int ans = 0;

	void dfs(String s, Set<String> set) { // set is to guarantee uniqueness
		int n = s.length();
		if (s.equals("")) {
			ans = Math.max(ans, set.size());
			return;
		}
		for (int end = 1; end <= n; end++) {
			String substr = s.substring(0, end);
			if (!set.contains(substr)) {// parts must be unique! => omit all wrong possibilities
				set.add(substr);
				dfs(s.substring(end), set);// cut off the first part and continue the procedure with the tail
				set.remove(substr);// backtracking
			}
		}
	}

	public int maxUniqueSplit(String s) {
		Set<String> set = new HashSet<>();
		dfs(s, set);
		return ans;
	}

}
