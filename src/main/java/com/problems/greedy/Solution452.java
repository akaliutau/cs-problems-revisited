package com.problems.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * How many arrows is needed to burst balloons
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
		int xStart, xEnd;
		int firstEnd = points[0][1];// the same as p[1] in the beginning
		for (int[] p : points) {
			xStart = p[0];
			xEnd = p[1];
			if (firstEnd < xStart) {// no intersection, need more arrows
				arrows++;
				firstEnd = xEnd;
			}
		}

		return arrows;
	}



}
