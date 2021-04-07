package com.problems.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and
 * 1 represents a land cell.
 * 
 * A move consists of walking from one land cell to another adjacent
 * (4-directionally) land cell or walking off the boundary of the grid.
 * 
 * Return the number of land cells in grid for which we cannot walk off the
 * boundary of the grid in any number of moves.
 * 
 * Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]] Output: 3
 * Explanation: There are three 1s that are enclosed by 0s, and one 1 that is
 * not enclosed because its on the boundary.
 * 
 * [
 * [0,1,1,0],
 * [0,0,1,0],
 * [0,0,1,0],
 * [0,0,0,0]
 * ]
 * 
 * IDEA:
 * BFS from edge cells
 * 
 * after that count all cells which are 1s
 * 
 */
public class Solution1020 {
	
	static int[] dir = {-1, 0, 1, 0, 0, -1, 0, 1};
	
	public int numEnclaves(int[][] grid) {
		
		int n = grid.length;
		int m = 0;
		if (n > 0) {
			m = grid[0].length;
		}
		// find all cells which has a boundary on the edge of grid
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			if (grid[i][0] == 1)
				q.add(new int[] {i, 0});
			if (grid[i][m - 1] == 1)
				q.add(new int[] {i, m - 1});
		}
		for (int i = 0; i < m; i++) {
			if (grid[0][i] == 1)
				q.add(new int[] {0, i});
			if (grid[n-1][i] == 1)
				q.add(new int[] {n - 1, i});
		}
		while (!q.isEmpty()) {
			int[] cell = q.poll();
			int x = cell[0];
			int y = cell[1];
			grid[x][y] = 2;
			for (int i = 0; i < 8; i+=2) {
				int nx = x + dir[i];
				int ny = y + dir[i + 1];
				if (nx >= 0 && nx < n && ny >=0 && ny < m && grid[nx][ny] == 1) {
					q.add(new int[] {nx, ny});
					grid[nx][ny] = 2;
				}
			}
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					ans ++;
				}
			}
		}
        return ans;
    }
}
