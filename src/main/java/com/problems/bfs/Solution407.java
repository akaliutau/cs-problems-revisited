package com.problems.bfs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an m x n matrix of positive integers representing the height of each
 * unit cell in a 2D elevation map, compute the volume of water it is able to
 * trap after raining. 
 * 
 * Example: 
 * Given the following 3x6 height map: 
 * [
 * [1,4,3,1,3,2], 
 * [3,2,1,3,2,4], 
 * [2,3,3,2,3,1] 
 * ] 
 * Return 4.
 */
public class Solution407 {

    class Pos {
        public int val;
        public int i;
        public int j;

        public Pos(int val, int i, int j) {
            this.val = val;
            this.i = i;
            this.j = j;
        }
    }

    public int trapRainWater(int[][] height) {
        Comparator<Pos> byVal = (a, b) -> a.val - b.val;
        PriorityQueue<Pos> minheap = new PriorityQueue<>(byVal);// min level on the top
        int n = height.length;
        int m = height[0].length;

        for (int j = 0; j < m; j++) {
            minheap.add(new Pos(height[0][j], 0, j));// global boundary
            minheap.add(new Pos(height[n - 1][j], n - 1, j));// global boundary
            height[0][j] = -1;// boundary cells cannot take water
            height[n - 1][j] = -1;
        }

        for (int i = 0; i < n; i++) {
            minheap.add(new Pos(height[i][0], i, 0));// global boundary
            minheap.add(new Pos(height[i][m - 1], i, m - 1));// global boundary
            height[i][0] = -1;
            height[i][m - 1] = -1;
        }

        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int volume = 0;

        while (!minheap.isEmpty()) {// from outside into inner cells
            Pos p = minheap.remove();// always take the smallest level

            int curHeight = p.val;
            int i = p.i;
            int j = p.j;

            for (int k = 0; k < dir.length; k++) {// compare with neighbors
                int ni = i + dir[k][0];
                int nj = j + dir[k][1];

                if (ni < 0 || ni > n - 1 || nj < 0 || nj > m - 1 || height[ni][nj] == -1) {
                    continue;
                }
                int neighbourHeight = height[ni][nj]; 
                if (neighbourHeight < curHeight) {
                    volume += (curHeight - neighbourHeight);// because its global min
                    minheap.add(new Pos(curHeight, ni, nj));
                } else {
                    minheap.add(new Pos(neighbourHeight, ni, nj));
                }
                height[ni][nj] = -1;
                
            }
        }

        return volume;
    }

}
