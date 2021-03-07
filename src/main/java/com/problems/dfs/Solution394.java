package com.problems.dfs;

/**
 * 
 * Stack, DFS
 * 
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * 
 * IDEA:
 *  identify recursive (repetitive) actions
 */
public class Solution394 {

	int i = 0;

	String decode(char[] cs) {
		StringBuilder result = new StringBuilder();
		while (i < cs.length && cs[i] != ']') {// exit when cs[i] = ']'
			if (!Character.isDigit(cs[i])) {// this block generates char sequence
				result.append(cs[i++]);
			} else {
				int k = 0;
				// build k until next character is a digit
				while (i < cs.length && Character.isDigit(cs[i])) {
					k = k * 10 + cs[i++] - '0';
				}
				// omit '['
				i++;
				String decoded = decode(cs);
				// omit ']'
				i++;
				// process k[decoded] -> decoded + decoded + ... + decoded k times and append to the result
				while (k-- > 0) {
					result.append(decoded);
				}
			}
		}
		return new String(result);
	}

	public String decodeString(String s) {
		return decode(s.toCharArray());
	}

	

}
