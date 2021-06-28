package problem.stack;

import java.util.Stack;

/**
 * 
 * You are given a string s consisting of lowercase English letters. A duplicate
 * removal consists of choosing two adjacent and equal letters and removing
 * them.
 * 
 * We repeatedly make duplicate removals on s until we no longer can.
 */
public class Solution1047 {

	public String removeDuplicates(String s) {

		int n = s.length();
		if (n == 1) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		n = s.length();
		int i = 0;
		while (i < n) {
			char c = s.charAt(i);
			if (!stack.isEmpty()) {
				if (stack.peek().equals(c)) {
					stack.pop();
				} else {
					stack.push(c);
				}
			} else {
				stack.push(c);
			}
			i++;
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		return sb.reverse().toString();

	}

}
