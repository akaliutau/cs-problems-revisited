package com.problems.dp;

/**
 * DP
 * 
 * O(n) + O(k)
 */
public class Solution55opt {

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

        for (int i = 0; i < n - 1; i++) {
            int dist = nums[i];
            if (dist == 0) {// check wall presence
                if (maxNumberCovered <= i) {
                    return false;
                }
            }
            int maxJump = i + dist;
            if (maxJump >= n - 1) {
                return true;
            }
            maxNumberCovered = Math.max(maxNumberCovered, maxJump);
            
        }
        return maxNumberCovered >= n - 1;

    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
