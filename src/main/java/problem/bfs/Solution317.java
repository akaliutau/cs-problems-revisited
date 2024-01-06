package problem.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * BFS
 * 
 * You want to build a house on an empty land which reaches all buildings in the
 * shortest amount of distance. You can only move up, down, left and right. You
 * are given a 2D grid of values 0, 1 or 2, where:
 * 
 * Each 0 marks an empty land which you can pass by freely. 
 * 
 * Each 1 marks a building which you cannot pass through. 
 * 
 * Each 2 marks an obstacle which you cannot pass through. 
 * 
 * Example:
 * 
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]], OR
 * 
 *    1 - 0 - 2 - 0 - 1 
 *    |   |   |   |   | 
 *    0 - 0 - 0 - 0 - 0 
 *    |   |   |   |   | 
 *    0 - 0 - 1 - 0 - 0
 * 
 * Output: 7
 * 
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at
 * (0,2), the point (1,2) is an ideal empty land to build a house, as the total
 * travel distance of 3+3+1=7 is minimal. So return 7.
 * 
 * IDEA:
 * 1) perform BFS from each building and save the maps with [accumulated] distances to accDistToBuildings
 * 2) go through all empty cells and analyze only those of them which are reachable from all buildings (use map reachableBuildingsCount)
 * 
 * 
 */
public class Solution317 {

	int[][] dirs = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	int rows, cols;

	// this method will be invoked only on (row,col) coordinates of building 
	void distToEmptyCell(int[][] grid, int[][] accDistToBuildings, int[][] reachableBuildingsCount, int row, int col) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[rows][cols];
		q.add(new int[] { row, col });
		visited[row][col] = true;
		int step = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] vertex = q.poll();
				int r = vertex[0];
				int c = vertex[1];
				accDistToBuildings[r][c] += step;
				reachableBuildingsCount[r][c] ++;
				for (int[] dir : dirs) {
					int nr = r + dir[0];
					int nc = c + dir[1];
					if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc] && grid[nr][nc] == 0) {
						q.add(new int[] { nr, nc });
						visited[nr][nc] = true;
					}
				}
			}
			step++;
		}
	}

	public int shortestDistance(int[][] grid) {
		this.rows = grid.length;
		if (rows == 0) {
			return 0;
		}
		this.cols = grid[0].length;
		int[][] accDistToBuildings = new int[rows][cols];// dist from this cell[i,j] to nearest building
		int[][] reachableBuildingsCount = new int[rows][cols];// the number of buildings one can reach from this cell
		int buildingCount = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 1) {
					distToEmptyCell(grid, accDistToBuildings, reachableBuildingsCount, i, j);
					buildingCount++;
				}
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 0 && buildingCount == reachableBuildingsCount[i][j]) {// count only when possible to reach all buildings
					min = Math.min(min, accDistToBuildings[i][j]);
				}
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

}
