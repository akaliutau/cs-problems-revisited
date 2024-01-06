package problem.dfs;

/**
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number
 * of islands.
 * 
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: grid = 
 * [
 * ["1","1","1","1","0"], 
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"], 
 * ["0","0","0","0","0"] 
 * ] 
 * 
 * Output: 1
 * O(n*m)
 * 
 * IDEA:
 * use dfs to fill out islands and count in the process
 */
public class Solution200 {
	void dfs(char[][] grid, int r, int c) {
		int nr = grid.length;
		int nc = grid[0].length;

		if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
			return;
		}

		grid[r][c] = '0';
		dfs(grid, r - 1, c);
		dfs(grid, r + 1, c);
		dfs(grid, r, c - 1);
		dfs(grid, r, c + 1);
	}

	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int nr = grid.length;
		int nc = grid[0].length;
		int numIslands = 0;
		for (int r = 0; r < nr; ++r) {
			for (int c = 0; c < nc; ++c) {
				if (grid[r][c] == '1') {
					++numIslands;
					dfs(grid, r, c);
				}
			}
		}

		return numIslands;
	}
}
