package com.problems.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Heap, greedy
 */
public class Solution253 {
    
    public int minMeetingRooms(int[][] intervals) {
        int len = intervals.length;
        
        // Check for the base case. If there are no intervals, return 0
        if (len == 0) {
          return 0;
        }

        // Min heap - the top contains the min ending of ADDED intervals
        Comparator<Integer> byEnd = (o,p) -> Integer.compare(o, p);
        Queue<Integer> endings = new PriorityQueue<Integer>(len, byEnd);

        // Sort the intervals by start time
        Comparator<int[]> byStart = (o,p) -> Integer.compare(o[0], p[0]);
        Arrays.sort(intervals, byStart);

        // Add the first meeting
        endings.add(intervals[0][1]);

        // Iterate over remaining intervals
        for (int i = 1; i < len; i++) {

          // If the ending of {all added intervals} already in the past relatively interval [i], use that time slot
          if (intervals[i][0] >= endings.peek()) {
              endings.poll();
          }
          // If a new slot is to be assigned, then also we add to the heap,
          // If an old slot is allocated, then also we have to add to the heap with new ending time.
          endings.add(intervals[i][1]);
        }

        // The size of the heap tells us the minimum slots required for all the meetings.
        return endings.size();
      }


	public static void main(String[] arg) {
		
		System.out.println();

	}

}
