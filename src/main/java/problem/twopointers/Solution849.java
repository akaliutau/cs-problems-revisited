package problem.twopointers;

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
 * 
 * IDEA:
 * 1) use 2 indecies which point to the left and right persons
 * 2) dynamically update these pointers, calculate min distance on each empty place
 * 
 * 1111000000011
 *    |       |
 * seenLeft   seenRight  
 */
public class Solution849 {

    public int maxDistToClosest(int[] seats) {
        int n = seats.length;
        int seenLeft = -1, seenRight = 0;
        int ans = 0;

        for (int i = 0; i < n; ++i) {
            if (seats[i] == 1) {
                seenLeft = i;
            } else {// this block is triggered on empty seat
                seenRight = Math.max(seenRight, i);
                while (seenRight < n && seats[seenRight] == 0) {// find seenRight 1 (after i) - a costly operation, but executed only once pre all i-loop
                    seenRight++;
                }
                // now: if no persons, seenRight=n else seenRight=index(seat with 1)
                //   1        0     1
                // [seenLeft, i, seenRight]
                int left = seenLeft == -1 ? n : i - seenLeft;// seenLeft == -1 - no persons
                int right = seenRight == n ? n : seenRight - i; // after i there is no 1
                ans = Math.max(ans, Math.min(left, right));
            }
        }

        return ans;
    }

}
