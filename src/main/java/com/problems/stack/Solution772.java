package com.problems.stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, /
 * operators , open ( and closing parentheses ) and empty spaces . The integer
 * division should truncate toward zero.
 * 
 * You may assume that the given expression is always valid. All intermediate
 * results will be in the range of [-2147483648, 2147483647].
 * 
 * Follow up: Could you solve the problem without using built-in library
 * functions.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "1 + 1" Output: 2 Example 2:
 * 
 * Input: s = " 6-4 / 2 " Output: 4
 * 
 * 
 */
public class Solution772 {

	public int calculate(String s) {
		char sign = '+';
		int num = 0;
		Stack<Integer> stack = new Stack<>();
		int i = 0;
		while (i < s.length()) {
			char c = s.charAt(i);
			if (c == '(') {
				int leftCnt = 1;
				int j = i + 1;
				for (; j < s.length() && leftCnt > 0; ++j) {
					if (s.charAt(j) == '(') {
						++leftCnt;
					} else if (s.charAt(j) == ')') {
						--leftCnt;
					}
				}
				num = calculate(s.substring(i + 1, j - 1));
				i = j - 1;
			} else {
				if (Character.isDigit(c)) {
					num = num * 10 + s.charAt(i) - '0';
				}
				if (i == s.length() - 1 || !Character.isDigit(c) && c != ' ') {
					if (sign == '+') {
						stack.push(num);
					} else if (sign == '-') {
						stack.push(-num);
					} else if (sign == '*') {
						stack.push(num * stack.pop());
					} else if (sign == '/') {
						stack.push(stack.pop() / num);
					}
					num = 0;
					sign = c;
				}
				++i;
			}
		}
		int sum = 0;
		while (!stack.isEmpty()) {
			sum += stack.pop();
		}
		return sum;
	}

}
