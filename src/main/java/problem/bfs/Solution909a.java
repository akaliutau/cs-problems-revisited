package problem.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import problem.utils.Utils;

/**
 * 
 * BFS 
 * 
 * On an N x N board, the cells are marked as the following
 * 
 * 0 - empty cell
 * 1 - obstacle
 * 2 - portal allows to reach any cells with max dist = k
 * 3 - princess
 * 
 * the player starts from the cell (0,0)
 * 
 * find the minimal steps needed to reach the princess
 * 
 */
public class Solution909a {
	
	static int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	void processJump(int x, int y, int n, int m, Queue<int[]> q, int[][] map, int[][] steps, int curStep, int k) {
		if (k <= 0) {
			return;
		}
		for (int[] move : dirs) {
			int nx = x + move[0];
			int ny = y + move[1];
			if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != 1 && steps[nx][ny] > curStep) {
				steps[nx][ny] = curStep;
				q.add(new int[] {nx, ny});
				processJump(nx, ny, n, m, q, map, steps, curStep, k - 1);
			}
		}
	}
	
	public int findTheWay(int[][] map, int k) {
		int n = map.length;
		int m = 0;
		if (n != 0) {
			m = map[0].length;
		}
		int[][] steps = new int[n][m];
		// init steps
		for (int i = 0; i < n; i++) {
			Arrays.fill(steps[i], Integer.MAX_VALUE);
		}
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0});
		steps[0][0] = 0;
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int step = steps[x][y];
			if (map[x][y] == 3) {
				Utils.print(steps);
				return step;
			}
			if (map[x][y] == 2) {
				processJump(x, y, n, m, q, map, steps, step + 1, k);
			} else {
				for (int[] move : dirs) {
					int nx = x + move[0];
					int ny = y + move[1];
					if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != 1 && steps[nx][ny] > step + 1) {
						steps[nx][ny] = step + 1;
						q.add(new int[] {nx, ny});
					}
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		
		Solution909a solution = new Solution909a();
		int[][] map1 = {
				{0, 2, 0 , 0, 1},
				{0, 0, 0 , 0, 1},
				{0, 1, 0 , 3, 1},
				{0, 0, 1 , 0, 1},
				{0, 0, 1 , 0, 1},
				};
		System.out.println(solution.findTheWay(map1, 3));
	}

}
