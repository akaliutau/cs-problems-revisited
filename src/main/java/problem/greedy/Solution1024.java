package problem.greedy;

import java.util.Arrays;

/**
 * 
 * You are given a series of video clips from a sporting event that lasted fullLength
 * seconds. These video clips can be overlapping with each other and have
 * varying lengths.
 * 
 * Each video clip is described by an array clips where clips[i] = [starti, endi] 
 * indicates that the ith clip started at starti and ended at endi.
 * 
 * We can cut these clips into segments freely.
 * 
 * For example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 * 
 * Return the minimum number of clips needed so that we can cut the clips into
 * segments that cover the entire sporting event [0, fullLength]. If the task is
 * impossible, return -1.
 * 
 * Example 1:
 * 
 * Input: clips = 
 * 
 * [
 * [0,2],[4,6],[8,10],
 *   [1,         9],
 *   [1,   5],
 *        [5,    9]
 * ],
 * 
 *  fullLength = 10 Output: 3
 * 
 * Explanation: We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows: We cut [1,9] into
 * segments [1,2] + [2,8] + [8,9]. Now we have segments [0,2] + [2,8] + [8,10]
 * which cover the sporting event [0, 10].
 * 
 *   IDEA:
 * 
 * oo      ooo 
 *  ooooooooo
 *  
 * use greedy approach - among overlapping intervals always choose the longest one
 *
 *
 */
public class Solution1024 {
	public int videoStitching(int[][] clips, int fullLength) {
		Arrays.sort(clips, (o, p) -> (o[0] - p[0]));
		int endTime = 0, end = 0, count = 0;
		int clipIndex = 0;
		
		while (end < fullLength) {
			count++;
			boolean foundOverlapped = false;
			while (clipIndex < clips.length && clips[clipIndex][0] <= end) {// pick up one which overlaps
				endTime = Math.max(endTime, clips[clipIndex][1]);      // among all clips choose the one which covers the most fullLength
				clipIndex++;
				foundOverlapped = true;
			}
			if (!foundOverlapped)
				return -1;
			endTime = end;
		}

		return count;
	}
}
