package com.problems.array;

/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * 
 */
public class Solution80 {

    public int removeDuplicates(int[] nums) {
        int[] mapped = new int[20002];
        int pos = 0;
        for (int num : nums) {
            if (mapped[num + 10000] < 2) {
                nums[pos++] = num;
                mapped[num + 10000] ++;
            }
        }
        return pos;
    }
	

}
