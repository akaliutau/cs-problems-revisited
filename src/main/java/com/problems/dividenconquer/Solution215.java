package com.problems.dividenconquer;

/**
 * 
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * Example 1:
 * 
 * Input: [3,2,1,5,6,4] and k = 2 Output: 5
 * 
 */
public class Solution215 {

	int[] nums;

	void swap(int a, int b) {
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}

	// Partition the array into 2 halves using a pivot
	// The left half will contain all elements greater than the pivot
	// The right half will contain all elements smaller than the pivot

	int partition(int left, int right) {
		int mid = (left + right) / 2;
		int pivot = nums[mid];
		swap(mid, right);

		int curr = left;

		for (int i = left; i < right; i++) {
			if (nums[i] > pivot) {
				swap(i, curr);
				curr++;
			}
		}

		swap(curr, right);

		return curr;
	}

	public int findKthLargest(int[] nums, int k) {
		int left = 0, right = nums.length - 1;
        this.nums = nums;

		while (left < right) {
			// Partition the array into 2 halves using a pivot
			int partition = partition(left, right);
			// After partition, we know the rank of the pivot
			// so just compare the rank of that pivot with k, if equal then we found the
			// element
			if (partition == k - 1)
				return nums[partition];

			// If the pivot has the higher rank (notice that the smaller the index, the
			// higher the rank)
			// we search the left half of the array, since we know that the element cannot
			// be in the other half
			else if (partition < k - 1)
				left = partition + 1;
			// Otherwise we go right
			else
				right = partition - 1;
		}

		return nums[left];
	}

}
