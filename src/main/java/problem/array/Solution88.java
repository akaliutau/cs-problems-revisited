package problem.array;

/**
 * 
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as
 * one sorted array.
 * 
 * Note:
 * 
 * The number of elements initialized in nums1 and nums2 are m and n
 * respectively. You may assume that nums1 has enough space (size that is equal
 * to m + n) to hold additional elements from nums2. Example:
 * 
 * Input: nums1 = [1,2,3,0,0,0], m = 3 nums2 = [2,5,6], n = 3
 * 
 * Output: [1,2,2,3,5,6]
 * 
 * IDEA: merge starting from the end for both result and 2 merge pointers
 * 
 */
public class Solution88 {

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int pos1 = m - 1;
		int pos2 = n - 1;
		int pos = nums1.length - 1;

		while (pos > -1) {
			if (pos2 > -1 && pos1 > -1) {
				if (nums2[pos2] > nums1[pos1]) {
					nums1[pos] = nums2[pos2];
					pos2--;
				} else {
					nums1[pos] = nums1[pos1];
					pos1--;
				}
			} else if (pos2 > -1 && pos1 < 0) {
				nums1[pos] = nums2[pos2];
				pos2--;
			} else if (pos1 > -1 && pos2 < 0) {
				nums1[pos] = nums1[pos1];
				pos1--;
			}
			pos--;
		}

	}

}
