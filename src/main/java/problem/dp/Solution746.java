package problem.dp;

/**
 * You are given an integer array cost where cost[i] is the cost of ith step on
 * a staircase. Once you pay the cost, you can either climb one or two steps.
 * 
 * You can either start from the step with index 0, or the step with index 1.
 * 
 * Return the minimum cost to reach the top of the floor.
 * 
 * Example 1:
 * 
 * Input: cost = [10,15,20] Output: 15 
 * 
 * Explanation: Cheapest is: start on
 * cost[1], pay that cost, and go to the top.
 * 
 * IDEA:
 * 1. Any position [i] on the staircase can be reached by 2 ways only: from [i-1] and [i-2]
 * 2. So start from i=2 and use dp as a cache of already calculated steps
 *
 */
public class Solution746 {
	public int minCostClimbingStairs(int[] cost) {
		int n = cost.length;
		int[] dp = new int[n + 1];// dp[i] - the cost to reach the step [i]

		for (int i = 2; i <= n; i++) {
			int takeOneStep = dp[i - 1] + cost[i - 1];
			int takeTwoSteps = dp[i - 2] + cost[i - 2];
			dp[i] = Math.min(takeOneStep, takeTwoSteps);
		}

		// The final element in minimumCost refers to the top floor
		return dp[n];
	}
}
