package com.problems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Two pointers
 * 
 */
public class Solution15 {
	
	void twoSums(int[] nums, int i, List<List<Integer>> results) {
        int lo = i + 1;// 2nd number
        int hi = nums.length - 1;// 3rd number
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum < 0) {// too small, increase the lowest one
                ++lo;
            } else if (sum > 0) {// too big, decrease the highest one
                --hi;
            } else {
            	results.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
            	lo++;
            	hi--;
                while (lo < hi && nums[lo] == nums[lo - 1]) {// omit equal numbers
                    ++lo;
                }
            }
        }
    }
	
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i) {
            if (i == 0 || nums[i - 1] != nums[i]) {
            	twoSums(nums, i, res);
            }
        }
        return res;
    }
    


	
	public static void main(String[] arg) {
		System.out.println(true);
	}

}
