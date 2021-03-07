package com.problems.dfs;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water. Find the
 * maximum area of an island in the given 2D array. (If there is no island, the
 * maximum area is 0.) 
 * 
 * Example 1: 
 * [
 * [0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0], 
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0], 
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0], 
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]
 * ]
 * output: 6
 * 
 * IDEA:
 *  use dfs to traverse all cells
 * 
 */

public class Solution695 {

    int[][] grid;
    boolean[][] visited;
    int n, m;

    int area(int r, int c) {
        if (r < 0 || r >= n || c < 0 || c >= m || visited[r][c] || grid[r][c] == 0)
            return 0;
        visited[r][c] = true;
        return 1 + area(r + 1, c) + area(r - 1, c) + area(r, c - 1) + area(r, c + 1);
    }

    public int maxAreaOfIsland(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        visited = new boolean[n][m];
        int ans = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                ans = Math.max(ans, area(r, c));
            }
        }
        return ans;
    }

}
