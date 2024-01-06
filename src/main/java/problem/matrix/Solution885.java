package problem.matrix;

/**
 * On a 2 dimensional grid with R rows and C columns, we start at (r0, c0)
 * facing east. Here, the north-west corner of the grid is at the first row and
 * column, and the south-east corner of the grid is at the last row and column.
 * Now, we walk in a clockwise spiral shape to visit every position in this
 * grid. Whenever we would move outside the boundary of the grid, we continue
 * our walk outside the grid (but may return to the grid boundary later.)
 * Eventually, we reach all R * C spaces of the grid. Return a list of
 * coordinates representing the positions of the grid in the order they were
 * visited. 
 * 
 * Example 1: Input: R = 1, C = 4, r0 = 0, c0 = 0 Output:
 * [[0,0],[0,1],[0,2],[0,3]] 
 * 
 * Example 2: Input: R = 5, C = 6, r0 = 1, c0 = 4
 * Output:
 * [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],
 *  [3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],
 *  [4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
 */
public class Solution885 {
    
    static int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
    
    static boolean isValid(int r, int c, int posR, int posC) {
        return posR >= 0 && posR < r && posC >= 0 && posC < c;
    }

    public int[][] spiralMatrixIII(int r, int c, int r0, int c0) {
        int sz = r * c;
        int[][] ans = new int[sz][2];
        int idx = 0;
        int dir = 0;
        int dist = 1;
        int step = 0;
        int shift = dist;
        while (idx < sz) {
            int[] pos = new int[2];
            pos[0] = r0;
            pos[1] = c0;
            if (isValid(r, c, pos[0], pos[1])) {
                ans[idx++] = pos;
            }
            r0 +=  dirs[dir][0];
            c0 +=  dirs[dir][1];
            shift --;
            if (shift == 0) {
                step ++;
                if (step % 2 == 0) {
                    dist ++;
                }
                shift = dist;
                dir ++;
                dir %= 4;
            }
        }
        return ans;
    }
}
