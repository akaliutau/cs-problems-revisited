package problem.greedy;

import java.util.LinkedList;

/**
 * Given a non-negative integer num represented as a string, remove k digits
 * from the number so that the new number is the smallest possible
 * 
 * 1234 -> 123
 * 1423 -> 123
 * 1432 -> 132 -> 12
 * 403 -> 3
 * 
 * IDEA: remove disorders
 * 
 */
public class Solution402 {

	public String removeKdigits(String num, int k) {
		LinkedList<Character> stack = new LinkedList<>();// contains the smallest number seen so far, i.e. list of smallest digits in asc order

		for (char digit : num.toCharArray()) {
			while (stack.size() > 0 && k > 0 && stack.peekLast() > digit) {// iterate until stack > current, removing disorders
				stack.removeLast();
				k -= 1;
			}
			stack.addLast(digit);
		}

		// remove the remaining digits from the tail, f.e. 123 -> 12
		for (int i = 0; i < k; ++i) {
			stack.removeLast();
		}

		// build the final string, while removing the [possible] leading zeros.
		StringBuilder result = new StringBuilder();
		boolean leadingZero = true;
		for (char digit : stack) {
			if (leadingZero && digit == '0')
				continue;
			leadingZero = false;
			result.append(digit);
		}

		if (result.length() == 0)
			return "0";
		return result.toString();
	}

}
