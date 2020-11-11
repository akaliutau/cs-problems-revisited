package com.problems.stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus lastSign -, non-negative integers and empty spaces .
 * 
 * Example 3:
 * 
 * Input: "(1+(4+5+2)-3)+(6+8)" Output: 23
 * 
 * IDEA: look at +,- & ), \n as terminal operation
 *       4+5+2   
 *   1+ (     )-3    6 + 8
 * (             )+(       )
 */
public class Solution224 {

	public int calculate(String s) {

		Stack<Integer> stack = new Stack<>();
		Stack<Integer> lastSigns = new Stack<>();
		int lastOperand = 0;
		int result = 0;
		int lastSign = 1;

		for (char ch : s.toCharArray()) {
			if (Character.isDigit(ch)) {
				lastOperand = 10 * lastOperand + (int) (ch - '0');
			} else if (ch == '+') {
				result += lastSign * lastOperand;
				lastSign = 1;
				lastOperand = 0;
			} else if (ch == '-') {
				result += lastSign * lastOperand;
				lastSign = -1;
				lastOperand = 0;
			} else if (ch == '(') {
				stack.push(result);
				lastSigns.push(lastSign);// sign before bracket
				// Reset lastOperand and result, as if new evaluation begins for the new
				// sub-expression
				lastSign = 1;
                lastOperand = 0;
				result = 0;
			} else if (ch == ')') {
				result += lastSign * lastOperand;
				result *= lastSigns.pop();// result of calculations inside bracket
				result += stack.pop();
				// Reset the lastOperand
                lastSign = 1;
				lastOperand = 0;
			}
		}
		return result + (lastSign * lastOperand);
	}

	

}
