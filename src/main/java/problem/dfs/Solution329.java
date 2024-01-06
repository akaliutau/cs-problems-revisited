package problem.dfs;

/**
 * Given an integer matrix, find the length of the longest increasing path. From
 * each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around
 * is not allowed). 
 * 
 * Example 1: 
 * nums = 
 * [ 
 * [9,9,4], 
 * [6,6,8], 
 * [2,1,1] 
 * ]
 * Output: 4 
 * 
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * 
 * IDEA: 
 * build path from pieces
 * 1) for each cell (i,j): build the longest path starting at (i,j) and save it in memo
 * 2) regardless of found order for each piece, the final resulting longest path will be found
 * 
 *  proof: 
 *  if start at cell 1 (bottom right), will be found pieces 1 -> 8, or the actual result 1 -> 2 -> 6 -> 9
 *  if start at cell 6, will be found pieces 6 -> 8, 6 -> 9
 * 
 */
public class Solution329 {

    private static final int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private int m, n;
    
    int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) {// cache hit
            return cache[i][j];
        }
        
        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];
            // move forward only for the bigger numbers
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {// choose the path according to conditions
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
            }
        }
        cache[i][j] = cache[i][j] + 1;
        return cache[i][j];
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n]; // contains max len from some (undefined) cell + visitedMap
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j, cache));
            }
        }
        return ans;
    }

    

}
