package com.problems.dp;

import java.util.Arrays;

/**
 *
 */
public class Solution1130 {

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = 0;
                } else if (j == i + 1) {
                    dp[i][j] = arr[i] * arr[j];
                } else {
                    for (int k = i; k < j; k++) {
                        int max1 = 1;
                        for (int p = i; p <= k; p++) {
                            max1 = Math.max(max1, arr[p]);
                        }
                        int max2 = 1;
                        for (int p = k + 1; p <= j; p++) {
                            max2 = Math.max(max2, arr[p]);
                        }
                        dp[i][j] = Math.min(dp[i][k] + dp[k + 1][j] + max1 * max2, dp[i][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
