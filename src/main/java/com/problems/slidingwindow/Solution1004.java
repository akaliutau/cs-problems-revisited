package com.problems.slidingwindow;

/**
 * Sliding window 
 * 
 * Given an array A of 0s and 1s, we may change up to K values
 * from 0 to 1. Return the length of the longest (contiguous) subarray that
 * contains only 1s. 
 * 
 * Input: A =   [1,1,1,0,0,0,1,1,1,1,0], K = 2 
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * 
 * Idea: use [left,right] as contiguous block and dynamically count 0s
 * 
 * |
 * 1,1,1,0,0,0,1,1,1,1,0,0
 *           | k=-1
 * 
 *         |
 * 1,1,1,0,0,0,1,1,1,1,0,0
 *           | k = 0
 * 
 *         |
 * 1,1,1,0,0,0,1,1,1,1,0,0
 *                     | k=-1
 * 
 *           |
 * 1,1,1,0,0,0,1,1,1,1,0,0
 *                       |
 * 
 * 
 */
public class Solution1004 {

    public int longestOnes(int[] arr, int k) {
        int left = 0, right;
        for (right = 0; right < arr.length; right++) {
            // If we included a zero in the window we reduce the value of K.
            // Since K is the maximum zeros allowed in a window.
            if (arr[right] == 0)
                k--;
            // A negative K denotes we have consumed all allowed flips and window has
            // more than allowed zeros, thus increment left pointer by 1 to keep the window
            // size same.
            if (k < 0) {
                // If the left element to be thrown out is zero we increase K.
                if (arr[left] == 0)
                    k++;
                left++;
            }
        }
        return right - left;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
