package problem.hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n,
 * return the number of tuples (i, j, k, l) such that:
 * 
 * 0 <= i, j, k, l < n nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * 
 * 
 * Example 1:
 * 
 * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2] Output:
 * 2 
 * 
 * Explanation: The two tuples are: 
 * 
 * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0 
 * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * IDEA:
 * 1. calculate all possible pairs for 2 arrays (i.e. split the original superset on groups from 2 arrays in group)
 * 2. combine results for each sum in the 1st set which have a -sum in the 2nd
 * 
 * Note, that we have to check only A-B and C-D pairs, because we have to deal with associative sum:
 * 
 * These variants should give the same set of elements
 * (a1 + b1) + (c1 + d1) = 0
 * (a1 + c1) + (b1 + d1) = 0
 * 
 * 
 */
public class Solution454 {
	
	Map<Integer, Integer> sum(int[] a, int[] b) {
		Map<Integer, Integer> result = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				int sum = a[i] + b[j];
				result.compute(sum, (k, v) -> v == null ? 1 : v + 1);
			}
		}
		return result;
	}

	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		Map<Integer, Integer> a = sum(A, B);
		Map<Integer, Integer> b = sum(C, D);

		int count = 0;
		for (int k : a.keySet()) {
			if (b.containsKey(-k)) {
				count += b.get(-k) * a.get(k);
			}
		}
		return count;
	}

}
