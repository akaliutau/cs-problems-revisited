package com.problems.dfs;

/**
 * find out a way you can make one square by using up all those matchsticks. You
 * should not break any stick, but you can link them up, and each matchstick
 * must be used exactly one time. Your input will be several matchsticks the
 * girl has, represented with their stick length. Your output will either be
 * true or false, to represent whether you could make one square using all the
 * matchsticks the little match girl has. 
 * 
 * Example 1: Input: [1,1,2,2,2] Output:
 * true Explanation: You can form a square with length 2, one side of the square
 * came two sticks with length 1.
 */
public class Solution473 {

    boolean dfs(int[] nums, int sides, int target, int index, int sum, boolean[] visited) {
        if (sides == 0) {
            return true;
        }
        
        if (sum > target) {
            return false;
        }
        
        if (sum == target) {
            return dfs(nums, sides - 1, target, 0, 0, visited);
        }
        
        for (int i = index; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (dfs(nums, sides, target, i + 1, sum + nums[i], visited))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }

    public boolean makesquare(int[] nums) {

        if (nums == null || nums.length == 0)
            return false;
        int total = 0;
        int sides = 4;

        for (int num : nums) {
            total += num;
        }

        if (total % sides != 0) {//edge case
            return false;
        }
        
        return dfs(nums, sides, total / sides, 0, 0, new boolean[nums.length]);

    }

}
