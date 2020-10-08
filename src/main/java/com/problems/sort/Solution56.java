package com.problems.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 
 * Sort
 * 
 * Idea:
 * 
 * growing intervals
 * 
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 */
public class Solution56 {

	public int[][] merge(int[][] intervals) {

		Comparator<int[]> byLeft = (o, p) -> Integer.compare(o[0], p[0]);
		Collections.sort(Arrays.asList(intervals), byLeft);

		LinkedList<int[]> merged = new LinkedList<>();
		for (int[] interval : intervals) {
			if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {// close/drop last interval and start next
				merged.add(interval);
			} else { //overlap - updated only right edge 
				merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
			}
		}

		return merged.toArray(new int[merged.size()][]);
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
