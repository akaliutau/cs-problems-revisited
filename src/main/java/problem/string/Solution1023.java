package problem.string;

import java.util.ArrayList;
import java.util.List;

/**
 * A query word matches a given pattern if we can insert lowercase letters to
 * the pattern word so that it equals the query. (We may insert each character
 * at any position, and may insert 0 characters.)
 * 
 * Given a list of queries, and a pattern, return an answer list of booleans,
 * where answer[i] is true if and only if queries[i] matches the pattern.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: queries =
 * ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern =
 * "FB" Output: [true,false,true,true,false] Explanation: "FooBar" can be
 * generated like this "F" + "oo" + "B" + "ar". "FootBall" can be generated like
 * this "F" + "oot" + "B" + "all". "FrameBuffer" can be generated like this "F"
 * + "rame" + "B" + "uffer".
 * 
 * IDEA:
 * find all occurrences of symbols from pattern in string, letter by letter
 * Note the additional checks for Uppercase letters on the tail and between
 *
 */
public class Solution1023 {

	static boolean match(String s, char[] pattern) {
		char[] lets = s.toCharArray();
		int i = 0;
		int pi = 0;
		while (pi < pattern.length && i < lets.length) {
			boolean found = false;
			while (i < lets.length) {
				char c = lets[i++];
				if (c == pattern[pi]) {
					found = true;
					break;
				}
				if (Character.isUpperCase(c)) {
					return false;
				}
			}
			if (!found) {
				return false;
			}
			pi++;// advance to the next symbol
		}
		// check the tail of string against upper case letters
		while (i < lets.length) {
			if (Character.isUpperCase(lets[i++])) {
				return false;
			}
		}
		
		return pi == pattern.length;
	}

	public List<Boolean> camelMatch(String[] queries, String pattern) {
		char[] p = pattern.toCharArray();
		List<Boolean> ans = new ArrayList<>();
		for (String query : queries) {
			ans.add(match(query, p));
		}
		return ans;
	}
}
