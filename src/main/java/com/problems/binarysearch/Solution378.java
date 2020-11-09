package com.problems.binarysearch;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix. Note that it is
 * the kth smallest element in the sorted order, not the kth distinct element.
 * 
 * Example: matrix = 
 * [ 
 * [ 1, 5, 9], 
 * [10, 11, 13], 
 * [12, 13, 15] 
 * ], k = 8, return 13.
 * 
 * IDEA:
 */
public class Solution378 {

	int getElementsLesserThan(int mid, int[][] matrix) {
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			count += getCount(matrix[i], mid);
		}
		return count;
	}

	int getCount(int arr[], int x) {
		int low = 0;
		int high = arr.length - 1;
		int ans = arr.length;
		if (x > arr[high]) {
			return ans;
		}
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (arr[mid] <= x) {
				low = mid + 1;
			} else {
				ans = mid;
				high = mid - 1;
			}
		}

		return ans;
	}

	public int kthSmallest(int[][] matrix, int k) {

		int n = matrix.length;

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				min = Math.min(min, matrix[i][j]);
				max = Math.max(max, matrix[i][j]);
			}

		}

		int low = min;
		int high = max;
		int ans = -1;
		while (low <= high) {
			int mid = low + (high - low) / 2;
			int midCount = getElementsLesserThan(mid, matrix);
			if (midCount < k) {
				low = mid + 1;
			} else {
				ans = mid;
				high = mid - 1;
			}

		}
		return ans;
	}

}
