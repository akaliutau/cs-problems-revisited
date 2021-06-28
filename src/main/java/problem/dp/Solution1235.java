package problem.dp;

import java.util.Arrays;

/**
 * 
 * We have n jobs, where every job is scheduled to be done from startTime[i] to
 * endTime[i], obtaining a profit of profit[i].
 * 
 * You're given the startTime, endTime and profit arrays, return the maximum
 * profit you can take such that there are no two jobs in the subset with
 * overlapping time range.
 * 
 * If you choose a job that ends at time X you will be able to start another job
 * that starts at time X.
 * 
 * IDEA:
 * use DP (Brute Force + memoization)
 * 
 * 1. sort jobs by the time of start
 * 2. investigate 2 possibilities (make the choice):
 *    1) pick up current job in the row and attach the next one which starts not earlier then this ends (use binary search)
 *    2) do not pick up current job (skipProfit)
 * 3. make the choice - choose the best and update the memo
 * 
 * Thoughts about memo structure:
 *  
 * int[] memo - contains the max profit obtained from jobs [i, i+1, ..., n-1] 
 * 
 * O(n^2) or O(n log n)
 *  
 */
public class Solution1235 {

	static class Job {
		int start;
		int end;
		int profit;

		Job(int start, int end, int profit) {
			this.start = start;
			this.end = end;
			this.profit = profit;
		}
	}

	int maxProfit(Job[] jobs, int[] memo, int idx) {
		int n = jobs.length;
		if (idx == n || idx == -1)
			return 0;
		
		if (memo[idx] != -1)
			return memo[idx];

		int maxProfit = 0;
		int workProfit = jobs[idx].profit
				+ maxProfit(jobs, memo, findNextJob(jobs, jobs[idx].end, idx + 1));
		int skipProfit = maxProfit(jobs, memo, idx + 1);
		maxProfit = Math.max(workProfit, skipProfit);
		memo[idx] = maxProfit;
		return maxProfit;
	}
	
	// Linear Search
	// find the job which starts AFTER target, starting from i = prevIndex
	int findNextJob(Job[] jobs, int target, int from) {
		for (int i = from; i < jobs.length; i++) {
			if (jobs[i].start >= target) {
				return i;
			}
		}
		return -1;
	}

	// Binary Search
	// 
	int findNextJobOpt(Job[] jobs, int target, int from) {
		int low = from;
		int high = jobs.length - 1;
		int result = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (jobs[mid].start < target) {
				low = mid + 1;
			} else {
				result = mid;
				high = mid - 1;
			}
		}
		return result;
	}
	
	public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int n = profit.length;
		Job[] jobs = new Job[n];
		for (int i = 0; i < n; i++)
			jobs[i] = new Job(startTime[i], endTime[i], profit[i]);

		Arrays.sort(jobs, (o, p) -> o.start - p.start);
		int[] memo = new int[n];
		Arrays.fill(memo, -1);
		return maxProfit(jobs, memo, 0);
	}


}
