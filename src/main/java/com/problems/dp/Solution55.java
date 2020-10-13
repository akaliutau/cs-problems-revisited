package com.problems.dp;

/**
 * DP
 */
public class Solution55 {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxNumberCovered = 0;
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return nums[0] >= 0;
        } else if (nums[0] == 0) {
            return false;
        }

        boolean[] coverMap = new boolean[n];
        for (int i = 0; i < n - 1; i++) {
            int dist = nums[i];
            if (dist == 0) {// check wall presence
                if (maxNumberCovered <= i) {
                    return false;
                }
            }
            for (int range = i; range <= i + dist; range++) {
                if (range > -1 && range < n) {
                    if (range == n - 1) {
                        return true;
                    }
                    coverMap[range] = true;
                    if (maxNumberCovered < range) {
                        maxNumberCovered = range;
                    }
                }
            }
        }
        return coverMap[n - 1];

    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
