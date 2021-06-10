package problem.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given a string s, encode the string such that its encoded length is the
 * shortest.
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the
 * square brackets is being repeated exactly k times. k should be a positive
 * integer.
 * 
 * If an encoding process does not make the string shorter, then do not encode
 * it. If there are several solutions, return any of them.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "aaa" Output: "aaa" 
 * 
 * Explanation: There is no way to encode it such
 * that it is shorter than the input string, so we do not encode it. Example 2:
 * 
 * Input: s = "aaaaa" Output: "5[a]" 
 * 
 * Explanation: "5[a]" is shorter than "aaaaa"
 * by 1 character. Example 3:
 * 
 * Input: s = "aaaaaaaaaa" Output: "10[a]" 
 * 
 * Explanation: "a9[a]" or "9[a]a" are
 * also valid solutions, both of them have the same length = 5, which is the
 * same as "10[a]". Example 4:
 * 
 * Input: s = "aabcaabcd" Output: "2[aabc]d" 
 * 
 * Explanation: "aabc" occurs twice,
 * so one answer can be "2[aabc]d".
 * 
 * IDEA:
 * optimized brute force
 * on each iteration:
 * 1. try to find repetitions (= a substring no bigger than 1/2)
 * 	  i.e. test the substring against condition:
 *    nnn nnn nnn - note the last segment starts from 1/2 or later
 * 
 * 2. try to cut off head | tail and repeat the encoding on parts
 * 3. choose the min of all options
 *
 */
public class Solution471 {
	
	/*
	 * counts how many repeats we have of pattern in s
	 */
	int countRepeats(String s, String pattern) {
		int repeats = 0;
		int from = 0;
		while (from < s.length()) {
			int idx = s.indexOf(pattern, from);
			if (idx == -1) {
				return repeats;
			}
			repeats++;
			from = idx + pattern.length();
		}
		return repeats;
	}


	String dfs(String s, Map<String, String> memo) {
		int n = s.length();
		if (s == null || n < 5) {// if have aaaa or smaller return it because 4[a] have 4 symbols
			return s;
		}
		if (memo.containsKey(s)) {
			return memo.get(s);
		}
		
		String minEncoded = s;
		
		for (int i = n / 2; i < n; i++) {
			String pattern = s.substring(i);
			if (n % pattern.length() != 0) {
				continue;
			}
			int repeats = countRepeats(s, pattern);
			if (repeats * pattern.length() != n) {
				continue;
			}
			String encoded = repeats + "[" + dfs(pattern, memo) + "]";
			if (encoded.length() < minEncoded.length()) {
				minEncoded = encoded;
			}
		}
		
		for (int i = 1; i < n; i++) {
			String head = dfs(s.substring(0, i), memo);
			String tail = dfs(s.substring(i), memo);
			String encoded = head + tail;
			if (encoded.length() < minEncoded.length()) {
				minEncoded = encoded;
			}
		}
		// at this point we have 3 options
		// 1. leave as is
		// 2. find pattern
		// 3. cut at any point on 2 parts and perform dfs on each part recursively
		// result will be in minEncoded
		memo.put(s, minEncoded);
		return minEncoded;
	}


	public String encode(String s) {
		return dfs(s, new HashMap<>());
	}

}
