package com.problems.dp;

/**
 * Given an array nums which consists of non-negative integers and an integer m,
 * you can split the array into m non-empty continuous subarrays. Write an
 * algorithm to minimize the largest sum among these m subarrays. 
 * 
 * Example 1:
 * Input: nums = [7,2,5,10,8], m = 2 Output: 18 
 * 
 * Explanation: There are four ways
 * to split nums into two subarrays. The best way is to split it into [7,2,5]
 * and [10,8], where the largest sum among the two subarrays is only 18.
 * 
 * [7,2,5,10,8] = 
 * [7] + SUM[2,5,10,8]
 * [7,2] + SUM[5,10,8]
 * [7,2,5] + SUM[10,8]
 * [7,2,5,10] + SUM[8]
 * 
 */
public class Solution20 {

    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];// dp[i][j] - min sum cutting [0,i] on j parts 
        int[] sum = new int[n + 1];
         
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];// sum[i] = SUM(0,i)
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        dp[0][0] = 0;
        
        for (int i = 1; i <= n; i++) {// i = length of array
            for (int j = 1; j <= m; j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = 0; k < i; k++) {// figure out the solution for SMALLER array using j-1 parts + 1 tail
                    int tailSum = sum[i] - sum[k];
                    ans = Math.min(ans, Math.max(dp[k][j - 1], tailSum));
                }
                dp[i][j] = ans;
            }
        }
        
        return dp[n][m];
    }

}
