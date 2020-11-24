package com.problems.heap;

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
 */
public class Solution1642 {

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length;
        int b = 0; // bricks used so far
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i + 1 < n; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff > 0) {
                if (pq.size() < ladders) {
                    pq.add(diff);// all ladders will be on the bottom of queue
                } else {
                    if (ladders == 0) {
                        b += diff;// use bricks
                    } else if (diff > pq.peek()) {// diff is the max so far, so use ladder for it
                        b += pq.peek(); // use bricks NOTE: all the rest jumps are covered with ladders
                        pq.poll();// replace with bricks
                        pq.add(diff);// replace with bricks
                    } else {
                        b += diff;// use bricks NOTE: all the rest jumps are covered with ladders
                    }
                }
            }
            if (b > bricks)
                return i;
        }
        return n - 1;

    }

}
