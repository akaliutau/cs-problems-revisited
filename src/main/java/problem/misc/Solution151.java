package problem.misc;

/**
 * 
 * Given an input string s, reverse the order of the words.
 * 
 * A word is defined as a sequence of non-space characters. The words in s will
 * be separated by at least one space.
 * 
 * Return a string of the words in reverse order concatenated by a single space.
 * 
 * Note that s may contain leading or trailing spaces or multiple spaces between
 * two words. The returned string should only have a single space separating the
 * words. Do not include any extra spaces.
 */
public class Solution151 {

	public String reverseWords(String s) {
		StringBuilder sb = new StringBuilder();
		String[] parts = s.split("\\s+");
		int idx = parts.length - 1;
		for (int i = parts.length - 1; i >= 0; i--) {
			if (!parts[i].equals("")) {
				parts[idx] = parts[i];
				idx--;
			}
		}
		for (int i = parts.length - 1; i > idx; i--) {
			sb.append(parts[i]);
			if (i != idx + 1) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}

}
