package com.problems.math;

/**
 * Given an integer array, find three numbers whose product is maximum and
 * output the maximum product. 
 * Example 1: Input: [1,2,3] Output: 6
 * 
 * IDEA: 
 * track 
 * 3 maximum numbers in desc order (max1 is the biggest)
 *  and
 * 2 smallest 
 */
public class Solution628 {

    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            
            if (n <= min1) {// min1 is the smallest 
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {// n in [min1, min2]
                min2 = n;
            }
            
            if (n >= max1) {// n >= [max1,max2,max3], max1 is the biggest
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {// n in [max1,max2]
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {// n in [max2,max3]
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);// -5 * -2 * 10 || 2 * 2 * 10

    }

}
