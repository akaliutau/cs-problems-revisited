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
 * 
 *
 */
public class Solution471 {

	String dfs(String s, Map<String, String> memo) {
		int n = s.length();
		if (s == null || n < 5) {
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
		memo.put(s, minEncoded);
		return minEncoded;
	}

	int countRepeats(String s, String pattern) {
		int repeats = 0;
		int i = 0;
		while (i < s.length()) {
			int idx = s.indexOf(pattern, i);
			if (idx == -1) {
				return repeats;
			}
			repeats++;
			i = idx + pattern.length();
		}
		return repeats;
	}

	public String encode(String s) {
		return dfs(s, new HashMap<>());
	}

}
