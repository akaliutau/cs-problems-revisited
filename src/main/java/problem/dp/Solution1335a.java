package problem.dp;

import java.util.Arrays;

/**
 * DP
 * 
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To
 * work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
 * 
 * You have to finish at least one task every day. The difficulty of a job
 * schedule is the sum of difficulties of each day of the d days. The difficulty
 * of a day is the maximum difficulty of a job done in that day.
 * 
 * Given an array of integers jobDifficulty and an integer d. The difficulty of
 * the i-th job is jobDifficulty[i].
 * 
 * Return the minimum difficulty of a job schedule. If you cannot find a
 * schedule for the jobs return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2 Output: 7 
 * Explanation: First day
 * you can finish the first 5 jobs, total difficulty = 6. Second day you can
 * finish the last job, total difficulty = 1. The difficulty of the schedule = 6
 * + 1 = 7
 * 
 * 
 * IDEA:
 * 
 * Reducing equation
 * 
 * [6,5,4,3,2,1]
 *      |
 *      
 * [6,5,4] [3,2,1]
 * 
 * 
 * [6,5] [4,3,2,1]
 *   |       max = the most difficult job seen so far
 *   |       
 *   |       
 *  reduced solution
 *         
 *          
 * Time complexity of BF: O(n^d)
 * Time complexity of DP: O(n*d) - theoretical min
 * 
 * In accordance with DP-type problem analysis:
 * 
 * 1. param space: length of input + # of days
 * 2. edge case: d=1: ans = max(of input)
 * 3. state = dp(n,d) - the solution
 * 4. state relations: 
 *        dp(n,d) = min(dp(n-1-i,d-1) + jobDiff(i,1))
 *                   i
 *  
 * Simulation
 * 		[1,1,1]
 *            \
 *            min([1,1][1], [1],[1,1])
 *            /                      \
 *       min([1][1])                 min([1])                               
 *  
 */
public class Solution1335a {
	
	int s(int d, int j, int[] jobDifficulty, int[][] dp) {
		if (d > j) {
			return -1;
		}
		if (d == 0 && j == 0) {
			return jobDifficulty[0];
		}
		if (j == 0) {
			return -1;
		}
		if (d == 0) {
			int max = jobDifficulty[0];
			for (int job = 1; job <=j; job ++) {
				max = Math.max(max, jobDifficulty[job]);
			}
			return max;
		}
		if (dp[d][j] != -1) {
			return dp[d][j];
		}
		int maxDifficulty = Integer.MAX_VALUE;
		int max = -1;
		
		// finding the optimal solution via BF
		// take the tail of array: jobDifficulty
		for (int job = j-1; job >= 0; job--) {
			int difficulty = s(d-1, job, jobDifficulty, dp);// in the 1st iteration == j-1
			// max of the tail
			max = Math.max(max, jobDifficulty[job+1]);// in the 1st iteration == j
			if (difficulty != -1) {// will be updated if at least 1 solution is found
				maxDifficulty = Math.min(maxDifficulty, difficulty + max);
			}
		}
		dp[d][j] = maxDifficulty == Integer.MAX_VALUE ? -1 : maxDifficulty;
		return dp[d][j];
	}

	public int minDifficulty(int[] jobDifficulty, int d) {
		int n = jobDifficulty.length;

		// dp[d][i] - difficulty of schedule for all jobs [0,i] during week consisting from d + 1 days (==baskets)
		int[][] dp = new int[d][n];
		for (int i = 0; i < d; i++)
			Arrays.fill(dp[i], -1);
		
		return s(d-1, n-1, jobDifficulty, dp);
	}
	

}
