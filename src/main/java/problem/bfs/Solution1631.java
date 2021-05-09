package problem.bfs;

import java.util.PriorityQueue;

/**
 * You are a hiker preparing for an upcoming hike. You are given efforts, a 2D
 * array of size rows x columns, where efforts[row][col] represents the effort
 * of cell (row, col). You are situated in the top-left cell, (0, 0), and you
 * hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e.,
 * 0-indexed). You can move up, down, left, or right, and you wish to find a
 * route that requires the minimum effort. A route's effort is the maximum
 * absolute difference in efforts between two consecutive cells of the route.
 * Return the minimum effort required to travel from the top-left cell to the
 * bottom-right cell. 
 * 
 * Input: efforts = 
 * [
 * [1,2,2],
 * [3,8,2],
 * [5,3,5]
 * ] 
 * 
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2
 * in consecutive cells. This is better than the route of [1,2,2,2,5], where the
 * maximum absolute difference is 3.
 * 
 * IDEA (similar to 1102):
 * 
 *  As a result all cells in queue will be prioritized by min effort on the top => optimal 
 *    [not necessary the shortest] path 
 *    
 * explicit greedy approach - we are trying the effortless paths first   
 */
public class Solution1631 {

    class Node {
        int row;
        int col;
        int effort;
        Node(int row, int col, int wt){
            this.row = row;
            this.col = col;    
            this.effort = wt;
        }
     }
    
    static int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    
    public int minimumEffortPath(int[][] height) {
        
        int m = height.length;
        int n = height[0].length;
        
        PriorityQueue<Node>pq = new PriorityQueue<>((o,p) -> o.effort - p.effort);
        pq.add(new Node(0,0,0)); //top left cell
        
        int max = Integer.MIN_VALUE;
        boolean [][] visited = new boolean[m][n];
        
        while(!pq.isEmpty()){
            Node nd = pq.poll();
            int r = nd.row;
            int c = nd.col;
            
            // this is working because of greedy approach
            // we NEVER run into big values until ALL SMALLER have been tried
            // and if we succeed  then will exit on next line 71
            max = Math.max(max, nd.effort);
            
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
                	// the effort is defined as the difference in heights
                    int effort = Math.abs(height[r][c]-height[nr][nc]);
                    pq.add(new Node(nr, nc, effort));
                }
            }
        }
        return max;
    }

}
