package com.problems.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * Open brackets must be closed by the same type of brackets. Open brackets must
 * be closed in the correct order.
 * 
 * 
 * Example 1:
 * 
 * Input: s = "()" Output: true
 * 
 */
public class Solution20 {

	static Map<Character, Character> mappings = new HashMap<>();
	static {
		mappings.put(')', '(');
		mappings.put('}', '{');
		mappings.put(']', '[');

	}

	public boolean isValid(String s) {

		// Initialize a stack to be used in the algorithm.
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// If the current character is a closing bracket.
			if (mappings.containsKey(c)) {

				// Get the top element of the stack. If the stack is empty, set a dummy value of ' '
				char topElement = stack.empty() ? ' ' : stack.pop();

				// If the mapping for this bracket doesn't match the stack's top element, return
				// false.
				if (topElement != mappings.get(c)) {
					return false;
				}
			} else {
				// If it was an opening bracket, push to the stack.
				stack.push(c);
			}
		}

		// If the stack still contains elements, then it is an invalid expression.
		return stack.isEmpty();
	}

	

}
