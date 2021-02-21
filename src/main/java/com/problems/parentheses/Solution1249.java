package com.problems.parentheses;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * 
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any
 * positions ) so that the resulting parentheses string is valid and return any
 * valid string.
 * 
 * Formally, a parentheses string is valid if and only if:
 * 
 * It is the empty string, contains only lowercase characters, or It can be
 * written as AB (A concatenated with B), where A and B are valid strings, or It
 * can be written as (A), where A is a valid string.
 * 
 * 
 * Example 1:
 * 
 * Input: s = "lee(t(c)o)de)" Output: "lee(t(c)o)de" 
 * 
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * 
 */
public class Solution1249 {

	public String minRemoveToMakeValid(String s) {
	       char[] str = s.toCharArray();
			int balance = 0;
			StringBuilder sb = new StringBuilder();

			for (char c : str) {
				if (c == '(') {
					balance++;
				} else if (c == ')') {
					balance--;
				} 
	            if (balance < 0) {
					balance = 0;
				}else {
	                sb.append(c);
				}
			}

			balance = 0;
			str = sb.reverse().toString().toCharArray();
			sb.setLength(0);
			for (char c : str) {
				if (c == ')') {
					balance--;
				} else if (c == '(') {
					balance++;
				}
				if (balance > 0) {
					balance = 0;
	            }else {
					sb.append(c);
				}
			}
			return sb.reverse().toString();
	    }
}
