package problem.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). In
 * one step, you can move up, down, left or right from and to an empty cell.
 * Return the minimum number of steps to walk from the upper left corner (0, 0)
 * to the lower right corner (m-1, n-1) given that you can eliminate at most k
 * obstacles. If it is not possible to find such walk return -1. 
 * 
 * Example 1:
 * Input: grid = 
 * [
 * [0,0,0], 
 * [1,1,0], 
 * [0,0,0], 
 * [0,1,1], 
 * [0,0,0]
 * ], 
 * k = 1 Output: 6
 * 
 * Explanation: The shortest path without eliminating any obstacle is 10. The
 * shortest path with one obstacle elimination at position (3,2) is 6. Such path
 * is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 * 
 * IDEA:
 * 1) use a classical BFS - which is ALWAYS return the shortest path due to its nature
 * 2) track a current number of overcome obstacles - and traverse this value along with coords on next cell to process
 * 
 */
public class Solution1293 {

    int[] dirX = new int[] { 1, -1, 0, 0 };
    int[] dirY = new int[] { 0, 0, -1, 1 };

    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (grid == null || rows == 0 || cols == 0) {
            return 0;
        }

        // if k is large enough, then can ignore any number of obstacles i.e. to jump directly to the dest point
        if (k >= rows + cols - 3) {
            return rows + cols - 2;
        }

        Queue<int[]> q = new LinkedList<>();
        int[] state = new int[] { 0, 0, k };
        q.offer(state);
        Set<String> visited = new HashSet<>();
        String key = Arrays.toString(state);
        visited.add(key);
        int res = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                // reached aim
                if (cur[0] == rows - 1 && cur[1] == cols - 1)
                    return res;

                for (int j = 0; j < 4; j++) {
                    int x = cur[0] + dirX[j];
                    int y = cur[1] + dirY[j];
                    int curK = cur[2];
                    if (isInvalid(x, y, rows, cols))
                        continue;
                    if (grid[x][y] == 1) {
                        if (curK < 1) {// cannot go through due to limit
                            continue;
                        }
                        curK--;
                    }
                    int[] next = new int[] { x, y, curK };
                    String nextKey = Arrays.toString(next);
                    if (!visited.contains(nextKey)) {
                        visited.add(nextKey);
                        q.add(next);
                    }
                }
            }
            res++;
        }
        // if didn't exit earlier, 
        return -1;
    }

    boolean isInvalid(int row, int col, int rows, int cols) {
        return (row < 0 || col < 0 || row >= rows || col >= cols);
    }

  

}
