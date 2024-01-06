package problem.dp;

/**
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that
 * amount. You may assume that you have infinite number of each kind of coin.
 * 
 * Example 1: Input: amount = 5, coins = [1, 2, 5] Output: 4 
 * 
 * Explanation: there
 * are four ways to make up the amount: 5=5 5=2+2+1 5=2+1+1+1 5=1+1+1+1+1
 */
public class Solution518 {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
          for (int x = coin; x < amount + 1; ++x) {
            dp[x] += dp[x - coin];
          }
        }
        return dp[amount];
      }
    
}
