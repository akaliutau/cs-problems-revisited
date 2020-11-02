package com.problems.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an m x n matrix of non-negative integers representing the height of
 * each unit cell in a continent, the "Pacific ocean" touches the left and top
 * edges of the matrix and the "Atlantic ocean" touches the right and bottom
 * edges.
 * 
 * Water can only flow in four directions (up, down, left, or right) from a cell
 * to another one with height equal or lower.
 * 
 * Find the list of grid coordinates where water can flow to both the Pacific
 * and Atlantic ocean.
 * 
 * Example:
 * 
 * Given the following 5x5 matrix:
 * 
 * Pacific    ~  ~  ~  ~  ~ 
 *         ~  1  2  2  3 (5) * 
 *         ~  3  2  3 (4)(4) * 
 *         ~  2  4 (5) 3  1  * 
 *         ~ (6)(7) 1  4  5  * 
 *         ~ (5) 1  1  2  4  * 
 *            *  *  *  *  *  Atlantic
 * 
 * Return:
 * 
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with
 * parentheses in above matrix)
 * 
 * IDEA: find the points which are peaks and meeting points in the same time 
 * 
 */
public class Solution417 {
	
	static int[][] dirs = {{0,-1},{0,1},{1,0},{-1,0}}; 
	
	void flow(int[][] matrix, int row, int col, int[][] count, int prev, int val) {
		if (row < 0. || row == matrix.length || col < 0 || col == matrix[row].length) {
			return;
		}

		// exit if visited             meeting point           cannot flow up
		if (count[row][col] == val || count[row][col] == 3 || matrix[row][col] < prev) {
			return;
		}

		count[row][col] += val;// count[row][col] = {0,1,2}
		
		for (int[] dir : dirs) {
			flow(matrix, row + dir[0], col + dir[1], count, matrix[row][col], val);
		}
	}

	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return Collections.emptyList();
		}
		int n = matrix.length;
		int m = matrix[0].length;

		int[][] memo = new int[n][m];

		for (int i = 0; i < n; i++) {// top/bottom
			// pacific val = 1
			flow(matrix, i, 0, memo, 0, 1);
			// atlantic val = 2
			flow(matrix, i, m - 1, memo, 0, 2);
		}

		for (int j = 0; j < m; j++) {// left/right
			// pacific val = 1
			flow(matrix, 0, j, memo, 0, 1);

			// atlantic val = 2
			flow(matrix, n - 1, j, memo, 0, 2);
		}

		// coordinates => pacific_val: 1 + atlantic_val 2 == 3
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (memo[i][j] == 3) {// meeting point
					res.add(Arrays.asList(i, j));
				}
			}
		}

		return res;
	}

	
}
