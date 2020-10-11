package com.problems.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * DFS
 * 
 */
public class Solution694 {

	int[][] grid;
	boolean[][] seen;
	int offset = Integer.MAX_VALUE;

	// relative position of point (r0,c0) will be the same for similar islands
	public void trace(int r, int c, int r0, int c0, Set<Long> shapeFingerPrint) {
		if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] == 1 && !seen[r][c]) {
			seen[r][c] = true;
			// create a 2D fingerprint as 
			shapeFingerPrint.add((long) ((r - r0) * offset + (c - c0)));
			trace(r + 1, c, r0, c0, shapeFingerPrint);
			trace(r - 1, c, r0, c0, shapeFingerPrint);
			trace(r, c + 1, r0, c0, shapeFingerPrint);
			trace(r, c - 1, r0, c0, shapeFingerPrint);
		}
	}

	public int numDistinctIslands(int[][] grid) {
		this.grid = grid;
		seen = new boolean[grid.length][grid[0].length];
		Set<Set<Long>> shapes = new HashSet<>();
		offset = 2 * grid[0].length;// offset is chosen due to 

		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				Set<Long> shapeFingerPrint = new HashSet<>();
				trace(r, c, r, c, shapeFingerPrint);
				if (!shapeFingerPrint.isEmpty()) {
					shapes.add(shapeFingerPrint);// equal sets will have the same result of equals call
				}
			}
		}

		return shapes.size();
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
