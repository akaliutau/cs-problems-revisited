package problem.regex;

/**
 * Given an input string (s) and a pattern (p), implement regular expression
 * matching with support for '.' and '*' 
 * where: '.' Matches any single character.​​​​ 
 *        '*' Matches zero or more of any elements. 
 * 
 * The matching should cover the entire input string (not partial). 
 * 
 * @author akaliutau
 *
 */
public class Solution3000 {

	boolean match(char[] text, int i, char[] pattern, int j) {

		if (i > text.length && j > pattern.length) {
			return true;
		} else if (j == pattern.length - 1 && pattern[j] == '*') {
			return true;
		}

		if (i < text.length && j < pattern.length) {
			if (pattern[j] == '.' || pattern[j] == text[i]) {
				return match(text, i + 1, pattern, j + 1);
			} else if (pattern[j] == '*') {
				boolean opOne = match(text, i + 1, pattern, j);
				boolean opTwo = match(text, i + 1, pattern, j + 1);
				boolean opThree = match(text, i, pattern, j + 1);
				return opOne || opTwo || opThree;
			}
		}

		return false;
	}

	public boolean isMatch(String txt, String prn) {
		char[] text = txt.toCharArray();
		char[] pattern = prn.toCharArray();

		return match(text, 0, pattern, 0);
	}

}
