package problem.dp;

import java.util.Arrays;

/**
 * Given an array of integers nums and a positive integer k, find whether it's
 * possible to divide this array into k non-empty subsets whose sums are all
 * equal. 
 * 
 * Example 1: Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4 Output: True
 * 
 * Explanation: It's possible to divide it into 4 subsets 
 * (5), (1,4), (2,3), (2,3) with equal sums.
 * 
 * [4, 3, 2, 3, 5, 2, 1]
 *  1                 1
 *     1  1
 *           1     1
 *              1
 * 
 */
public class Solution698 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int target = sum / k;
        if (sum % k > 0 || nums[n - 1] > target) {// must be divisible by k, any ele ( incl. the last - the biggest one) must be < part_sum
            return false;
        }
        int len = 1 << n;// tree of possibilities
        boolean[] dp = new boolean[len];
        dp[0] = true;
        int[] total = new int[len];

        for (int state = 0; state < len; state++) {
            if (!dp[state]) {
                continue;
            }
            for (int i = 0; i < n; i++) {
                int future = state | (1 << i);
                if (state != future && !dp[future]) {
                    if (nums[i] <= target - (total[state] % target)) {
                        dp[future] = true;
                        total[future] = total[state] + nums[i];
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[len - 1];// state = 1111111 
    }

}
