package com.problems.bfs;

import java.util.PriorityQueue;

/**
 * Given a matrix of integers grid with R rows and C columns, find the maximum
 * score of a path starting at [0,0] and ending at [R-1,C-1].
 * 
 * The score of a path is the minimum value in that path. For example, the value
 * of the path 8 → 4 → 5 → 9 is 4.
 * 
 * grid path moves some number of times from one visited cell to any neighbouring
 * unvisited cell in one of the 4 cardinal directions (north, east, west,
 * south).
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: 
 * [ 
 * [5,4,5], 
 * [1,2,6], 
 * [7,4,6]
 * ]
 * 
 * Output: 4 (5 -> 4 -> 5 -> 6 -> 6)
 * 
 * IDEA:
 * 1) the score of the whole path is determined by the min value in the chain 
 *    => the winning strategy will be to avoid min as long until it's possible
 *    
 *    Use  PriorityQueue to pick up next the cell with the max score FIRST, among all neighbors
 *    
 *    As a result all cells in queue will be prioritized with the max score on the top => optimal 
 *    [not necessary the shortest] path 
 * 
 */
public class Solution1102 {

	boolean[][] visited;

	public int maximumMinimumPath(int[][] grid) {
		if (grid == null || grid.length == 0)
			return 0;

		int m = grid.length, n = grid[0].length;
		visited = new boolean[m][n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[2] - a[2]));
		int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
		pq.add(new int[] { 0, 0, grid[0][0] });
		visited[0][0] = true;

		while (!pq.isEmpty()) {
			int[] pair = pq.poll();// the cell with max score of a path so far, result: the first path which reaches the dest is a max one
			int row = pair[0];
			int col = pair[1];
			if (row == m - 1 && col == n - 1) {
				return pair[2];
			}
			for (int[] dir : directions) {
				if (row + dir[0] >= m || row + dir[0] < 0 || col + dir[1] >= n || col + dir[1] < 0)
					continue;
				int nr = row + dir[0];
				int nc = col + dir[1];
				if (visited[nr][nc])
					continue;
				else {
					visited[nr][nc] = true;
					// transfer min value through the grid - because the score of path determined by smallest value
					pq.add(new int[] { nr, nc, Math.min(pair[2], grid[nr][nc]) });
				}
			}
		}
		return -1;
	}


}
