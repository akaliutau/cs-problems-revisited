package com.problems.linesweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given the availability time slots arrays slots1 and slots2 of two people and
 * a meeting duration duration, return the earliest time slot that works for
 * both of them and is of duration duration.
 * 
 * If there is no common time slot that satisfies the requirements, return an
 * empty array.
 * 
 * The format of a time slot is an array of two elements [start, end]
 * representing an inclusive time range from start to end.
 * 
 * It is guaranteed that no two availability slots of the same person intersect
 * with each other. That is, for any two time slots [start1, end1] and [start2,
 * end2] of the same person, either start1 > end2 or start2 > end1.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: slots1 = 
 * [[10,50],[60,120],[140,210]], 
 * slots2 = 
 * [[0,15],[60,70]],
 * duration = 8 Output: [60,68]
 * 
 * 
 */
public class Solution1229 {
	
	boolean overlaps(int[] a, int[] b) {
		return b[0] <= a[0] && a[0] <= b[1] || a[0] <= b[0] && b[0] <= a[1];
	}

	/**
	 * Only for overlapping intervals
	 * @param a
	 * @param b
	 * @return
	 */
	int getDuration(int[] a, int[] b) {
		int start = Math.max(a[0], b[0]);
		int end = Math.min(a[1], b[1]);
		return end - start;
	}

	public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
		Comparator<int[]> byStart = (a, b) -> Integer.compare(a[0], b[0]);
		Comparator<int[]> byEnd = (a, b) -> Integer.compare(a[1], b[1]);
		
		Arrays.sort(slots1, byStart.thenComparing(byEnd));
		Arrays.sort(slots2, byStart.thenComparing(byEnd));
		List<Integer> result = new ArrayList<>();

		int s1 = 0;
		int s2 = 0;
		while (s1 < slots1.length && s2 < slots2.length) {
			int[] interval1 = slots1[s1];
			int[] interval2 = slots2[s2];
			if (interval1[1] - interval1[0] < duration) {// wrong - interval too small
				s1++;
			} else if (interval2[1] - interval2[0] < duration) {// wrong - interval too small
				s2++;
			} else if (overlaps(interval1, interval2) && getDuration(interval1, interval2) >= duration) {// can get here, if 1) both intervals are big enough 2) overlapping 3) overlap >= duration
				int start = Math.max(interval1[0], interval2[0]);
				result.add(start);
				result.add(start + duration);
				return result;
			} else {
				if (interval1[0] < interval2[0]) {// omit that started the earliest
					s1++;
				} else {
					s2++;
				}
			}
		}
		return result;// returns empty list
	}

	

}
