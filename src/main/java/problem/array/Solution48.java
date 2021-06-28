package problem.array;

/**
 * 
 * You are given an n x n 2D matrix representing an image, rotate the image by
 * 90 degrees (clockwise).
 * 
 * You have to rotate the image in-place, which means you have to modify the
 * input 2D matrix directly. DO NOT allocate another 2D matrix and do the
 * rotation.
 *
 */
public class Solution48 {
	public static void moveCircle(int[][] matrix, int left, int right) {
		for (int i = left; i < right; i++) {
			int c = matrix[left][i];
			matrix[left][i] = matrix[right - i + left][left];
			matrix[right - i + left][left] = matrix[right][right - i + left];
			matrix[right][right - i + left] = matrix[i][right];
			matrix[i][right] = c;
		}

	}

	public void rotate(int[][] matrix) {
		int n = matrix.length;
		if (n == 1) {
			return;
		}
		int center = n % 2 == 0 ? n / 2 : n / 2 + 1;

		for (int i = 0; i < center; i++) {
			moveCircle(matrix, i, n - 1 - i);
		}
	}
}
