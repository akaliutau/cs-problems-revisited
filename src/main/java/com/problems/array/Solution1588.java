package com.problems.array;

/**
 * Given an array of positive integers arr, calculate the sum of all possible
 * odd-length subarrays. A subarray is a contiguous subsequence of the array.
 * Return the sum of all odd-length subarrays of arr. 
 * 
 * Example 1: Input: arr = [1,4,2,5,3] Output: 58 
 * 
 * Explanation: The odd-length subarrays of arr and their
 * sums are: 
 * [1] = 1 
 * [4] = 4 
 * [2] = 2 
 * [5] = 5 
 * [3] = 3 
 * [1,4,2] = 7 
 * [4,2,5] = 11
 * [2,5,3] = 10 
 * [1,4,2,5,3] = 15 
 * 
 * If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 * 
 * IDEA:
 * use prefix sum array and use it as cache of sums for subarryas with lengths = 5, 3, 1
 * 
 *  0  1  2  3  4   5
 * [0, 1, 5, 7, 12, 15]
 * 
 * 
 */
public class Solution1588 {

    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int[] sum = new int[n + 1];
        int total = 0;
        int len = n % 2 == 0 ? n - 1 : n;
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + arr[i]; 
        }        
        while (len > 0) {
            for (int i = 0; i < n && i + len <= n; i++) {
                total += sum[i + len] - sum[i];
            }
            len -= 2;
        }
        return total;

    }

}
