package com.problems.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted array arr, two integers k and x, find the k closest elements
 * to x in the array. The result should also be sorted in ascending order. If
 * there is a tie, the smaller elements are always preferred.
 * 
 * 
 * Example 1:
 * 
 * Input: arr = [1,2,3,4,5], k = 4, x = 3 Output: [1,2,3,4]
 * 
 *  0 1 2 3 4 5 6 7 8
 * [1,2,3,4,5,6,7,8,9]
 *  |   |   |
 *  l  mid  r=n-4
 * 
 */
public class Solution658 {

	public List<Integer> findClosestElements(int[] arr, int k, int x) {
		int n = arr.length;
		int left = 0;
		int right = n - k;
		List<Integer> ans = new ArrayList<>();
		if (n == 0 || n == 1) {
			return Arrays.stream(arr).boxed().collect(Collectors.toList());
		}
	    
		while (left < right) {
			int mid = (left + right) / 2;
			if (x - arr[mid] > arr[mid + k] - x) {// find [mid, mid + k]
				left = mid + 1;
			} else {
				right = mid;
			}
		}

	    for (int i = left; i < left + k; i++) {
		    ans.add(arr[i]);
		}
		return ans;
	}

}
