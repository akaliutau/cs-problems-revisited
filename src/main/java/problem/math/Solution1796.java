package problem.math;

/**
 * Given an alphanumeric string s, return the second largest numerical digit
 * that appears in s, or -1 if it does not exist.
 * 
 * An alphanumeric string is a string consisting of lowercase English letters
 * and digits.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "dfa12321afd" Output: 2 Explanation: The digits that appear in s
 * are [1, 2, 3]. The second largest digit is 2.
 *
 */
public class Solution1796 {

	public int secondHighest(String s) {
		char digit = (char) ((int) '0' - 1);
		char c = digit;
		for (char ch : s.toCharArray()) {
			if (c < ch && '0' <= ch && ch <= '9') {
				c = ch;
			}
		}
		if (c == digit) {
			return -1;
		}
		char c2 = digit;
		for (char ch : s.toCharArray()) {
			if (c2 < ch && ch != c && '0' <= ch && ch <= '9') {
				c2 = ch;
			}
		}
		if (c2 == digit) {
			return -1;
		}

		return (int) (c2 - '0');

	}

}
