package com.problems.dp;

/**
 * We partition a row of numbers a into at most k adjacent (non-empty) groups,
 * then our score is the sum of the average of each group. What is the largest
 * score we can achieve? note that our partition must use every number in a, and
 * that scores are not necessarily integers. 
 * 
 * Example: Input: a = [9,1,2,3,9] k = 3 Output: 20 
 * 
 * Explanation: The best choice is to partition a into [9], [1, 2, 3], [9]. 
 * The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20. We could have also
 * partitioned a into [9, 1], [2], [3, 9], for example. That partition would
 * lead to a score of 5 + 2 + 6 = 13, which is worse
 */
public class Solution813 {

    public double largestSumOfAverages(int[] a, int k) {
        int n = a.length;
        double[] sum = new double[n + 1];// cumulative sum[i] = SUM (0..i)
        for (int i = 0; i < n; ++i) {
            sum[i + 1] = sum[i] + a[i];
        }

        double[] dp = new double[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = (sum[n] - sum[i]) / (n - i); // dp[i]  averages on [0..n-i]
        }

        for (int l = 0; l < k - 1; ++l) {// k iterations
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {// all possible 2-part partition
                    dp[i] = Math.max(dp[i], (sum[j] - sum[i]) / (j - i) + dp[j]);
                }
            }
        }

        return dp[0];
    }

}
