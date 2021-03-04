package com.problems.binarysearch;

/**
 * 
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Example 1:
 * 
 * Input: [3,4,5,1,2] Output: 1
 * 
 *  IDEA: min elem = point of change
 *  
 *  
 * CASE 1
 * [4,5,6,7,0,1,2]
 *        |
 *        7>0 - then this is the point of change
 * 
 * CASE 2        
 * [4,5,6,7,0,1,2]
 *      |
 *      6>4  point of changes somewhere on the right piece
 *      
 */
public class Solution153 {

	public int findMin(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}

		int left = 0, right = nums.length - 1;

		// if the last element is greater than the first element then there is no
		// rotation.
		if (nums[right] > nums[0]) {
			return nums[0];
		}

		while (right >= left) {
			int mid = left + (right - left) / 2;

			// CASE 1
			// if the mid element is greater than its next element then mid+1 element is the
			// smallest
			// This point would be the point of change. From higher to lower value.
			if (nums[mid] > nums[mid + 1]) {
				return nums[mid + 1];
			}

			// the same as previous if the mid element is lesser than its previous element then mid element is
			// the smallest
			if (nums[mid - 1] > nums[mid]) {
				return nums[mid];
			}

			// CASE 2
			// the least value is somewhere to the right
			if (nums[mid] > nums[0]) {
				left = mid + 1;
			} else {
				// the smallest value is somewhere to the left
				right = mid - 1;// safe, because mid is not the 0 here
			}
		}
		return -1;
	}

	

}
