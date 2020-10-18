package com.problems.dividenconquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the
 * buildings in that city when viewed from a distance. Now suppose you are given
 * the locations and height of all the buildings as shown on a cityscape photo
 * (Figure A), write a program to output the skyline formed by these buildings
 * collectively (Figure B)
 * 
 * The geometric information of each building is represented by a triplet of
 * integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and
 * right edge of the ith building, respectively, and Hi is its height. It is
 * guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You
 * may assume all buildings are perfect rectangles grounded on an absolutely
 * flat surface at height 0.
 * 
 * For instance, the dimensions of all buildings in Figure A are recorded as: [
 * [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ]
 * 
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3
 * 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]
 * 
 */
public class Solution218 {
	
	/**
	 * Merge two skylines together.
	 */
	List<List<Integer>> mergeSkylines(List<List<Integer>> left, List<List<Integer>> right) {
		int nL = left.size(), nR = right.size();
		int pL = 0, pR = 0;
		int mergedY = 0, leftY = 0, rightY = 0;
		int x, maxY;
		List<List<Integer>> output = new ArrayList<List<Integer>>();

		// while we're in the region where both skylines are present
		while ((pL < nL) && (pR < nR)) {
			List<Integer> pointL = left.get(pL);
			List<Integer> pointR = right.get(pR);
			// pick up the smallest x
			if (pointL.get(0) < pointR.get(0)) {
				x = pointL.get(0);
				leftY = pointL.get(1);
				pL++;
			} else {
				x = pointR.get(0);
				rightY = pointR.get(1);
				pR++;
			}
			// max height (i.e. y) between both skylines
			maxY = Math.max(leftY, rightY);

			if (mergedY != maxY) {
				updateOutput(output, x, maxY);
				mergedY = maxY;
			}
		}

		appendSkyline(output, left, pL, nL, mergedY);
		appendSkyline(output, right, pR, nR, mergedY);

		return output;
	}

	/**
	 * Update the final output with the new element.
	 */
	void updateOutput(List<List<Integer>> output, int x, int y) {
		if (output.isEmpty() || output.get(output.size() - 1).get(0) != x) {
			output.add(Arrays.asList(x, y));
		}else {
			output.get(output.size() - 1).set(1, y);
		}
	}

	/**
	 * Append the rest of the skyline elements with indice (p, n) to the final
	 * output.
	 */
	void appendSkyline(List<List<Integer>> output, List<List<Integer>> skyline, int p, int n, int mergedY) {
		while (p < n) {
			List<Integer> point = skyline.get(p);
			int x = point.get(0);
			int y = point.get(1);
			p++;

			if (mergedY != y) {
				updateOutput(output, x, y);
				mergedY = y;
			}
		}
	}


	/**
	 * Divide-and-conquer algorithm to solve skyline problem, which is similar with
	 * the merge sort algorithm.
	 */
	public List<List<Integer>> getSkyline(int[][] buildings) {
		int n = buildings.length;
		List<List<Integer>> output = new ArrayList<>();

		// The base cases
		if (n == 0)
			return output;
		if (n == 1) {
			int xStart = buildings[0][0];
			int xEnd = buildings[0][1];
			int y = buildings[0][2];

			output.add(Arrays.asList(xStart, y));
			output.add(Arrays.asList(xEnd, 0));
			return output;
		}

		List<List<Integer>> leftSkyline, rightSkyline;
		leftSkyline = getSkyline(Arrays.copyOfRange(buildings, 0, n / 2));
		rightSkyline = getSkyline(Arrays.copyOfRange(buildings, n / 2, n));

		return mergeSkylines(leftSkyline, rightSkyline);
	}


	

}
