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
 *  
 *     ""  a 
 *  "" 1   0 
 *  a  0   X
 *  a  0   1
 *  
 * 
 */
public class Solution10 {
	

	public boolean isMatch(String txt, String prn) {
        int n = txt.length();
        int m = prn.length();
        boolean[][] dp = new boolean[n + 1][m + 1];// dp[i][j] - there is a match of pattern[0,j] on the string txt[0,i]
        
        dp[n][m] = true;
        
        char[] text = txt.toCharArray();
        char[] pattern = prn.toCharArray();

        // note: all references are from tail
        for (int i = n; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                boolean firstMatch = (i < n && (pattern[j] == text[i] || pattern[j] == '.'));
                if (j + 1 < m && pattern[j + 1] == '*') {
                    dp[i][j] = (dp[i][j + 2] || firstMatch) && dp[i + 1][j];
                } else {
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

}
