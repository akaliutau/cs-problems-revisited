package com.problems.dp;

/**
 * There are N piles of stones arranged in a row. The i-th pile has stones[i]
 * stones.
 * 
 * A move consists of merging exactly K consecutive piles into one pile, and the
 * cost of this move is equal to the total number of stones in these K piles.
 * 
 * Find the minimum cost to merge all piles of stones into one pile. If it is
 * impossible, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: stones = [3,2,4,1], K = 2 Output: 20 
 * 
 * Explanation: 
 * We start with [3, 2, 4, 1]. 
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1]. 
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5]. 
 * We merge [5, 5] for a cost of 10, and we are left with [10]. 
 * The total cost was 20, and this
 * is the minimum possible.
 * 
 * 
 */
public class Solution1000 {
	
	int process(int left, int right, int part, int[] arr, int k, int[] preSum, int[][][] dp) {
		if (dp[left][right][part] != 0) {
			return dp[left][right][part];
		}

		if (left == right) {
			return part == 1 ? 0 : -1;
		}

		if (part == 1) {
			int next = process(left, right, k, arr, k, preSum, dp);
			if (next == -1) {
				return -1;
			} else {
				return next + preSum[right + 1] - preSum[left];
			}
		} else {

			int ans = Integer.MAX_VALUE;
			for (int mid = left; mid < right; mid += k - 1) {
				int next1 = process(left, mid, 1, arr, k, preSum, dp);
				int next2 = process(mid + 1, right, part - 1, arr, k, preSum, dp);
				if (next1 != -1 && next2 != -1) {

					ans = Math.min(ans, next1 + next2);
				}
			}
			dp[left][right][part] = ans;
			return ans;
		}

	}

	public int mergeStones(int[] arr, int k) {

		int N = arr.length;
		if ((N - 1) % (k - 1) > 0) {
			return -1;
		}
		int[] preSum = new int[N + 1];
		for (int i = 0; i < N; i++) {
			preSum[i + 1] = preSum[i] + arr[i];
		}

		int[][][] dp = new int[N][N][k + 1];

		return process(0, N - 1, 1, arr, k, preSum, dp);

	}

	

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
