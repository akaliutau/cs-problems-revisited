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
 * Reserve a list of blocks to be covered by ladders and then dynamically optimize it
 * Use a heap to update list using the most optimal (==smallest gap) node
 * 
 */
public class Solution1642 {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        PriorityQueue<Integer> withLadders = new PriorityQueue<>();

        for (int i = 0; i + 1 < n; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff > 0) {
                if (withLadders.size() < ladders) {
                	withLadders.add(diff);// all added ladders will be on the bottom of queue
                } else {
                    if (withLadders.size() == 0) {
                    	bricks -= diff;// use bricks
                    } else if (diff > withLadders.peek()) {// diff is the max so far, so use ladder for it
                    	bricks -= withLadders.peek(); // use bricks from queue as the most optimal step, because the tip contains the smallest amount 
                    	withLadders.poll();
                    	withLadders.add(diff);// replace with bigger amount as all in withLadders covered with ladders anyway
                    } else {// found even more optimal solution
                    	bricks -= diff;
                    }
                }
            }
            if (bricks < 0)
                return i;
        }
        // NOTE: the size of coveredBricks will be <=ladders here
        return n - 1;

    }

}
