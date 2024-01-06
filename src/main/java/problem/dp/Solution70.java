package problem.dp;

/**
 * You are climbing a stair case. It takes n steps to reach to the top. Each
 * time you can either climb 1 or 2 steps. In how many distinct ways can you
 * climb to the top?
 * 
 * IDEA:
 * all paths ending in step i can be formed with 2 endings: i-1 -> i, and i-2 -> i
 * calculate progressive sum for each i >= 2, using already known results
 * 
 */
public class Solution70 {

    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n - 1];

    }

}
