package problem.dp;

import java.util.Arrays;

/**
 * Given an integer array with all positive numbers and no duplicates, find the
 * number of possible combinations that add up to a positive integer target.
 * 
 * Example: nums = [1, 2, 3] target = 4 
 * 
 * The possible combination ways are: 
 * 
 * (1, 1, 1, 1) 
 * (1, 1, 2) 
 * (1, 2, 1) 
 * (1, 3) 
 * (2, 1, 1) 
 * (2, 2) 
 * (3, 1) 
 * 
 * Note that
 * different sequences are counted as different combinations. Therefore the
 * output is 7.
 * 
 * IDEA:
 * nums = [1, 2, 3]
 * 
 * 
 */
public class Solution377 {
    
    int combinationSum4(int[] nums, int sum, int[] dp) {
        if (sum == 0) {
            return 1;
        }
        if (sum < 0) {
            return 0;
        }
        if (dp[sum] == - 1) {// if calc earlier, use cached value as an answer
            dp[sum] = 0;
            for (int num : nums) {
                dp[sum] += combinationSum4(nums, sum - num, dp);
            }
        }
        return dp[sum];
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];// memo table to hold all combinations for sum=target
        Arrays.fill(dp, -1);
        return combinationSum4(nums, target, dp);
    }
    
    

}
