package com.problems.binarysearch;

/**
 * Given a sorted array A of unique numbers, find the K-th missing number
 * starting from the leftmost number of the array. 
 * 
 * Example 1: Input: A =
 * [4,7,9,10], K = 1 Output: 5 Explanation: The first missing number is 5.
 * 
 * IDEA:
 * 
 * orig:
 * [4,7,9,10]
 * 
 * full:
 * [4,5,6,7,8,9,10]
 *  |     |
 *  l     r
 * 
 * n-1   0
 * 
 * 10  - 4 = 6
 * 
 * [4,7,9,10]
 *  |   |  |
 *  l   m  r  
 *  
 */
public class Solution1060 {

    // Return how many numbers are missing until n = nums[len]
    int missing(int len, int[] nums) {
        return (nums[len] - nums[0]) - len;
    }

    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        
        if (k > missing(n - 1, nums)) {// If kth missing number is placed after last element of the array
            return nums[n - 1] + (k - missing(n - 1, nums));
        }

        int left = 0, right = n - 1, mid;
        // find mid = (left = right) that missing(left - 1) < k <= missing(left)
        while (left != right) {
            mid = left + (right - left) / 2;

            if (missing(mid, nums) < k) {// note, we are calculating for array [0,mid]
                left = mid + 1;
            }else {
                right = mid;
            }
        }

        // kth missing number is greater than nums[len - 1]
        // and less than nums[len]
        return nums[right - 1] + (k - missing(right - 1, nums));
    }

}
