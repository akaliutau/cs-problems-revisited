package problem.priorityqueue;

import java.util.PriorityQueue;

/**
 * You are given an integer array heights representing the heights of buildings,
 * some bricks, and some ladders. You start your journey from building 0 and
 * move to the next building by possibly using bricks or ladders. While moving
 * from building i to building i+1 (0-indexed), If the current building's height
 * is greater than or equal to the next building's height, you do not need a
 * ladder or bricks. If the current building's height is less than the next
 * building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
 * Return the furthest building index (0-indexed) you can reach if you use the
 * given ladders and bricks optimally.
 * 
 * IDEA:
 * 1. use bricks whenever possible, and ladders when bricks are not enough
 * 2. Implementation:
 *  
 */
public class Solution1642 {

    public int furthestBuilding(int[] heights, int bricks, int nLadders) {
        int n = heights.length;
        int bricksUsed = 0; // bricks used so far
        
        // used to hold jumps to be covered by ladders
        PriorityQueue<Integer> laddersJumps = new PriorityQueue<>();

        for (int i = 0; i + 1 < n; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff > 0) {
            	// first nLadders buildings can be covered by ladders, so just collect them
                if (laddersJumps.size() < nLadders) {
                	laddersJumps.add(diff);// all big jumps to be covered by ladders will be on the bottom of queue and processed in the end
                } else {
                    if (nLadders == 0) {// if there are no ladders at all, always use bricks instead
                    	bricksUsed += diff;
                    } else if (diff > laddersJumps.peek()) {// diff is the max so far, so use ladder for it
                    	bricksUsed += laddersJumps.peek(); // use bricks NOTE: all the rest jumps are covered with ladders
                    	laddersJumps.poll();// replace with bricks
                    	laddersJumps.add(diff);// replace with bricks
                    } else {
                    	bricksUsed += diff;// use bricks NOTE: all the rest jumps are covered with ladders
                    }
                }
            }
            if (bricksUsed > bricks)
                return i;
        }
        return n - 1;

    }

}
