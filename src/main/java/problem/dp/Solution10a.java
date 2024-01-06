package problem.dp;

/**
 * Given an input string (s) and a pattern (p), implement regular expression
 * matching with support for '.' and '*' 
 * where: '.' Matches any single character.​​​​ 
 *        '*' Matches zero or more of the preceding element. 
 * 
 * The matching should cover the entire input string (not partial). 
 * 
 * Example 1:
 * Input: s = "aa", p = "a" Output: false Explanation: "a" does not match the
 * entire string "aa"
 * 
 * IDEA:
 * 1) start search from the tail
 *		consider pattern = 'wo.d' and text= word
 *		if last symbol is '.' or explicit: remove last symbol, reduce problem to
 *		  pattern = 'wo.' and text= wor
 * 		else have to choose:
 *        1) accept match *=letter and advance in text without advancing in pattern
 *        2) problem to  pattern = 'wo.' and text= wor		
 * 
 * 2) 
 * 3) use dp table as a cache
 * 
 * 4) subproblem
 *    remove the last symbol @pattern for pattern and compare against block 
 *
 */
public class Solution10a {

    boolean match(char[] text, int i, char[] pattern, int j) {

		if (i >= text.length && j >= pattern.length) {
			return true;
		}else if (i >= text.length && j >= tail) {
			return true;
		}  

		// covering all possible cases, in which we can advance pointer either in pattern, in text, or in both
		if (i < text.length && j < pattern.length) {
			if (j < pattern.length - 1 && pattern[j+1] == '*') {
				boolean opOne = false;
				if (pattern[j] == text[i] || pattern[j] == '.') {
					opOne = match(text, i + 1, pattern, j);
				}
				boolean opTwo = match(text, i, pattern, j + 2);
				return opOne || opTwo;
			}else if (pattern[j] == text[i] || pattern[j] == '.') {// check exact match
				boolean opOne = match(text, i + 1, pattern, j + 1);
				return opOne;
			}
		}
		// else terminate recursion
		return false;
	}
    
    int tail;

	public boolean isMatch(String txt, String prn) {
		char[] text = txt.toCharArray();
		char[] pattern = prn.toCharArray();
        tail = pattern.length - 1;
        while (tail > 0 && pattern[tail] == '*'){
            tail -= 2;
        }
        tail ++;
		return match(text, 0, pattern, 0);
	}

}
