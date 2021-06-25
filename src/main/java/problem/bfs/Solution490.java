package problem.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls
 * (represented as 1). The ball can go through the empty spaces by rolling up,
 * down, left or right, but it won't stop rolling until hitting a wall. When the
 * ball stops, it could choose the next direction.
 * 
 * Given the maze, the ball's start position and the destination, where start =
 * [startrow, startcol] and destination = [destinationrow, destinationcol],
 * return true if the ball can stop at the destination, otherwise return false.
 * 
 * You may assume that the borders of the maze are all walls (see examples)
 * 
 * IDEA:
 * almost pure BFS, with additional code to calc stable position
 * 
 */
public class Solution490 {
	
	static boolean valid(int[][] maze, int x, int y, int n, int m) {
		return x >= 0 && y >= 0 && x < n && y < m && maze[x][y] == 0;
	}

	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		int n = maze.length;
		if (n == 0) {
			return false;
		}
		int m = maze[0].length;
		boolean[][] visited = new boolean[n][m];
		int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
		Queue<int[]> queue = new LinkedList<>();
		queue.add(start);
		visited[start[0]][start[1]] = true;
		while (!queue.isEmpty()) {
			int[] s = queue.remove();
			if (s[0] == destination[0] && s[1] == destination[1])
				return true;
			for (int[] dir : dirs) {// start traversing in all directions in the same time
				int x = s[0];
				int y = s[1];
				while (valid(maze, x + dir[0], y + dir[1], n, m)) {// until hit the wall
					x += dir[0];
					y += dir[1];
				}
				if (!visited[x][y]) {
					queue.add(new int[] { x, y });
					visited[x][y] = true;
				}
			}
		}
		return false;
	}



}
