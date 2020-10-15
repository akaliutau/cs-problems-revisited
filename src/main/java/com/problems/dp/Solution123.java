package com.problems.dp;

/** 
 * 
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * 
 *
 *    4 4 4 4 3 3 0               
 *    | |
 * [3,3,5,0,0,3,1,4]
 *          |     |
 *  0 0 2 2 2 3 3 4     
 *          
 */
public class Solution123 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;

        int leftMin = prices[0];
        int rightMax = prices[n - 1];

        int[] leftProfits = new int[n];
        // pad the right DP array with an additional zero for convenience.
        int[] rightProfits = new int[n + 1];

        // construct the bidirectional DP array
        for (int l = 1; l < n; ++l) {
            leftProfits[l] = Math.max(leftProfits[l - 1], prices[l] - leftMin);
            leftMin = Math.min(leftMin, prices[l]);
        }

        for (int r = n - 2; r > -1; r--) {
            rightProfits[r] = Math.max(rightProfits[r + 1], rightMax - prices[r]);
            rightMax = Math.max(rightMax, prices[r]);
        }

        int maxProfit = 0;
        for (int i = 0; i < n; ++i) {
            maxProfit = Math.max(maxProfit, leftProfits[i] + rightProfits[i + 1]);
        }
        return maxProfit;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
