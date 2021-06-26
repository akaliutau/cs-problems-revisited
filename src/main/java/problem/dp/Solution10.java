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
 * IDEA for generic REGEX:
 * 
 * 1) start search from the beginning or from end
 *		consider pattern = 'wo.d' and text= word
 *		if last symbol is '.' or a..z then: remove last symbol, reduce problem to sub-problem:
 *		  pattern = 'wo.' and text= wor
 * 		else have to choose:
 *        1) accept match *=letter and advance in text without advancing in pattern
 *        2) advance both in text and in pattern and reduce problem to  pattern = 'wo.' and text= wor		
 * 
 * IDEA for this specific problem:
 * 
 * 
 */
public class Solution10 {
	
	boolean match(char[] text, int i, char[] pattern, int j) {

		if (i >= text.length && j >= pattern.length) {
			return true;
		}else if (i >= text.length && j >= end) {
			return true;
		}  

		if (i < text.length && j < pattern.length) {
			if (j < pattern.length - 1 && pattern[j+1] == '*') {
				boolean opOne = false;
				if (pattern[j] == text[i]) {
					opOne = match(text, i + 1, pattern, j);
				}
                if (opOne){
                    return true;
                }
				return match(text, i, pattern, j + 2);
			}else if (pattern[j] == text[i] || pattern[j] == '.') {
				boolean opOne = match(text, i + 1, pattern, j + 1);
				return opOne;
			}
		}

		return false;
	}
    
    int end;

	public boolean isMatch(String txt, String prn) {
		char[] text = txt.toCharArray();
		char[] pattern = prn.toCharArray();
        end = pattern.length - 1;
        while (end > 0 && pattern[end] == '*'){
            end -= 2;
        }
        end ++;
		return match(text, 0, pattern, 0);
	}

}
