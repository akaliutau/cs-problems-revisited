package problem.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
 * IDEA:
 * The general approach is straightforward:
 * 1. On each iteration: generate all possible arrays. For provided example k=2 it should be:
 *                          [3,2,4,1]
 * 				         /     |        \
 *               [5,4,1]    [3,6,1]     [3,2,5]
 *              /    \      /    \       /      \
 *          [9,1]  [5,5] [9,1]  [3,7]  [5,5]    [3,7]
 *          
 * 
 * The benefit to use memo is obvious: total state space is n^n, and total elems in memo is just n^2
 *          
 * 
 * each possibility can be encoded by reference in the following format:
 * dp[i][j] = minimum cost to merge for array [i,j], where i, j in [0, n-1]
 * 
 * 2. build the tree of possibilities calculating total cost along the way
 * 3. (optional) optimize traversing by terminating branches when we have a better solution
 * 
 * Implementation:
 * 
 * 
 */
public class Solution1000a {
	
	int merge(int[] stones, int[] sum, int[][] memo, int k, int left, int right) {
		if (right - left + 1 < k)
			return 0;

		if (memo[left][right] != 0)
			return memo[left][right];

		if (right - left + 1 == k) {
			memo[left][right] = left == 0 ? sum[right] : sum[right] - sum[left - 1];
			return memo[left][right];
		}
		int ans = Integer.MAX_VALUE;
		for (int i = left; i < right; i += k - 1) {
			ans = Math.min(ans, merge(stones, sum, memo, k, left, i) +
					            merge(stones, sum, memo, k, i + 1, right));
		}
		if (k == 2 || (right - left + 1) % (k - 1) == 1) {
			ans += left == 0 ? sum[right] : sum[right] - sum[left - 1];
		}
		memo[left][right] += ans;
		return memo[left][right];
	}

	public int mergeStones(int[] stones, int k) {
		if (k != 2 && stones.length % (k - 1) != 1)
			return -1;
		int size = stones.length;
		int[] sum = new int[size];
		int[][] memo = new int[size][size];
		sum[0] = stones[0];
		
		for (int i = 1; i < size; i++) {
			sum[i] += sum[i - 1] + stones[i];
		}
		
		return merge(stones, sum, memo, k, 0, size - 1);
	}
    
}
