package problem.sort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [5,2,6,1] Output: [2,1,1,0]
 * 
 * Explanation: To the right of 5 there are 2 smaller elements (2 and 1). To the
 * right of 2 there is only 1 smaller element (1). To the right of 6 there is 1
 * smaller element (1). To the right of 1 there is 0 smaller element.
 * 
 * IDEA:
 * 
 * Part1: use partitioning technique
 * 
 * left >= pivot > right
 * 
 * [5,2,6,1]
 * 
 * Part2: pick up pivots in desc order, i.e. 6 -> 5 -> 2 -> 1
 * 
 * for each pivot do the following 2 stages:
 * 
 * 1. split initial array into 2 parts: [5,2] >= 6 > [1]
 * 
 * 2. update counter for pivot 3. repeat recursively for left and right parts
 * 
 * 
 *
 */
public class Solution315 {

	private int[] count;
	private int[] index;
	private int[] nums; // will not change during the whole process

	void mergeSort(int left, int right) {
		if (left >= right)
			return;
		int mid = left + (right - left) / 2;

		mergeSort(left, mid);
		mergeSort(mid + 1, right);

		int j = mid + 1;
		for (int i = left; i <= mid; i++) {
			while (j <= right && nums[index[i]] > nums[index[j]])
				j++;
			count[index[i]] += (j - mid - 1);
		}

		merge(left, mid, right);
	}

	void merge(int left, int mid, int right) {
		int[] range = new int[right - left + 1];
		for (int i = left; i <= right; i++) {
			range[i - left] = index[i];
		}

		int i = left, j = mid + 1;
		int idx = left;

		while (i <= mid && j <= right) {
			if (nums[range[i - left]] <= nums[range[j - left]]) {
				index[idx++] = range[i++ - left];
			} else {
				index[idx++] = range[j++ - left];
			}
		}

		while (i <= mid) {
			index[idx++] = range[i++ - left];
		}
	}

	public List<Integer> countSmaller(int[] nums) {
		int n = nums.length;
		this.count = new int[n];
		this.index = new int[n];
		this.nums = nums;

		for (int i = 0; i < n; i++)
			index[i] = i;
		mergeSort(0, n - 1);

		return Arrays.stream(count).boxed().collect(Collectors.toList());
	}

}
