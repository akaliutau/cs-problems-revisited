package com.problems.binarysearch;

/**
 * You are given a sorted array consisting of only integers where every element
 * appears exactly twice, except for one element which appears exactly once.
 * Find this single element that appears only once. Follow up: Your solution
 * should run in O(log n) time and O(1) space. 
 * 
 * Example 1: Input: nums =
 * [1,1,2,3,3,4,4,8,8] Output: 2
 * 
 * [1,1,2,3,3,4,4,8,8]
 *  |               |
 *  l(0)            r(8)
 *    
 * [1,1,2,3,3,4,4,8,8]
 *  |       |       |
 *          mid(4) 
 *          != mid +1 => missing number is to the left of mid!
 *          
 */
public class Solution540 {

    public int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean even = (hi - mid) % 2 == 0;
            if (nums[mid + 1] == nums[mid]) {
                if (even) {
                    lo = mid + 2;
                } else {
                    hi = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                if (even) {
                    hi = mid - 2;
                } else {
                    lo = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[lo];
    }

}
