package problem.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for
 * each cell.
 * 
 * The distance between two adjacent cells is 1.
 * 
 * Example 1:
 * 
 * Input: 
 * [
 * [0,0,0], 
 * [0,1,0], 
 * [0,0,0]
 * ]
 * 
 * Output: 
 * [
 * [0,0,0], 
 * [0,1,0], 
 * [0,0,0]
 * ]
 * 
 * IDEA:
 * BFS from multiple sources with competitive update of dest map
 * 
 */
public class Solution542 {

	public int[][] updateMatrix(int[][] matrix) {
		int rows = matrix.length;
		if (rows == 0)
			return matrix;
		int cols = matrix[0].length;
		int[][] dist = new int[rows][cols]; // each element (i,j) contains the distance from (i, j) till the nearest 0 
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == 0) {
					dist[i][j] = 0;
					q.add(new int[] { i, j }); // Put all 0s in the queue.
				}
			}
		}

		int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int x = cur[0];
				int y = cur[1];
				int nr = x + dir[i][0];
				int nc = y + dir[i][1];
				if (nr >= 0 && nc >= 0 && nr < rows && nc < cols) {
					if (dist[nr][nc] > dist[x][y] + 1) {// update cell if and only if it was contacted by shorter path from cur cell to [some other cell]
						dist[nr][nc] = dist[x][y] + 1;// there is a shorter dist
						q.add(new int[] { nr, nc });
					}
				}
			}
		}
		return dist;
	}
}
