package problem.statistics;

/**
 * You are given an array of strings words and a string chars.
 * 
 * A string is good if it can be formed by characters from chars (each character
 * can only be used once).
 * 
 * Return the sum of lengths of all good strings in words.
 * 
 * Example 1:
 * 
 * Input: words = ["cat","bt","hat","tree"], chars = "atach" Output: 6
 * Explanation: The strings that can be formed are "cat" and "hat" so the answer
 * is 3 + 3 = 6.
 * 
 * @author akaliutau
 *
 */
public class Solution1160 {
	public int countCharacters(String[] words, String chars) {
		int[] stat = new int[26];
		for (char c : chars.toCharArray()) {
			stat[c - 'a']++;
		}
		int len = 0;
		for (String word : words) {
			int[] have = stat.clone();
			boolean canbeFormed = true;
			for (char c : word.toCharArray()) {
				have[c - 'a']--;
				if (have[c - 'a'] < 0) {
					canbeFormed = false;
					break;
				}
			}
			if (canbeFormed) {
				len += word.length();
			}
		}

		return len;
	}
}
