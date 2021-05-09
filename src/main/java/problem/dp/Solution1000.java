package problem.dp;

/**
 * 
 * There are n piles of stones arranged in a row. The i-th pile has stones[i]
 * stones.
 * 
 * A move consists of merging exactly k consecutive piles into one pile, and the
 * cost of this move is equal to the total number of stones in these k piles.
 * 
 * Find the minimum cost to merge all piles of stones into one pile. If it is
 * impossible, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: stones = [3,2,4,1], k = 2 Output: 20 
 * 
 * Explanation: We start with [3, 2, 4, 1]. 
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1]. 
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5]. 
 * We merge [5, 5] for a cost of 10, and we are left with [10]. 
 * 
 * The total cost was 20, and this
 * is the minimum possible.
 * 
 * 
 */
public class Solution1000 {

	public int mergeStones(int[] stones, int k) {
		// 1. check for feasibility
		int n = stones.length;

		if ((n - 1) % (k - 1) != 0) {
			return -1;
		}
		// calculate partial sums
		int[] sum = new int[n + 1];
		for (int i = 0; i < n; i++) {
			sum[i + 1] = sum[i] + stones[i];// sum[i] = sum of stones [0,i]
		}
		
		// 2. populate dp
		int[][] dp = new int[n][n];// dp[i][j] - cost of merge of piles on [i,j]
		
		for (int l = k; l <= n; l++) {
			// analyse all intervals of length l 
			// [3,2,4,1]
			// l = 2: [3,2], [2,4], [4,1]

			for (int i = 0; i <= n - l; i++) {// analysis of all [i,j] of length =l
				int j = i + l - 1;
				dp[i][j] = Integer.MAX_VALUE;
				// mid cuts i-j into blocks of min possible length (i.e. k)
				for (int mid = i; mid < j; mid += k - 1) {
					int cost = dp[i][mid] + dp[mid + 1][j];
					dp[i][j] = Math.min(dp[i][j], cost);
				}
				if ((j - i) % (k - 1) == 0) {// also add the total cost - last 1 elem, because length = 1 omitted in cycle
					dp[i][j] += sum[j + 1] - sum[i];
				}
			}
		}
		// 3. return result
		return dp[0][n - 1];
	}

}
