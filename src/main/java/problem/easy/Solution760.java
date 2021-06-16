package problem.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two integer arrays nums1 and nums2 where nums2 is an anagram of
 * nums1. Both arrays may contain duplicates.
 * 
 * Return an index mapping array mapping from nums1 to nums2 where mapping[i] =
 * j means the ith element in nums1 appears in nums2 at index j. If there are
 * multiple answers, return any of them.
 * 
 * An array a is an anagram of an array b means b is made by randomizing the
 * order of the elements in a.
 * 
 */
public class Solution760 {
	public int[] anagramMappings(int[] nums1, int[] nums2) {
		Map<Integer, int[]> stat = new HashMap<>();

		int n = nums1.length;
		int[] mapping = new int[n];
		for (int i = 0; i < n; i++) {
			int num = nums2[i];
			int[] nums = stat.computeIfAbsent(num, k -> new int[101]);
			if (nums[0] == 0) {
				nums[0] = 1;
			}
			int idx = nums[0];
			nums[idx] = i;
			nums[0]++;
		}
		for (int i = 0; i < n; i++) {
			int num = nums1[i];
			int idx = stat.get(num)[0]--;
			int mapped = stat.get(num)[idx - 1];
			mapping[i] = mapped;
		}

		return mapping;
	}
}
