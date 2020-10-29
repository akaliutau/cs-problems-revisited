package com.problems.dp;

/**
 * Given two strings text1 and text2, return the length of their longest common
 * subsequence. A subsequence of a string is a new string generated from the
 * original string with some characters(can be none) deleted without changing
 * the relative order of the remaining characters. (eg, "ace" is a subsequence
 * of "abcde" while "aec" is not). A common subsequence of two strings is a
 * subsequence that is common to both strings. If there is no common
 * subsequence, return 0.
 */
public class Solution1143 {

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];// dp[i][j] - LCS for strings of length i and j

        for (int row = 1; row <= n; row++) {
           for (int col = 1; col <= m; col++) {
                // If the corresponding characters for this cell are the same...
                if (text1.charAt(row-1) == text2.charAt(col-1)) {
                    dp[row][col] = 1 + dp[row - 1][col - 1];
                    // Otherwise they must be different...
                } else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }
            }
        }

        return dp[n][m];
    }

}
