package com.problems.dp;

/**
 * DP
 * 
 * dp flow:
 * 
 * 
 * 
 */
public class Solution1335 {

	public int minDifficulty(int[] jobDifficulty, int d) {
		int n = jobDifficulty.length;
		
		// dp[d][i] - difficulty of schedule for all jobs [0,i] during week from d days
		int[][] dp = new int[d][n];

		dp[0][0] = jobDifficulty[0];
		for (int job = 1; job < n; job++) {
			dp[0][job] = Math.max(dp[0][job - 1], jobDifficulty[job]);
		}

		for (int day = 1; day < d; day++) {
			dp[day][0] = -1;
		}

		for (int day = 1; day < d; day++) {
			for (int job = 1; job < n; job++) {
				dp[day][job] = -1;
				if (dp[day - 1][job - 1] != -1) {
					dp[day][job] = dp[day - 1][job - 1] + jobDifficulty[job];
					int max = jobDifficulty[job];
					for (int prev = job - 2; prev >= 0; prev--) {
						max = Math.max(max, jobDifficulty[prev + 1]);
						if (dp[day - 1][prev] != -1) {
							dp[day][job] = Math.min(dp[day][job], dp[day - 1][prev] + max);
						}
					}
				}
			}
		}
		return dp[d - 1][n - 1];
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
