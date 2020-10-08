package com.problems.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * BFS
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
						if (nx == n - 1 && ny == n - 1) {// bottom-right  corner 
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

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
