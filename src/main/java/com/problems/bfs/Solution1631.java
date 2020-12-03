package com.problems.bfs;

import java.util.PriorityQueue;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D
 * array of size rows x columns, where heights[row][col] represents the height
 * of cell (row, col). You are situated in the top-left cell, (0, 0), and you
 * hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e.,
 * 0-indexed). You can move up, down, left, or right, and you wish to find a
 * route that requires the minimum effort. A route's effort is the maximum
 * absolute difference in heights between two consecutive cells of the route.
 * Return the minimum effort required to travel from the top-left cell to the
 * bottom-right cell. 
 * 
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]] Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2
 * in consecutive cells. This is better than the route of [1,2,2,2,5], where the
 * maximum absolute difference is 3.
 */
public class Solution1631 {

    class Node {
        int row;
        int col;
        int height;
        Node(int row, int col, int wt){
            this.row = row;
            this.col = col;    
            this.height = wt;
        }
     }
    
    static int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    
    public int minimumEffortPath(int[][] heights) {
        
        int m = heights.length;
        int n = heights[0].length;
        
        PriorityQueue<Node>pq = new PriorityQueue<>((o,p) -> o.height - p.height);
        pq.add(new Node(0,0,0)); //top left cell
        
        int max = Integer.MIN_VALUE;
        boolean [][] visited = new boolean[m][n];
        
        while(!pq.isEmpty()){
            Node nd = pq.poll();
            int r = nd.row;
            int c = nd.col;
            
            max = Math.max(max, nd.height);
            
            if( r == m-1 && c == n-1) {
                break; //we are in the bottom right cell
            }
            
            if(visited[r][c]) {
                continue;
            }
            visited[r][c] = true;
            
            for (int[] dir : dirs) {//move in all the four possible directions
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]){
                    int effort = Math.abs(heights[r][c]-heights[nr][nc]);
                    pq.add(new Node(nr, nc, effort));
                }
            }
        }
        return max;
    }

}
