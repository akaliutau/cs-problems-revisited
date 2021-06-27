package problem.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, are there elements
 * a, b, c, and d in nums such that a + b + c + d = target? 
 * Find all unique quadruples in the array which gives the sum of target. 
 * 
 * Notice that the
 * solution set must not contain duplicate quadruples. 
 * 
 * Example 1: Input: nums =
 * [1,0,-1,0,-2,2], target = 0 Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 
 * IDEA:
 * 1. reduce the problem to n-1 case, then to n-2, and so on down to n = 2 for a list of sorted numbers
 * 2. implemented as 2 nested for loops + 1 2 pointer for loop
 * 
 * detail example
 * [1,0,-1,0,-2,2]
 * [-2,-1,0,0,1,2]
 * 
 * [-2] -> [-1,0,0,1,2]
 * 
 * O(n^3)
 * 
 */
public class Solution18 {
    
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> quadruplets = new ArrayList<>();
        for (int i = 0; i < n - 3; i++) {
        	if (i > 0 && nums[i-1] == nums[i]) {
        		continue;
        	}
            for (int j = i + 1; j < n - 2; j++) {
            	if (j > i + 1 && nums[j-1] == nums[j]) {
            		continue;
            	}
            	int left = j + 1;
            	int right = n - 1;
            	while (left < right) {
                   	if (left > j + 1 && nums[left-1] == nums[left]) {
                		continue;
                	}
                   	if (right < n - 1 && nums[right+1] == nums[right]) {
                		continue;
                	}
                   	int sum = nums[i] + nums[j] + nums[left] + nums[right];
                   	if (sum == target) {
                   		quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                   		// found at i, j, left, right
                   		// here we have control over left & right: as we have added them already, so advance indices
                   		// case: [0,1,2,3], tgt = 3
                   		left ++;
                   		right --;
                   	}else if (sum > target){
                   		right --;
                   	}else if (sum < target){
                   		left ++;
                   	}
            	}
            }
        }
        return quadruplets;
    }
 
}
