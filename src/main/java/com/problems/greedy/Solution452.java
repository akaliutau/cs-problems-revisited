package com.problems.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * Greedy
 * 
 * The idea of greedy algorithm is to pick the locally optimal move at each
 * step, that will lead to the globally optimal solution
 */
public class Solution452 {

	public int findMinArrowShots(int[][] points) {
		if (points.length == 0)
			return 0;

		Comparator<int[]> byEnd = (o, p) -> Integer.compare(o[1], p[1]);
		// sort by end
		Arrays.sort(points, byEnd);

		int arrows = 1;
		int xStart, xEnd, firstEnd = points[0][1];
		for (int[] p : points) {
			xStart = p[0];
			xEnd = p[1];
			if (firstEnd < xStart) {
				arrows++;
				firstEnd = xEnd;
			}
		}

		return arrows;
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
