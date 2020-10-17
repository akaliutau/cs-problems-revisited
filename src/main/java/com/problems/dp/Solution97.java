package com.problems.dp;

/**
 * Given s1, s2, and s3, find whether s3 is formed by the interleaving of s1 and
 * s2.
 * 
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" Output: true
 * 
 * aa  bc   c          
 *   db  bca
 * 
 * aadbbcbcac
 */
public class Solution97 {
	
	public boolean isInterleave(String s1, String s2, String s3) {
		int n = s1.length();
	    int m = s2.length();
        if (s3.length() != n + m) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        
        // dp[i][j] implies if it is possible to obtain a substring of length (i+j+2) 
        // which is a prefix of s3 by some interleaving of prefixes of strings s1 and s2 
        // having lengths (i+1) and (j+1) respectively
        boolean dp[][] = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int j = 1; j <= m; j++) {
        	dp[0][j] = dp[0][j - 1] && str2[j - 1] == str3[j - 1];
        }
        for (int i = 1; i <= n; i++) {
        	dp[i][0] = dp[i-1][0] && str1[i - 1] == str3[i - 1];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
            	int cur = i + j - 1;
            	// is it possible to continue build taking 1 symbol from s1 || s2 ?
               	dp[i][j] = (dp[i-1][j] && str1[i - 1] == str3[cur]) || (dp[i][j - 1] && str2[j - 1] == str3[cur]);
            }
        }
        return dp[n][m];
    }

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
