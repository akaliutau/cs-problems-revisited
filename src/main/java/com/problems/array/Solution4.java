package com.problems.array;

/**
 * 
 * Array, Binary search
 */
public class Solution4 {

	public static double median(int[] nums, int len) {
		if (len % 2 == 1) {
			return (double) nums[(len - 1) / 2];
		} else {
			return ((double) nums[len / 2 - 1] + (double) nums[len / 2]) / 2;
		}
	}

	public static int[] merge(int[] arr1, int[] arr2) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		int[] result = new int[len1 + len2];
		int i = 0, j = 0, r = 0;
		while (i < len1 && j < len2) {
			if (arr1[i] <= arr2[j]) {
				result[r] = arr1[i];
				i++;
			} else {
				result[r] = arr2[j];
				j++;
			}
			r++;
		}
		if (i < len1) {
			System.arraycopy(arr1, i, result, r, (len1 - i));
			r += (len1 - i);
		}
		if (j < len2) {
			System.arraycopy(arr2, j, result, r, (len2 - j));
			r += (len2 - j);
		}
		return result;
	}

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int len1 = nums1.length;
		int len2 = nums2.length;

		if (len1 == 0 && len2 == 0) {
			return 0.0;
		} else if (len1 == 0) {
			return median(nums2, len2);
		} else if (nums2.length == 0) {
			return median(nums1, len1);
		} else {
			int[] merged = merge(nums1, nums2);
			return median(merged, merged.length);
		}
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
