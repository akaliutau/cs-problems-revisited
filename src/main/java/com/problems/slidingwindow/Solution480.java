package com.problems.slidingwindow;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Median is the middle value in an ORDERED integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * 
 * Examples: [2,3,4] , the median is 3
 * 
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * 
 * Given an array nums, there is a sliding window of size k which is moving from
 * the very left of the array to the very right. You can only see the k numbers
 * in the window. Each time the sliding window moves right by one position. Your
 * job is to output the median array for each window in the original array.
 * 
 * For example, Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * 
 * Window position           Median 
 * ---------------           ----- 
 * [1 3 -1] -3 5 3 6 7          1   // ordered win : [-1 1 3]    
 * 1 [3 -1  -3] 5 3 6 7        -1 
 * 1 3 [-1 -3 5] 3 6 7         -1 
 * 1 3 -1 [-3 5 3] 6 7          3 
 * 1 3 -1 -3 [5 3 6] 7          5 
 * 1 3 -1 -3 5 [3 6 7]          6 
 * 
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 * 
 * 
 */
public class Solution480 {
	
	void balanceTrees(TreeSet<Integer> minTree, TreeSet<Integer> maxTree) {
	        while (minTree.size() > maxTree.size()) {
	            maxTree.add(minTree.pollFirst());
	        }
	}
	    
	double getMedian(int[] nums, boolean kIsOdd, TreeSet<Integer> minTree, TreeSet<Integer> maxTree) {
	        if (kIsOdd) {
	            return (double) nums[maxTree.first()];
	        } else {
	            return ((double) nums[minTree.first()] + nums[maxTree.first()]) / 2;
	        }
	}

	
	public double[] medianSlidingWindow(int[] nums, int k) {
        // custom comparator, if equal value, compare indices <, else values <
        Comparator<Integer> byIndex = (a, b) -> a - b;
        Comparator<Integer> byValue = (a, b) -> Integer.compare(nums[a], nums[b]);
        Comparator<Integer> comparator = byValue.thenComparing(byIndex);
        
        // minTree that store indices of smaller numbers
        // NOTE: comparator has references to nums array and performs appropriate key sorting!
        TreeSet<Integer> minTree = new TreeSet<>(comparator.reversed());
        
        // maxTree that store indices of bigger numbers
        // NOTE: comparator has references to nums array!
        TreeSet<Integer> maxTree = new TreeSet<>(comparator);
        
        // maxTree will always store equally for 1 element more than minTree
        double[] result = new double[nums.length - k + 1];
        boolean kIsOdd = k % 2 == 1;
        
        // build the window
        for (int i = 0; i < k; i++) {
            minTree.add(i);
        }
        // while sliding/building, if num < left.max, add to left, else add to right, balance trees
        balanceTrees(minTree, maxTree);
        result[0] = getMedian(nums, kIsOdd, minTree, maxTree);
        
        // slide the window for the rest array
        for (int i = k, r = 1; i < nums.length; i++, r++) {
            if (!minTree.remove(i - k)) {
                maxTree.remove(i - k);
            }
            maxTree.add(i);
            minTree.add(maxTree.pollFirst());
            balanceTrees(minTree, maxTree);
            result[r] = getMedian(nums, kIsOdd, minTree, maxTree);
        }
        
        return result;
    }
    
     

}
