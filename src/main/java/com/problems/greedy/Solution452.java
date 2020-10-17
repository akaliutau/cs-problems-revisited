package com.problems.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * Greedy
 * 
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
