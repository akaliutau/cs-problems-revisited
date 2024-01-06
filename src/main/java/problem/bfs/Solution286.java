package problem.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a m x n 2D grid initialized with these three possible values.
 * 
 * -1 - A wall or an obstacle. 
 * 
 *  0 - A gate. 
 *  
 * INF - Infinity means an empty room.
 * 
 * We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that
 * the distance to a gate is less than 2147483647. Fill each empty room with the
 * distance to its nearest gate. If it is impossible to reach a gate, it should
 * be filled with INF. 
 * 
 * Example: Given the 2D grid:
 *  
 * INF  -1   0 INF 
 * INF INF INF  -1
 * INF  -1 INF  -1 
 *   0  -1 INF INF 
 *   
 * After running your function, the 2D grid should
 * be: 
 * 
 * 3 -1 0  1 
 * 2  2 1 -1 
 * 1 -1 2 -1 
 * 0 -1 3  4
 * 
 * IDEA:
 * 
 * Use BFS with multiple sources and "first come - first fill" strategy
 * (will work because problem requires to find the shortest path and bfs provides the shortest )
 * 
 */
public class Solution286 {

    static final int EMPTY = Integer.MAX_VALUE;
    static final int GATE = 0;

    int[] dirX = { 1, -1, 0, 0 };
    int[] dirY = { 0, 0, 1, -1 };

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0)
            return;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) {
                    q.add(new int[] { row, col });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int i = 0; i < 4; i++) {
                int r = row + dirX[i];
                int c = col + dirY[i];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                q.add(new int[] { r, c });
            }
        }
    }



}
