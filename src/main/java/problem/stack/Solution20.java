package problem.stack;

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
 * IDEA:
 * 1) add only opening brackets to the stack
 * 2) if bracket is from closing set, try to match with the top of stack
 * 3) in the end check stack for emptiness
 */
public class Solution20 {

	static Map<Character, Character> mapping = new HashMap<>();
	static {
		mapping.put(')', '(');
		mapping.put('}', '{');
		mapping.put(']', '[');

	}

	public boolean isValid(String s) {

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			// If the current character is a closing bracket.
			if (mapping.containsKey(c)) {

				// Get the top element of the stack. If the stack is empty, set a dummy value of ' '
				char topElement = stack.empty() ? ' ' : stack.pop();

				// mapping for this bracket must match the latest element on the stack
				if (topElement != mapping.get(c)) {
					return false;
				}
			} else {
				// If it was an opening bracket, push to the stack.
				stack.push(c);
			}
		}

		// after processing all elements the stack must be empty
		return stack.isEmpty();
	}

	

}
