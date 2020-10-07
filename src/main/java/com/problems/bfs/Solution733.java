package com.problems.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * BFS
 */
public class Solution733 {

    static boolean isValid(int[][] image, int i, int j, int n, int m, int color, boolean[][] bmap) {
        return i >= 0 && i < n && j >= 0 && j < m && image[i][j] == color && !bmap[i][j];
    }

    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

        int rows = image.length;
        int cols = 0;
        if (rows > 0) {
            cols = image[0].length;
        }

        boolean[][] visited = new boolean[rows][cols];
        int pickColor = image[sr][sc];
        visited[sr][sc] = true;
        image[sr][sc] = newColor;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { sr, sc });
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int i = cur[0] + dx[dir];
                int j = cur[1] + dy[dir];
                if (isValid(image, i, j, rows, cols, pickColor, visited)) {
                    visited[i][j] = true;
                    image[i][j] = newColor;
                    queue.add(new int[] { i, j });
                }
            }
        }
        return image;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
