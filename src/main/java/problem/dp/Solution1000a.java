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
 * The approach is straightforward:
 * 1. On each iteration: generate all possible arrays. For provided example k=2 it should be:
 *                          [3,2,4,1]
 * 				         /     |        \
 *               [5,4,1]    [3,6,1]     [3,2,5]
 *              /    \      /    \       /      \
 *          [9,1]  [5,5] [9,1]  [3,7]  [5,5]    [3,7]
 *          
 *          
 *          
 * 
 * each possibility can be encoded by reference in the following format:
 * dp[i][j] = minimum cost to merge for array [i,j], where i, j in [0, n-1]
 * 
 * 2. build the tree of possibilities calculating total cost along the way
 * 3. (optional) optimize traversing by terminating branches when we have a better solution
 * 
 */
public class Solution1000a {
	
	int cost = Integer.MAX_VALUE;
	
	private int sum(int[] stones, int i, int sz) {
		int s = 0;
		for (int j = i; j < i + sz; j++) {
			s += stones[j];
		}
		return s;
	}

	
	void generate(int[] stones, int sz, int build) {
		int n = stones.length;
		if (n < sz) {
			return;
		}
		if (n == sz) {
			cost = Math.min(cost, build + sum(stones, 0, sz));
			return;
		}
		for (int i = 0; i < n - 1 - sz; i++) {
			int[] next = new int[1 + n - sz];
			int l = 0;
			for (int j = 0; j < i; j++) {
				next[l++] = stones[j];
			}
			int middleCost = sum(stones, i, sz); 
			next[l++] = middleCost;
			for (int j = i + sz; j < n; j++) {
				next[l++] = stones[j];
			}
			generate(next, sz, middleCost + build);
		}
	}


	public int mergeStones(int[] stones, int k) {
		// 1. check for feasibility
		int n = stones.length;
		int[][] memo = new int[n][n];

		if ((n - 1) % (k - 1) != 0) {
			return -1;
		}
		generate(stones, k, 0);
		return cost == Integer.MAX_VALUE ? -1 : cost;
		
	}

}
