package com.problems.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * BFS
 */
public class Solution1293 {

    int[] dirX = new int[] { 1,  -1,  0, 0 };
    int[] dirY = new int[] { 0,   0, -1, 1 };

    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (grid == null || rows == 0 || cols == 0) {
            return 0;
        }

        // if k is large enough, then can ignore obstacles
        if (k >= rows + cols - 3) {
            return rows + cols - 2;
        }

        Queue<int[]> q = new LinkedList<>();
        int[] state = new int[] {0, 0, k};
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
                        curK --;
                    }
                    int[] next = new int[] {x, y, curK};
                    String nextKey = Arrays.toString(next);
                    if (!visited.contains(nextKey)) {
                        visited.add(nextKey);
                        q.add(next);
                    }
                }
            }
            res++;
        }
        return -1;
    }

    boolean isInvalid(int row, int col, int rows, int cols) {
        return (row < 0 || col < 0 || row >= rows || col >= cols);
    }

 
    public static void main(String[] arg) {

        System.out.println();

    }

}
