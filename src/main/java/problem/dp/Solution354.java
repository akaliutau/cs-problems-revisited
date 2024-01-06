package problem.dp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You have a number of envelopes with widths and heights given as a pair of
 * integers (w, h). One envelope can fit into another if and only if both the
 * width and height of one envelope is greater than the width and height of the
 * other envelope. What is the maximum number of envelopes can you Russian doll?
 * (put one inside other) Example: Input: [[5,4],[6,4],[6,7],[2,3]] Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3]
 * => [5,4] => [6,7]).
 */
public class Solution354 {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];//dp[i] =  longest seq on [0,i]
        dp[0] = 1;
        int maxLen = 1;
        
        for (int i = 1; i < n; i++) {
            int maxval = 0;
            int cur = nums[i];
            for (int j = 0; j < i; j++) {
                if (cur > nums[j]) {// each time the order find update the length
                    maxval = Math.max(maxval, dp[j]);// choose the best available seq
                }
            }
            dp[i] = maxval + 1;// attach: [best avail seq @ some point j]  + [cur]
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    public int maxEnvelopes(int[][] envelopes) {
        // sort on increasing in first dimension and decreasing in second
        Comparator<int[]> byWidth = (o,p) -> Integer.compare(o[0], p[0]);
        Comparator<int[]> byHeight = (o,p) -> Integer.compare(p[1], o[1]);
        
        Arrays.sort(envelopes, byWidth.thenComparing(byHeight));
        // extract the second dimension and run LIS
        int n = envelopes.length;
        int[] heights = new int[n];
        for (int i = 0; i < n; ++i) {
            heights[i] = envelopes[i][1];
        }
        return lengthOfLIS(heights);
    }

}
