package problem.stack;

import java.util.Stack;

/**
 * You are given a string s that consists of lower case English letters and
 * brackets.
 * 
 * Reverse the strings in each pair of matching parentheses, starting from the
 * innermost one.
 * 
 * Your result should not contain any brackets.
 * 
 * Example 1:
 * 
 * Input: s = "(abcd)" Output: "dcba"
 * 
 * IDEA:
 * 1. use ( and ) as control operators:
 *  ( == opens new stack
 *  ) == pop the stack, reverse and push back
 * 
 *
 *  (ab(abcd)v)
 *  
 *  stack
 *  ========
 *  [""]
 *  ["ab"]
 *  ["abdcba"]
 *  
 */
public class Solution1190 {
	public String reverseParentheses(String s) {
		Stack<String> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c == '(') {
				stack.add(sb.toString());
				sb = new StringBuilder();
			}else if (c == ')') {
				String res = stack.pop() + sb.reverse();
				sb = new StringBuilder(res);
			}else {
				sb.append(c);
			}
		}
		if (stack.isEmpty()) {
			return sb.toString();
		}
		return stack.pop();
	}

}
