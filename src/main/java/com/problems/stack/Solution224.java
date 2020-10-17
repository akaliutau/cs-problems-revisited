package com.problems.stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces .
 * 
 * Example 3:
 * 
 * Input: "(1+(4+5+2)-3)+(6+8)" Output: 23
 * 
 */
public class Solution224 {

	public int calculate(String s) {

		Stack<Integer> stack = new Stack<>();
		Stack<Integer> signs = new Stack<>();
		int operand = 0;
		int result = 0;
		int sign = 1;

		for (char ch : s.toCharArray()) {
			if (Character.isDigit(ch)) {
				operand = 10 * operand + (int) (ch - '0');
			} else if (ch == '+') {
				result += sign * operand;
				sign = 1;
				operand = 0;
			} else if (ch == '-') {
				result += sign * operand;
				sign = -1;
				operand = 0;
			} else if (ch == '(') {
				stack.push(result);
				signs.push(sign);
				// Reset operand and result, as if new evaluation begins for the new
				// sub-expression
				sign = 1;
				result = 0;
			} else if (ch == ')') {
				result += sign * operand;
				result *= signs.pop();
				result += stack.pop();
				// Reset the operand
				operand = 0;
			}
		}
		return result + (sign * operand);
	}

	public static void main(String[] arg) {

		System.out.println();

	}

}
