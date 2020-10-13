package com.problems.dp;

/**
 * 
 * DP
 * 
 * Number of Dice Rolls With Target Sum
 */
public class Solution1155 {

	public int numRollsToTarget(int d, int f, int target) {
		int mod = 1000_000_007;
		int[] dp = new int[target + 1];
		for (int i = 1; i <= f && i <= target; i++) {
			dp[i] = 1;
		}
		// dp[j] - Number of d Dices Rolls With sum j
		for (int i = 2; i <= d; i++) {
			for (int j = target; j >= 0; j--) {
				dp[j] = 0;
				for (int k = 1; k <= f && j - k > 0; k++) {
					dp[j] = (dp[j] + dp[j - k]) % mod;
				}
			}
		}
		return dp[target];
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
