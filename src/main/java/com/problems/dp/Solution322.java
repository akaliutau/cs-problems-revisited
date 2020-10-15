package com.problems.dp;

import java.util.Arrays;

/**
 *
 */
public class Solution322 {

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (amount == 0) {
            return 0;
        }
        int coin = coins.length;
        if (coin == 0) {
            return -1;
        }
        Arrays.sort(coins);

        int maxAmount = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, maxAmount);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coin; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
