package problem.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

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
 * 2. implemented as DFS with switching to special 2-sum case (as an optimization)
 * 
 * detail example
 * [1,0,-1,0,-2,2]
 * [-2,-1,0,0,1,2]
 * 
 * [-2] -> [-1,0,0,1,2]
 * 
 * 
 * 
 */
public class Solution18a {
    
	int len;
	Map<String, Set<List<Integer>>> memo = new HashMap<>();
    
    void twoSum(int[] nums, int target, int start, Set<List<Integer>> res) {
        int left = start;
        int right = len - 1;
        while (left < right){
            int sum = nums[left] + nums[right];
            if (target > sum){
                left ++;
            }else if (target < sum){
                right --;
            }else {
                res.add(Arrays.asList(nums[left], nums[right]));
                    left ++;
                    right --;
            }
        }
    }
    
    void updateResults(Set<List<Integer>> results, Stack<Integer> seq, Set<List<Integer>> collector) {
        for (List<Integer> sub : results) {
    	    List<Integer> completed = new ArrayList<>(seq);
    	    completed.addAll(sub);// append tail
    	    collector.add(completed);
        }
    }

    void kSum(int[] nums, int target, int start, int k, Stack<Integer> seq, Set<List<Integer>> collector) {
        if (k == 0) { 
        	if (target == 0) {
        		List<Integer> completed = new ArrayList<>(seq);
        		collector.add(completed);
        	}
        	return;
        }
        Set<List<Integer>> results = new HashSet<>();
        Stack<Integer> subseq = new Stack<>();
        
        if (k == 2) { 
            twoSum(nums, target, start, results);
            updateResults(results, seq, collector);
            return;
        }
        if (start > len - 1){
            return;
        }
        if (nums[len - 1] * k < target) {
        	return;
        }
        String key = target + ":" + start + ":" + k;
        if (memo.containsKey(key)) {
            updateResults(memo.get(key), seq, collector);
            return;
        }
        
        for (int i = start; i < len; i++) {
        	subseq.add(nums[i]);
        	kSum(nums, target - nums[i], i + 1, k - 1, subseq, results);
        	subseq.pop();
        	kSum(nums, target, i + 1, k, subseq, results);
        }
        updateResults(results, seq, collector);
        memo.put(key, results);
    }
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        Stack<Integer> seq = new Stack<>();
        Set<List<Integer>> res = new HashSet<>();
        kSum(nums, target, 0, 4, seq, res);
        return new ArrayList<>(res);
    }
 
}
