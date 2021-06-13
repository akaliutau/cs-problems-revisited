package problem.greedy;

import java.util.Arrays;

/**
 * Given a rectangular cake with height h and width w, and two arrays of
 * integers horizontalCuts and verticalCuts where horizontalCuts[i] is the
 * distance from the top of the rectangular cake to the ith horizontal cut and
 * similarly, verticalCuts[j] is the distance from the left of the rectangular
 * cake to the jth vertical cut.
 * 
 * Return the maximum area of a piece of cake after you cut at each horizontal
 * and vertical position provided in the arrays horizontalCuts and verticalCuts.
 * Since the answer can be a huge number, return this modulo 10^9 + 7.
 * 
 * IDEA:
 * because any horizontal line cuts all vertical, find the biggest gap between vertical lines,
 *  and the biggest gap between horizontal lines, and multiple them
 * 
 */
public class Solution1465 {

	public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
		int hCuts = horizontalCuts.length;
		int vCuts = verticalCuts.length;
		
		// define the array to hold deltas between slices
		// for n cuts we have n + 1 slices
		long[] hInt = new long[hCuts + 1];
		Arrays.sort(horizontalCuts);
		hInt[0] = horizontalCuts[0];// the size of the very first slice is the 1st cut
		for (int i = 1; i < hCuts; i++) {
			hInt[i] = horizontalCuts[i] - horizontalCuts[i - 1];
		}
		hInt[hCuts] = h - horizontalCuts[hCuts - 1];// tail
		
		long[] vInt = new long[vCuts + 1];
		Arrays.sort(verticalCuts);
		vInt[0] = verticalCuts[0];
		for (int i = 1; i < vCuts; i++) {
			vInt[i] = verticalCuts[i] - verticalCuts[i - 1];
		}
		vInt[vCuts] = w - verticalCuts[vCuts - 1];

		long maxh = 0;
		for (int i = 0; i <= hCuts; i++) {
			maxh = Math.max(maxh, hInt[i]);
		}

		long maxv = 0;
		for (int i = 0; i <= vCuts; i++) {
			maxv = Math.max(maxv, vInt[i]);
		}
		long area = (long) (maxh * maxv) % 1000000007;
		return (int) area;
	}

}
