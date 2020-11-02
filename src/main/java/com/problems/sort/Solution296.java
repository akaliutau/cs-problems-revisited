package com.problems.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * A group of two or more people wants to meet and minimize the total travel
 * distance. You are given a 2D grid of values 0 or 1, where each 1 marks the
 * home of someone in the group. The distance is calculated using Manhattan
 * Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * 
 * Example:
 * 
 * Input:
 * 
 *    1 - 0 - 0 - 0 - 1 
 *    |   |   |   |   | 
 *    0 - 0 - 0 - 0 - 0 
 *    |   |   |   |   | 
 *    0 - 0 - 1 - 0 - 0
 * 
 * Output: 6
 * 
 * Explanation: Given three people living at (0,0), (0,4), and (2,2): The point
 * (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is
 * minimal. So return 6.
 * 
 * IDEA:
 * calc |row0 - orig| + |row0 - orig| + |row1 - orig| 
 * 
 * rows = [0,0,2]
 * cols = [0,2,4]
 * 
 */
public class Solution296 {
	
	int n, m;
	
	int minDistance1D(List<Integer> points, int origin) {// calc |row0 - orig| + |row0 - orig| + |row1 - orig| 
	    int distance = 0;
	    for (int point : points) {
	        distance += Math.abs(point - origin);
	    }
	    return distance;
	}

	List<Integer> collectRows(int[][] grid) {// returns the list of rows with 1s, in the order of their appearance
	    List<Integer> rows = new ArrayList<>();
	    for (int row = 0; row < n; row++) {
	        for (int col = 0; col < m; col++) {
	            if (grid[row][col] == 1) {
	                rows.add(row);
	            }
	        }
	    }
	    return rows;
	}

	List<Integer> collectCols(int[][] grid) {// returns the list of cols with 1s, in the order of their appearance
	    List<Integer> cols = new ArrayList<>();
	    for (int col = 0; col < m; col++) {
	        for (int row = 0; row < n; row++) {
	            if (grid[row][col] == 1) {
	                cols.add(col);
	            }
	        }
	    }
	    return cols;
	}
	
	public int minTotalDistance(int[][] grid) {
		n = grid.length;
		m = grid[0].length;
	    List<Integer> rows = collectRows(grid);
	    List<Integer> cols = collectCols(grid);
	    int rowOpt = rows.get(rows.size() / 2);// because in sorted list of x-coords the middle point is an equilibrium
	    int colOpt = cols.get(cols.size() / 2);
	    return minDistance1D(rows, rowOpt) + minDistance1D(cols, colOpt);
	}

	

}
