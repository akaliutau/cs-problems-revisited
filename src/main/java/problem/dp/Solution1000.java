package problem.dp;

/**
 * 
 * There are n piles of stones arranged in a row. The i-th pile has stones[i]
 * stones.
 * 
 * A move consists of merging exactly k consecutive piles into one pile, and the
 * cost of this move is equal to the total number of stones in these k piles.
 * 
 * Find the minimum cost to cost all piles of stones into one pile. If it is
 * impossible, return -1.
 * 
 * Example 1:
 * 
 * Input: stones = [3,2,4,1], k = 2 Output: 20 
 * 
 * Explanation: We start with [3, 2, 4, 1]. 
 * We cost [3, 2] for a cost of 5, and we are left with [5, 4, 1]. 
 * We cost [4, 1] for a cost of 5, and we are left with [5, 5]. 
 * We cost [5, 5] for a cost of 10, and we are left with [10]. 
 * 
 * The total cost was 20, and this
 * is the minimum possible.
 * 
 * IDEA:
 * The general approach is straightforward:
 * 1. On each iteration: generate all possible arrays. For provided example k=2 it should be:
 *                                [3,2,4,1]                                         <-- 1 X n
 * 				         /     |                 \
 *               [3+2,4,1]    [3,2+4,1]            [3,2,4+1]                        <-- (n-1) X (n-1)
 *              /    \         /     \                  /        \
 *      [3+2+4,1]  [3+2,4+1] [3+2+4,1] [3,2+4+1]    [3+2, 4+1]  [3, 2+4+1]          <-- (n-2) x (n-1) X (n-1)
 *          
 * 
 * The benefit to use memo is obvious: total state space is n^n, and total elements in memo is just n^2
 *          
 * 
 * each possibility can be encoded by reference in the following format:
 * dp[i][j] = minimum cost to cost for array [i,j], where i, j in [0, n-1]
 * 
 * 2. build the tree of possibilities calculating total cost along the way
 * 3. (optional) optimize traversing by terminating branches when we have a better solution
 * 
 * Implementation uses THREE IDEAS:
 * 1. optimal way of summation is the greedy one, when we adding up smaller numbers first, then using blocks to build the bigger ones
 * 2. (as a consequence) use only non-intersection blocks, i.e. all blocks must be aligned with k-size
 * 
 * 
 *                          [2,3,2,4,1]                     <-- super block [0,4]
 * 				         /               \         
 *              [2,3][2,4,1]           [2,3,2,4][1]         <-- build optimal variants: [0,1] + [2,4]  AND [0,3] + [4,4] 
 *              /      \                 /    \    \
 *          [2,3]     [2,4][1]       [2,3]  [2,4]   [1]
 *          
 *          
 *  Tale about 2 paths (optimal and sub-optimal):         
 *                          [2,3,2,4,1]
 * 				         /               \        
 *              [2,3][2,4,1]           [2][3,2][4,1]
 *              /      \                 /       \  
 *           [5]     [6][1]           [2,5]     [5]  
 *           
 *    
 *  3. On each iteration the length decreases on (k-1), because we merge k blocks into 1:
 *     len
 *     len - (k-1)
 *     len - 2*(k-1) 
 *     len - 3*(k-1)
 *     ...
 *     len - t*(k-1) = 1, => len % (k-1) == 1
 *     
 *     MOD(len - t*(k-1)) = MOD(1)
 */
public class Solution1000 {
	
	int sum(int[] stones, int left, int right) {
		int s = 0;
		for (int i = left; i <= right; i++) {
			s += stones[i];
		}
		return s;
	}
	
	int cost(int[] stones, int[][] memo, int k, int left, int right) {
		// ignore all blocks smaller than k - because there is no need to consider non-sync blocks:
		// [3,2,4,1] - [3] [2,4,1]
		int len = right - left + 1;
		if (len < k)
			return 0;

		if (memo[left][right] != 0)
			return memo[left][right];

		if (len == k) {
			memo[left][right] = sum(stones, left, right);
			return memo[left][right];
		}
		// choose optimal variant from block [left, right] and choose the best one
		// we are splitting block [left,right] using mid point at i
		int ans = Integer.MAX_VALUE;
		for (int i = left; i < right; i += k - 1) {// note the increase on blocksize
			ans = Math.min(ans, cost(stones, memo, k, left, i         ) +
					            cost(stones, memo, k,     i + 1, right));
		}
		
		// this block covers the situation [k * n][size < k]
		// tail will give the cost 0 (see line 89)
		// so the cost of merge [left, right] will be sum(k*n block) + sum (size < k block)
		if (k == 2 || len % (k - 1) == 1) { // here len can be only > k
			ans += sum(stones, left, right); 
		}
		memo[left][right] = ans;
		return memo[left][right];
	}

	public int costStones(int[] stones, int k) {
		int n = stones.length;
		if (k != 2 && n % (k - 1) != 1) {// edge case 
			return -1;
		}
		int[][] memo = new int[n][n];
		
		return cost(stones, memo, k, 0, n - 1);
	}
    
}
