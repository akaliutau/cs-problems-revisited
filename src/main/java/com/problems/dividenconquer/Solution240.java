package com.problems.dividenconquer;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * Integers in each row are sorted in ascending from left to right. Integers in
 * each column are sorted in ascending from top to bottom.
 * 
 */
public class Solution240 {

	public boolean searchMatrix(int[][] matrix, int target) {
		// start our "2d - pointer" in the bottom-left
		int row = matrix.length - 1;
		int col = 0;

		while (row >= 0 && col < matrix[0].length) {
			if (matrix[row][col] > target) {
				row--;
			} else if (matrix[row][col] < target) {
				col++;
			} else { // found it
				return true;
			}
		}

		return false;
	}

	

}
