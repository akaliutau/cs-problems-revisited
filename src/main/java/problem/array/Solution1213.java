package problem.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing
 * order, return a sorted array of only the integers that appeared in all three
 * arrays.
 * 
 * Example 1:
 * 
 * Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8] Output:
 * [1,5]
 * 
 * IDEA:
 * 1. find and advance the smallest index first (at any time)
 * 2. equalize one by one
 *
 */
public class Solution1213 {

	public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
		int n = arr1.length;
		int m = arr2.length;
		int k = arr3.length;

		List<Integer> ans = new ArrayList<>();
		int i = 0, j = 0, l = 0;
		while (i < n && j < m && l < k) {

			if (arr1[i] == arr2[j] && arr2[j] == arr3[l]) {// found intersection, advance all pointers
				ans.add(arr1[i]);
				i++;
				j++;
				l++;
			} else {
				if (arr1[i] < arr2[j]) {// l could be smaller, but this case can be processed on the next iteration
					i++;
				} else if (arr2[j] < arr3[l]) {
					j++;
				} else {
					l++;
				}

			}
		}
		return ans;

	}
}
