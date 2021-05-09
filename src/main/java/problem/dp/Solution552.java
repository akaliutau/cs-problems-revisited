package problem.dp;

/**
 * Given a positive integer n, return the number of all possible attendance
 * records with length n, which will be regarded as rewardable. The answer may
 * be very large, return it after mod 109 + 7. A student attendance record is a
 * string that only contains the following three characters: 
 * 'A' : Absent. 
 * 'L' : Late. 
 * 'P' : Present. 
 * 
 * A record is regarded as rewardable if it doesn't contain
 * more than one 'A' (absent) or more than two continuous 'L' (late). 
 * 
 * Example 1:
 * Input: n = 2 Output: 8 Explanation: There are 8 records with length 2 will be
 * regarded as rewardable: "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL" Only
 * "AA" won't be regarded as rewardable owing to more than one absent times.
 * 
 * IDEA:
 * 
 * 
 */
public class Solution552 {

    static int MOD = 1000000007;
    Long[][][] dp;

    long dfs(int absent, int late, int i, int n) {
        if (i == n) {
            return 1;
        }

        if (dp[absent][late][i] != null) {// return cached value
            return dp[absent][late][i];
        }

        long local = 0L;

        // A
        if (absent == 0) {
            local += dfs(1, 0, i + 1, n);
        }
        // L
        if (late < 2) {
            local += dfs(absent, late + 1, i + 1, n);
        }
        // P
        local += dfs(absent, 0, i + 1, n);

        dp[absent][late][i] = local % MOD;
        return dp[absent][late][i];
    }

    public int checkRecord(int n) {
        if (n == 0)
            return 0;

        dp = new Long[2][3][n + 1];

        return (int) (dfs(0, 0, 0, n) % MOD);
    }

}
