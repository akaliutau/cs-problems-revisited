package com.problems.twopointers;

/**
 * You are given an array representing a row of seats where seats[i] = 1
 * represents a person sitting in the ith seat, and seats[i] = 0 represents that
 * the ith seat is empty (0-indexed). There is at least one empty seat, and at
 * least one person sitting. Alex wants to sit in the seat such that the
 * distance between him and the closest person to him is maximized. Return that
 * maximum distance to the closest person. 
 * 
 * Input: seats = [1,0,0,0,1,0,1]  Output: 2 
 * 
 * Explanation: If Alex sits in the second open seat (i.e. seats[2]),
 * then the closest person has distance 2. 
 * 
 * If Alex sits in any other open seat,
 * the closest person has distance 1. Thus, the maximum distance to the closest
 * person is 2.
 */
public class Solution849 {

    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int prev = -1, next = 0;
        int ans = 0;

        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                prev = i;
            } else {
                next = Math.max(next, i);
                while (next < n && seats[next] == 0) {// find next 1 (after i)
                    next++;
                }
                //   1    0   1
                // [prev, i, next]
                int left = prev == -1 ? n : i - prev;// prev == -1 - no init
                int right = next == n ? n : next - i; // after i there is no 1
                ans = Math.max(ans, Math.min(left, right));
            }
        }

        return ans;
    }

}
