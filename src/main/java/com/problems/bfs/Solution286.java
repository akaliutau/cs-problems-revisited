package com.problems.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class Solution286 {
    
    static final int EMPTY = Integer.MAX_VALUE;
    static final int GATE = 0;
    
    int[] dirX = {1, -1, 0, 0};
    int[] dirY = {0,  0, 1, -1};

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
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


	public static void main(String[] arg) {
		
		System.out.println();

	}

}
