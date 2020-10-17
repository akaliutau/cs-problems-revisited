package com.problems.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * An image is represented by a 2-D array of integers, each integer representing
 * the pixel value of the image (from 0 to 65535).
 * 
 * Given a coordinate (sr, sc) representing the starting pixel (row and column)
 * of the flood fill, and a pixel value newColor, "flood fill" the image.
 * 
 * To perform a "flood fill", consider the starting pixel, plus any pixels
 * connected 4-directionally to the starting pixel of the same color as the
 * starting pixel, plus any pixels connected 4-directionally to those pixels
 * (also with the same color as the starting pixel), and so on. Replace the
 * color of all of the aforementioned pixels with the newColor.
 * 
 * At the end, return the modified image.
 * 
 * Example 1: Input: image = 
 * [
 * [1,1,1],
 * [1,1,0],
 * [1,0,1]
 * ] 
 * sr = 1, sc = 1, newColor = 2 
 * Output: 
 * [
 * [2,2,2],
 * [2,2,0],
 * [2,0,1]
 * ] 
 * Explanation: From the center of the
 * image (with position (sr, sc) = (1, 1)), all pixels connected by a path of
 * the same color as the starting pixel are colored with the new color. Note the
 * bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
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
