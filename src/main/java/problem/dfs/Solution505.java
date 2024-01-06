package problem.dfs;

import java.util.Arrays;

/**
 * 
 * There is a ball in a maze with empty spaces (represented as 0) and walls
 * (represented as 1). The ball can go through the empty spaces by rolling up,
 * down, left or right, but it won't stop rolling until hitting a wall. When the
 * ball stops, it could choose the next direction.
 * 
 * Given the m x n maze, the ball's start position and the destination, where
 * start = [startrow, startcol] and destination = [destinationrow,
 * destinationcol], return the shortest distance for the ball to stop at the
 * destination. If the ball cannot stop at destination, return -1.
 * 
 * The distance is the number of empty spaces traveled by the ball from the
 * start position (excluded) to the destination (included).
 * 
 * You may assume that the borders of the maze are all walls (see examples).
 * 
 * IDEA:
 * 1. define hop as a part of all path from once bounce to another one
 * 
 * 
 */
public class Solution505 {

	static int[][] dirs = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	void dfs(int[][] maze, int[] start, int[][] dp) {
		for (int[] dir : dirs) {
			int x = start[0] + dir[0];
			int y = start[1] + dir[1];
			int hop = 0;
			while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {// roll while wall
				x += dir[0];
				y += dir[1];
				hop++;
			}
			// point of stop
			int xs = x - dir[0];
			int ys = y - dir[1];
			
			if (dp[xs][ys] == -1 || dp[start[0]][start[1]] + hop < dp[xs][ys]) {
				dp[xs][ys] = dp[start[0]][start[1]] + hop;
				dfs(maze, new int[] { xs, ys }, dp);
			}
		}
	}

	public int shortestDistance(int[][] maze, int[] start, int[] dest) {
		int[][] dp = new int[maze.length][maze[0].length];
		for (int[] row : dp)
			Arrays.fill(row, -1);
		
		dp[start[0]][start[1]] = 0;
		
		dfs(maze, start, dp);
		
		return dp[dest[0]][dest[1]];
	}

}
