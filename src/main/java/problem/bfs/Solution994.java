package problem.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * In a given grid, each cell can have one of three values:
 * 
 * the value 0 representing an empty cell; the value 1 representing a fresh
 * orange; the value 2 representing a rotten orange. Every minute, any fresh
 * orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
 * 
 * Return the minimum number of minutes that must elapse until no cell has a
 * fresh orange. If this is impossible, return -1 instead.
 * 
 * IDEA:
 * simulate a rottenness propagation
 * 
 */
public class Solution994 {

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, -1, 0, 1 };

	public static int orangesRotting(int[][] grid) {
		int m = grid.length;

		if (m == 0) {
			return 0;
		}
		int n = grid[0].length;

		Queue<int[]> queue = new LinkedList<>(); // BFS: starting with all of the rotten oranges

		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				if (grid[r][c] == 2) {
					queue.add(new int[] { r, c });
				}
			}
		}

		boolean[][] processed = new boolean[m][n];
		int days = -1; // Result (similar to depth level in BFS)

		while (!queue.isEmpty()) {
			List<int[]> toAdd = new ArrayList<>();
			while (!queue.isEmpty()) {
				int[] cell = queue.poll();
				processed[cell[0]][cell[1]] = true;
				for (int k = 0; k < 4; k++) {
					int nr = cell[0] + dr[k];
					int nc = cell[1] + dc[k];

					if (0 <= nr && nr < m && 0 <= nc && nc < n && !processed[nr][nc] && grid[nr][nc] == 1) {
						grid[nr][nc] = 2;
						toAdd.add(new int[] { nr, nc });
					}
				}
			}
			queue.addAll(toAdd);
			days++;
		}

		int sum = 0;

		for (int[] row : grid) {// edge cases
			for (int v : row) {
				sum += v;
				if (v == 1) {
					return -1;
				}
			}
		}

		return sum == 0 ? 0 : days; // Empty grid if sum == 0
	}



}
