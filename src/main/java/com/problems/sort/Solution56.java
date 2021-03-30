package com.problems.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * IDEA:
 * 
 * growing intervals - 
 * 
 * try to grow interval as much as possible (until there is intersection)
 * 
 * when such situation has been reached, add completed interval to the answer list
 * 
 * NOTE: sorting by left edge is needed
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
		    int[] last = merged.isEmpty() ? null : merged.getLast();
			if (merged.isEmpty() || last[1] < interval[0]) {// close/drop last interval and start next
				merged.add(interval);
			} else { //overlap - updated only right edge 
			    last[1] = Math.max(last[1], interval[1]);
			}
		}

		return merged.toArray(new int[merged.size()][]);
	}

	
}
