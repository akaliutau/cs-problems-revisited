package com.problems.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than 
 * n/3  times. Example 1: Input: nums = [3,2,3] Output: [3]
 * 
 * [1,1,1,2,3,4,5]
 * 
 * counter for 1:
 *  1 2 3 3 3 3 3 
 * counter for 2:
 *        1 0 
 *          |
 *          2 => 3
 */
public class Solution229 {

    public List<Integer> majorityElement(int[] nums) {

        // 1st pass
        int count1 = 0;
        int count2 = 0;

        Integer candidate1 = null;
        Integer candidate2 = null;

        for (int n : nums) {
            if (candidate1 != null && candidate1 == n) {
                count1++;
            } else if (candidate2 != null && candidate2 == n) {
                count2++;
            } else if (count1 == 0) {//is neither candidate matches and current one expired replace it with new 
                candidate1 = n;
                count1++;
            } else if (count2 == 0) {
                candidate2 = n;
                count2++;
            } else {// n is neither candidate1 OR candidate2, update counters
                count1--;
                count2--;
            }
        }

        // 2nd pass
        List<Integer> result = new ArrayList<>();

        count1 = 0;
        count2 = 0;

        for (int n : nums) {
            if (candidate1 != null && n == candidate1)
                count1++;
            if (candidate2 != null && n == candidate2)
                count2++;
        }

        int n = nums.length;
        if (count1 > n / 3)
            result.add(candidate1);
        if (count2 > n / 3)
            result.add(candidate2);

        return result;
    }

}
