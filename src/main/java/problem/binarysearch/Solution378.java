package problem.binarysearch;

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
 * 
 * 1. use 2d binary search
 * 
 *    0  1  2  3  4  5
 *   [1, 4, 5, 5, 8, 9]
 *    |     |        |
 *  low    mid       high
 * 
 *  compare:
 *    elem < arr[mid]  => answer can be only [mid-1] and elements to the left, so update high = mid - 1
 *    elem >= arr[mid] => answer must <em>include</em> elem, so set low = mid + 1  
 *  
 *  as a result this procedure will return the number of elems, LESS OR EQUAL to elem 
 * 
 * 
 * 2. use partitioning on all elements in matrix (less efection, space complexity O(n))
 * 
 */
public class Solution378 {


	int countElemsOnRow(int arr[], int elem) {
		int low = 0;
		int high = arr.length - 1;
		if (elem > arr[high]) {// if elem > max value on the raw, then return the whole line
			return arr.length;
		}
		
		while (low <= high) { // Otherwise use binary search to find the break point 
			int mid = low + (high - low) / 2;
			if (arr[mid] <= elem) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return low;
	}
	
	int getElementsLesserThan(int mid, int[][] matrix) {
		int count = 0;
		for (int i = 0; i < matrix.length; i++) {
			count += countElemsOnRow(matrix[i], mid);
		}
		return count;
	}

	public int kthSmallest(int[][] matrix, int k) {

		int n = matrix.length;

		int min = matrix[0][0];
		int max =  matrix[n-1][n-1];

		int low = min;
		int high = max;

		while (low <= high) {
			int mid = low + (high - low) / 2;
			int midCount = getElementsLesserThan(mid, matrix);
			if (midCount < k) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return low;
	}

}
