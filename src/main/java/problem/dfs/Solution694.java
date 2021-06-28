package problem.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water.
 * 
 * Count the number of distinct islands. An island is considered to be the same
 * as another if and only if one island can be translated (and not rotated or
 * reflected) to equal the other.
 * 
 * Example 1: 
 * 11000 
 * 11000 
 * 00011 
 * 00011 
 * 
 * Given the above grid map, return 1.
 * 
 * IDEA:
 * 1. variation on the topic of count separate islands, need to create a 2d fingerprint for shape
 * 
 *   oo              ooo
 *    o                o
 * 
 * (0,1)(1,0)(1,1)  (1,0)(1,1)(1,2)(0,2)  <-- the same set of  pairs (x,y) means the same shape
 */
public class Solution694 {

	int[][] grid;
	boolean[][] seen;
	int offset = Integer.MAX_VALUE;
	int n = 0, m = 0;
    
    static String coord(int r, int c, int r0, int c0){
        return String.format("%d:%d", r - r0, c - c0);
    }

	// relative position of point (r0,c0) will be the same for similar islands
	public void trace(int r, int c, int r0, int c0, Set<String> shapeFingerPrint) {
		if (0 <= r && r < n && 0 <= c && c < m && grid[r][c] == 1 && !seen[r][c]) {
			seen[r][c] = true;
			shapeFingerPrint.add(coord(r,c,r0,c0));
			trace(r + 1, c, r0, c0, shapeFingerPrint);
			trace(r - 1, c, r0, c0, shapeFingerPrint);
			trace(r, c + 1, r0, c0, shapeFingerPrint);
			trace(r, c - 1, r0, c0, shapeFingerPrint);
		}
	}

	public int numDistinctIslands(int[][] grid) {
		this.grid = grid;
		n = grid.length;
		m = 0;
		if (n > 0) {
			m = grid[0].length;
		}
		if (n == 0 || m == 0) {
			return 0;
		}
		seen = new boolean[n][m];
		Set<Set<String>> shapes = new HashSet<>();
		offset = m;// this offset is chosen due to 

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				Set<String> shapeFingerPrint = new HashSet<>();
				trace(r, c, r, c, shapeFingerPrint);
				if (!shapeFingerPrint.isEmpty()) {
					shapes.add(shapeFingerPrint);// equal sets will have the same result of equals call
				}
			}
		}

		return shapes.size();
	}

}
