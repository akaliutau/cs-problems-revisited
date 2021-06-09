package problem.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are starving and you want to eat food as quickly as possible. You want to
 * find the shortest path to arrive at any food cell.
 * 
 * You are given an m x n character matrix, grid, of these different types of
 * cells:
 * 
 * '*' is your location. There is exactly one '*' cell.
 * 
 * '#' is a food cell. There may be multiple food cells.
 * 
 * 'O' is free space, and you can travel through these cells.
 * 
 * 'X' is an obstacle, and you cannot travel through these cells.
 * 
 * You can travel to any adjacent cell north, east, south, or west of your
 * current location if there is not an obstacle.
 * 
 * Return the length of the shortest path for you to reach any food cell. If
 * there is no path for you to reach food, return -1.
 * 
 * IDEA: 
 * use pure BFS, layer by layer propagation
 * 
 * ["X","X","X","X","X"]
 * ["X","*","X","O","X"]
 * ["X","O","X","#","X"]
 * ["X","X","X","X","X"]
 *
 */
public class Solution1730 {
	   static int[] dirs = {-1,0, 1,0, 0,-1, 0,1};
	    
	    
	    static int[] find(char[][] grid, int n, int m){
	        for (int i = 0; i < n; i++){
	            for (int j = 0; j < m; j++){
	                if (grid[i][j] == '*'){
	                    return new int[]{i, j};
	                }    
	            }
	        }
	        return null;
	    }
	    
	    public int getFood(char[][] grid) {
	        int n = grid.length;
	        int m = 0;
	        if (n > 0){
	            m = grid[0].length;
	        }
	        int steps = 0;
	        Queue<int[]> q = new LinkedList<>();
	        int[] start = find(grid, n, m);
	        q.add(start);
	        boolean[][] seen = new boolean[n][m];
	        seen[start[0]][start[1]] = true;
	        
	        while(!q.isEmpty()){
	            int sz = q.size();
	            for (int i = 0; i < sz; i++){
	                int[] pos = q.poll();
	                int x = pos[0];
	                int y = pos[1];
	                if (grid[x][y] == '#'){
	                    return steps;
	                }
	                for (int dir = 0; dir < 8; dir +=2){
	                    int nx = x + dirs[dir];
	                    int ny = y + dirs[dir+1];
	                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && !seen[nx][ny] && grid[x][y] != 'X' && grid[x][y] != 'X'){
	                        q.add(new int[]{nx, ny});
	                        seen[nx][ny] = true;
	                    }
	                }
	            }
	            steps ++;
	        }
	        
	        return -1;
	        
	    }

}
