package problem.dfs;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water). An island is a maximal
 * 4-directionally connected group of 0s and a closed island is an island
 * totally (all left, top, right, bottom) surrounded by 1s.
 * 
 * Return the number of closed islands.
 * 
 * Input: grid =
 * [
 * [1,1,1,1,1,1,1,0],
 * [1,0,0,0,0,1,1,0],
 * [1,0,1,0,1,1,1,0],
 * [1,0,0,0,0,1,0,1],
 * [1,1,1,1,1,1,1,0]
 * ]
 * Output: 2 
 * 
 * Explanation: Islands in gray are closed because they are completely
 * surrounded by water (group of 1s).
 * 
 * IDEA:
 * 1) detect and remove boundary land using dfs
 * 2) detect and count lands using dfs
 */
public class Solution1254 {
	
	int n, m;
	
	static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	void dfs(int grid[][], int i, int j) {
		if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 2 || grid[i][j] == 1)// omit visited and water
			return;

		// marking the visited node
		grid[i][j] = 2;

		// Calling in 4 directions
		for (int[] dir : dirs) {
			dfs(grid, i + dir[0], j + dir[1]);
		}
	}

	public int closedIsland(int[][] grid) {
		
		n = grid.length;
		m = grid[0].length;
		int num = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {// detect and remove all land which touch boundary
					if (grid[i][j] == 0) {
						dfs(grid, i, j);
					}
				}
			}
		}
		// Counting all closed islands
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 0) {
					dfs(grid, i, j);// fill all inner land
					num++;
				}
			}
		}

		return num;
	}

	

}
