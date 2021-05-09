package problem.dp;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You
 * may assume that the maximum length of s is 1000. Example 1: Input: "bbbab"
 * Output: 4 One possible longest palindromic subsequence is "bbbb". 
 * 
 * IDEA:
 * find LCS for string and its reversed replica
 * 
 */
public class Solution516 {

    public int longestPalindromeSubseq(String s) {
        StringBuilder sb = new StringBuilder(s);
        char[] str1 = s.toCharArray();
        char[] str2 = sb.reverse().toString().toCharArray();
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][n];

    }

}
