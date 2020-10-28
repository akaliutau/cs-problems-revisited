package com.problems.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order
 * and an integer k. Define a pair (u,v) which consists of one element from the
 * first array and one element from the second array. Find the k pairs
 * (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums. 
 * 
 * Example 1: Input: 
 * nums1 = [1,7,11], nums2 = [2,4,6], k = 3 
 * Output: [[1,2],[1,4],[1,6]] 
 * 
 * Explanation: The first 3 pairs are returned from the sequence:
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 
 * it1 [1,2],[7,2],[11,2] - pick up [1,2], replace it with the next best candidate - [1,4]
 */
public class Solution373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n1 == 0 || n2 == 0) {
            return res;
        }
        
        Comparator<int[]> bySum =  (arr1, arr2) -> {
            int sum1 = nums1[arr1[0]] + nums2[arr1[1]];
            int sum2 = nums1[arr2[0]] + nums2[arr2[1]];
            return sum1 - sum2;
        };
        
        PriorityQueue<int[]> q = new PriorityQueue<int[]>(bySum);// autosort all arrays in such manner that on the top always the smallest sum is placed

        for (int i = 0; i < n1; i++) {
            q.add(new int[] { i, 0 });// add all pairs from array 1, do not add from the 2nd array - memory optimization
        }

        while (!q.isEmpty() && k > 0) {
            int[] arr = q.remove();
            int i1 = arr[0];
            int i2 = arr[1];
            res.add(Arrays.asList(nums1[i1], nums2[i2]));
            if (i2 + 1 < n2) {// add to the chain the next index from the second array
                q.add(new int[] { i1, i2 + 1 });
            }
            k--;
        }
        return res;
    }

}
