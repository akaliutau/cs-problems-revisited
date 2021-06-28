package problem.unionfind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given an empty 2D binary grid grid of size m x n. The grid represents
 * a map where 0's represent water and 1's represent land. Initially, all the
 * cells of grid are water cells (i.e., all the cells are 0's).
 * 
 * We may perform an add land operation which turns the water at position into a
 * land. You are given an array positions where positions[i] = [ri, ci] is the
 * position (ri, ci) at which we should operate the ith operation.
 * 
 * Return an array of integers answer where answer[i] is the number of islandsToJoin
 * after turning the cell (ri, ci) into a land.
 * 
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 * 
 * IDEA:
 * 
 * Analyze all neighbors (islandsToJoin) and repaint them on merge
 * 
 * 3x3 board
 * 
 * 0 0 0    0 1 0    0 1 0    0 1 0    0 1 0    0 1 1    1 1 1    1 1 1   
 * 0 0 0    0 0 0    0 0 1    0 0 1    1 0 1    1 0 1    1 0 1    1 1 1
 * 0 0 0    0 0 0    0 0 0    0 1 0    0 1 0    0 1 0    0 1 0    0 1 0 
 * 
 * 0        1        2        3        4        3        2        1          
 * 
 *  Note: the color of repainting is not important; only solidness does matter
 *  (i.e. all parts of the same island must have the same color)
 *  
 * [[0,1],[1,2],[2,1],[1,0],[0,2],[0,0],[1,1]]
 * 
 */
public class Solution305 {
    static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    
    void repaint(int x, int y, int m, int n, int[][] map, int col){// repaint all in touch
        map[x][y] = col;
        for (int[] dir : dirs){
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >=0 && ny >=0 && nx < m && ny < n && map[nx][ny] != col && map[nx][ny] != 0){
                repaint(nx, ny, m, n, map, col);
            }
        }
    }
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        
        int col = 2;
        int count = 0;
        List<Integer> ans = new ArrayList<>();
        int[][] map = new  int[m][n];
        for (int[] pos : positions){
            int x = pos[0];
            int y = pos[1];
            if (map[x][y] != 0){// put value in the same dot
                ans.add(count);
                continue;
            }
            // put into empty cell and repaint neighbors
            Set<Integer> islandsToJoin = new HashSet<>();
            for (int[] dir : dirs){
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >=0 && ny >=0 && nx < m && ny < n && map[nx][ny] != 0){
                    islandsToJoin.add(map[nx][ny]);
                }
            }
            List<Integer> ni = new ArrayList<>(islandsToJoin);
            if (ni.size() == 0){//no neighbors
                count ++;
                map[x][y] = col ++;
            }else if (ni.size() == 1){
                map[x][y] = ni.get(0);
                col++;
            }else if (ni.size() >= 2){
                count -= ni.size() - 1;
                map[x][y] = col ++;
                repaint(x, y, m, n, map, col++);
            }
            ans.add(count);
        }
        return ans;
        
    }

}
