package problem.string;

/**
 * Given a binary string s ​​​​​without leading zeros, return true​​​ if s
 * contains at most one contiguous segment of ones. Otherwise, return false.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "1001" Output: false Explanation: The ones do not form a
 * contiguous segment.
 *
 */
public class Solution1784 {

	public boolean checkOnesSegment(String s) {
		int lastOneIdx = 0;
		char[] letters = s.toCharArray();
		for (char c : letters) {
			if (c == '0') {
				break;
			}
			lastOneIdx++;
		}
		while (lastOneIdx < letters.length && letters[lastOneIdx] == '0') {
			lastOneIdx++;
		}
		return lastOneIdx == letters.length;
	}
}
