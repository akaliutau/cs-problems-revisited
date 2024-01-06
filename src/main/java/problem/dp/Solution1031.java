package problem.dp;

/**
 * Given an array arr of non-negative integers, return the maximum sum of
 * elements in two non-overlapping (contiguous) subarrays, which have lengths L
 * and M. (For clarification, the L-length subarray could occur before or after
 * the M-length subarray.) Formally, return the largest V for which 
 * V = (arr[i] + arr[i+1] + ... + arr[i+L-1]) + 
 *     (arr[j] + arr[j+1] + ... + arr[j+M-1]) and
 * either: 
 * 0 <= i < (i + L - 1) < j < (j + M - 1) < arr.length, or 
 * 0 <= j < (j + M - 1) < i < (i + L - 1) < arr.length. 
 * 
 * Example 1: Input: arr = [0,6,5,2,2,5,1,9,4],
 * L = 1, M = 2 Output: 20 
 * 
 * Explanation: One choice of subarrays is [9] with length 1, 
 *                                     and [6,5] with length 2. 
 * 
 * Example 2: Input: arr =
 * [3,8,1,3,2,1,8,9,0], L = 3, M = 2 Output: 29 
 * 
 * [3, 8,  1, 3,2, 1, 8, 9, 0]
 * 
 * dpL:
 * [12,12, 6, 6,11,18,17,9, 0]
 * [18,18,18,18,18,18,17,9, 0]
 * 
 * 
 * Explanation: One choice of
 * subarrays is [3,8,1] with length 3, and [8,9] with length 2.
 * 
 * IDEA:
 * 
 */
public class Solution1031 {
    
    // slideSum[0][i] is the sliding window sum arr[i] + ... + arr[i+len-1]
    // slideSum[1][i] is the max sliding window's wum with length=len  starting from arr[i]
    int[][] slideSum(int[] arr, int len) {
        int n = arr.length;
        int[][] res = new int[2][n];

        // calculate the last block's sum of [n-len, n-1]
        for (int i = n - 1; i >= n - len; i--) {
            res[0][n - len] += arr[i];
            res[1][n - len] = res[0][n - len];
        }

        for (int i = n - len - 1; i >= 0; i--) {
            res[0][i] = res[0][i + 1] + (arr[i] - arr[i + len]);// add left, remove right
            res[1][i] = Math.max(res[0][i], res[1][i + 1]);// save the max
        }

        return res;
    }

    public int maxSumTwoNoOverlap(int[] arr, int l, int m) {

        int[][] dpL = slideSum(arr, l);
        int[][] dpM = slideSum(arr, m);

        int res = 0;
        for (int offset = 0; offset < arr.length - (l + m - 1); offset++) {// max = [.........L M]
            // check 2 variants: [L] [M] and [M] [L]
            res = Math.max(res, 
                    Math.max(dpL[0][offset] + dpM[1][offset + l],  dpM[0][offset] + dpL[1][offset + m])
                   );
        }

        return res;
    }

    
}
