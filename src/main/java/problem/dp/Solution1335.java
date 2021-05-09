package problem.dp;

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
 * [6,5,4,3,2,1]
 *      |
 *      
 * [6,5,4] [3,2,1]
 *  
 * [6,5] [4,3,2,1]
 *          max = the most difficult job seen so far
 *  
 */
public class Solution1335 {

	public int minDifficulty(int[] jobDifficulty, int d) {
		int n = jobDifficulty.length;

		// dp[d][i] - difficulty of schedule for all jobs [0,i] during week consisting from d + 1 days (==baskets)
		int[][] dp = new int[d][n];

		dp[0][0] = jobDifficulty[0];
		for (int job = 1; job < n; job++) {// for 1 day
			dp[0][job] = Math.max(dp[0][job - 1], jobDifficulty[job]);
		}

		for (int day = 1; day < d; day++) {// no jobs
			dp[day][0] = -1;
		}

		for (int day = 1; day < d; day++) {
			for (int job = 1; job < n; job++) {
				if (dp[day - 1][job - 1] != -1) {
					dp[day][job] = dp[day - 1][job - 1] + jobDifficulty[job];// by definition of schedule(day) = schedule(without cur day) + job difficulty at cur day
					int max = jobDifficulty[job];
					for (int j = job - 2; j >= 0; j--) {//go through all combinations (dp[day][j], max is the MAX(difficulty of job on (j..n))
						max = Math.max(max, jobDifficulty[j + 1]);// max on the block [4,3,2,1]
						if (dp[day - 1][j] != -1) {
							dp[day][job] = Math.min(dp[day][job], dp[day - 1][j] + max);
						}
					}
				}else{
					dp[day][job] = -1;// all dp where jobs < days = -1
				}
			}
		}
		return dp[d - 1][n - 1];
	}

	

}
