package com.problems.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 * 
 * A clear path from top-left to bottom-right has length k if and only if it is
 * composed of cells C_1, C_2, ..., C_k such that:
 * 
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner) 
 * C_1 is at location (0, 0) (ie. has value grid[0][0]) 
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1]) 
 * 
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0). 
 * 
 * Return the length of the shortest such clear path from top-left to bottom-right. 
 * 
 * If such a path does not exist, return -1.
 * 
 * IDEA:
 * 
 * 
 */
public class Solution1091 {

	public int shortestPathBinaryMatrix(int[][] grid) {

		int n = grid.length;
		if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
			return -1;
		}

		if (n == 1 && grid[0][0] == 0) {
			return 1;
		}

		int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { 0, 0 });
		int distance = 1;
		grid[0][0] = 1; // mark [0, 0] as 1 means it has been visited.
		while (!queue.isEmpty()) {
			distance++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				for (int[] d : directions) {
					int nx = cur[0] + d[0];
					int ny = cur[1] + d[1];
					if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {
						if (nx == n - 1 && ny == n - 1) {// bottom-right corner
							return distance;
						}
						grid[nx][ny] = 1;
						queue.add(new int[] { nx, ny });
					}
				}
			}
		}
		return -1;
	}



}
