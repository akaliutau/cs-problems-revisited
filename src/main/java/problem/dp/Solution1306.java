package problem.dp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an array of non-negative integers arr, you are initially positioned at
 * start index of the array. When you are at index i, you can jump to i + arr[i]
 * or i - arr[i], check if you can reach to any index with value 0. Notice that
 * you can not jump outside of the array at any time. 
 * 
 * Example 1: Input: arr = [4,2,3,0,3,1,2], start = 5 Output: true 
 * 
 * Explanation: All possible ways to
 * reach at index 3 with value 0 are: index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * 
 * IDEA:
 * Build a graph of all possible paths using BFS, with seen array as a memo
 */
public class Solution1306 {

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] seen = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int pos = q.poll();
            if (arr[pos] == 0) {
                return true;
            }
            seen[pos] = true;
            int pos1 = pos - arr[pos];
            int pos2 = pos + arr[pos];
            if (pos1 >= 0 && !seen[pos1]) {
                q.add(pos1);
            }
            if (pos2 < n && !seen[pos2]) {
                q.add(pos2);
            }
        }
        return false;
    }

}
