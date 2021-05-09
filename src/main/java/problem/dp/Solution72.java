package problem.dp;

/**
 * 
 * Given two words word1 and word2, find the minimum number of operations
 * required to convert word1 to word2.
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * Insert a character Delete a character Replace a character Example 1:
 * 
 * Input: word1 = "horse", word2 = "ros" Output: 3 
 * Explanation: horse -> rorse
 * (replace 'h' with 'r') rorse -> rose (remove 'r') rose -> ros (remove 'e')
 * 
 */
public class Solution72 {

	public int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		char[] w1 = new char[len1];
		char[] w2 = new char[len2];

		for (int i = 0; i < len1; i++) {
			w1[i] = word1.charAt(i);
		}
		for (int i = 0; i < len2; i++) {
			w2[i] = word2.charAt(i);
		}

		int[][] dp = new int[len1 + 1][len2 + 1];

		// boundary conditions
		for (int i = 0; i <= len2; i++) {
			dp[0][i] = i;
		}

		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}

		// main case

		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (w1[i - 1] == w2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
				}
			}
		}
		return dp[len1][len2];

	}

}
