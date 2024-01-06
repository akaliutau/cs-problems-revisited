package problem.string;

import java.util.Stack;

/**
 * Given a string s, determine if it is valid.
 * 
 * A string s is valid if, starting with an empty string t = "", you can
 * transform t into s after performing the following operation any number of
 * times:
 * 
 * Insert string "abc" into any position in t. More formally, t becomes tleft +
 * "abc" + tright, where t == tleft + tright. Note that tleft and tright may be
 * empty.
 * 
 * Return true if s is a valid string, otherwise, return false.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "aabcbc" Output: true Explanation: "" -> "abc" -> "aabcbc" Thus,
 * "aabcbc" is valid.
 * 
 * IDEA:
 * 
 * 1) always add a|b
 * 2) if c, try to poll exactly b->a (do not add c though)
 * 3) continue until hav letters
 * 
 * in the end check the emptiness of the stack
 */
public class Solution1003 {
	public boolean isValid(String s) {
		char[] str = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		for (char c : str) {
			if (c == 'a' || c == 'b') {
				stack.add(c);
			} else {
				if (stack.empty() || stack.peek() != 'b') {
					return false;
				}
				stack.pop();
				if (stack.empty() || stack.peek() != 'a') {
					return false;
				}
				stack.pop();
			}
		}
		return stack.empty();
	}
}
