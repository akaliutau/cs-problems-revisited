package com.problems.dp;

import java.util.Arrays;

/**
 * Given an integer array nums, return the number of longest increasing
 * subsequences. Example 1: Input: nums = [1,3,5,4,7] Output: 2 
 * 
 * Explanation: The
 * two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7]
 */
public class Solution673 {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n];// dp[i] - length of subseq on [0, i] incl
        int[] counts = new int[n];
        Arrays.fill(counts, 1);

        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < j; ++i) {// iterate through all possible pairs (i,j)
                if (nums[i] < nums[j]) {// [i], .. , [j] - increasing seq
                    if (dp[i] >= dp[j]) {// find some short seq which is increasing
                        dp[j] = dp[i] + 1;// added [j] elem
                        counts[j] = counts[i];// no increase in counts, because seq is not the longest
                    } else if (dp[i] + 1 == dp[j]) {
                        counts[j] += counts[i];
                    }
                }
                
            }
        }

        int longest = 0, ans = 0;
        for (int length : dp) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < n; ++i) {// aggregate statistics
            if (dp[i] == longest) {
                ans += counts[i];
            }
        }
        return ans;
    }

}
