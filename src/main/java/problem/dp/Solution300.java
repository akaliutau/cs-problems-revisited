package problem.dp;

/**
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence. Example: Input: [10,9,2,5,3,7,101,18] Output: 4 
 * 
 * Explanation: The
 * longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * 
 * This method relies on the fact that the longest increasing subsequence
 * possible upto the ith index in a given array is independent of the
 * elements coming later on in the array. Thus, if we know the length of the LIS
 * upto i th index, we can figure out the length of the LIS possible by
 * including the (i+1)th elem
 * 
 * O(n^2)
 * 
 * IDEA:
 * 
 */
public class Solution300 {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];//dp[i] =  longest seq on [0,i]
        dp[0] = 1;
        int globalMaxLen = 1;
        
        for (int i = 1; i < n; i++) {
            int maxval = 0;
            int lastElemSeq = nums[i];
            for (int j = 0; j < i; j++) {// looking for the best seq on [0,i]
                if (lastElemSeq > nums[j]) {// each time the order find update the length
                    maxval = Math.max(maxval, dp[j]);// choose the best available seq - 1) to leave as is 2) choose some subsequence ending at [j]
                }
            }
            dp[i] = maxval + 1;// attach: [best available subsequence ending @ j]  + [cur]
            globalMaxLen = Math.max(globalMaxLen, dp[i]);
        }
        return globalMaxLen;
    }

}
