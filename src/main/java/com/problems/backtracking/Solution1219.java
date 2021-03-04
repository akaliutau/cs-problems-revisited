package com.problems.backtracking;

import java.util.Arrays;

/**
 * In a gold mine grid of size m * n, each cell in this mine has an integer
 * representing the amount of gold in that cell, 0 if it is empty.
 * 
 * Return the maximum amount of gold you can collect under the conditions:
 * 
 * Every time you are located in a cell you will collect all the gold in that
 * cell. 
 * 
 * From your position you can walk one step to the left, right, up or down. 
 * 
 * You can't visit the same cell more than once. 
 * 
 * Never visit a cell with 0 gold. 
 * 
 * You can start and stop collecting gold from any position in the grid
 * that has some gold.
 * 
 * 
 * Example 1:
 * 
 * Input: grid = 
 * [
 * [0,6,0],
 * [5,8,7],
 * [0,9,0]
 * ] Output: 24 
 * 
 * Explanation: 
 * Path to get the maximum gold, 9 -> 8 -> 7.
 * 
 * IDEA:
 * investigate all routes
 * 
 */
public class Solution1219 {
	
	int backtrack(int[][] grid, int row, int col, int sum) {
		// exit conditions
        if (row < 0 || col < 0 || row == grid.length || col == grid[row].length)
            return sum;
        if (grid[row][col] == 0)
            return sum;

        // save  prev value because it's dfs
        int gold = grid[row][col];
        // mark as visited to avoid loops
        grid[row][col] = 0;

        // collected gold after using all 4 directions
        int up = backtrack(grid, row + 1, col, sum + gold);
        int down = backtrack(grid, row - 1, col, sum + gold);
        int left = backtrack(grid, row, col - 1, sum + gold);
        int right = backtrack(grid, row, col + 1, sum + gold);

        // backtrack - restore prev value to pick up using another route
        grid[row][col] = gold;

        // return the largest one of 4 directions
        return Arrays.asList(up, down, left, right).stream().max(Integer::compare).get();

    }
	
	public int getMaximumGold(int[][] grid) {
        int max = 0;
        // start from each point
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, backtrack(grid, i, j, 0));// test path for each cell
            }
        }
        return max;
    }

 }
