package problem.dp;

/**
 * Given a string letters, partition letters such that every substring of the partition is a
 * palindrome. Return the minimum cuts needed for a palindrome partitioning of
 * letters. Example 1: Input: letters = "aab" Output: 1 Explanation: The palindrome
 * partitioning ["aa","b"] could be produced using 1 cut.
 * 
 * IDEA:
 * 
 *   a a b a
 * a 1 
 * a   1 
 * b     1
 * a       1
 * 
 *   a a b a
 * a 1 1      <-- fill values just above diagonal if appropriate chars are equal
 * a   1 
 * b     1
 * a       1
 * 
 * 
 * 
 */
public class Solution132 {

    public int minCut(String s) {

        int n = s.length();
        boolean dp[][] = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        char[] letters = s.toCharArray();

        for (int i = 0; i < n - 1; i++) {
           if (letters[i] == letters[i + 1])
                dp[i][i + 1] = true;
        }

        for (int col = 2; col < n; col++) {
            int r = 0, c = col;
            while (r < n && c < n) {
                if (letters[r] == letters[c] && dp[r + 1][c - 1] == true) {
                    dp[r][c] = true;
                }
                r++;
                c++;
            }
        }

        int cuts[] = new int[n];
        cuts[0] = 0;

        for (int k = 0; k < n; k++) {
            if (dp[0][k] == true)
                cuts[k] = 0;
            else
                cuts[k] = cuts[k - 1] + 1;

            for (int i = 1; i < k; i++) {
                if (dp[i][k] == true) {
                    cuts[k] = Math.min(cuts[k], cuts[i - 1] + 1);
                }
            }
        }

        return cuts[n - 1];

    }

}
