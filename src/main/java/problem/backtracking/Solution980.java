package problem.backtracking;

/**
 * On a 2-dimensional grid, there are 4 types of squares: 
 * 
 *  1 represents the starting square. There is exactly one starting square. 
 * 
 *  2 represents the ending square. There is exactly one ending square. 
 *
 *  0 represents toDo squares we can walk over. 
 * 
 * -1 represents obstacles that we cannot walk over. 
 * 
 * Return
 * the number of 4-directional walks from the starting square to the ending
 * square, that walk over every non-obstacle square exactly once. 
 * 
 * Example 1:
 * Input: 
 * [
 * [1,0,0,0],
 * [0,0,0,0],
 * [0,0,2,-1]
 * ] 
 * 
 * Output: 2 
 * 
 * Explanation: We have the
 * following two paths: 
 * 1.
 * (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2) 
 * 2.
 * (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * 
 * IDEA:
 *  start at any point of board and investigate in all directions 
 *  taking into account all conditions
 * 
 */
public class Solution980 {

    int rows, cols;
    int[][] grid;
    int counter;

    // explore the 4 potential directions around
    int[] xOff = { 0, 0, 1, -1 };
    int[] yOff = { 1, -1, 0, 0 };

    void backtrack(int row, int col, int toDo) {
    	// conditions fro exit
        // base case for the termination of backtracking
        if (grid[row][col] == 2 && toDo == 1) {
            // reach the destination
            counter += 1;
            return;
        }

        // mark the square as visited. case: 0, 1, 2
        int temp = grid[row][col];
        grid[row][col] = -4;// put a lock
        toDo -= 1; // now we now have one less square to visit

        for (int i = 0; i < 4; ++i) {
            int nr = row + xOff[i];
            int nc = col + yOff[i];

            if (0 > nr || nr >= rows || 0 > nc || nc >= cols || grid[nr][nc] < 0)
                // invalid next coordinate || either obstacle || visited square
                continue;

            backtrack(nr, nc, toDo);
        }

        // restore for backtracking
        grid[row][col] = temp;
    }

    public int uniquePathsIII(int[][] grid) {
        int toDo = 0, startRow = 0, startCol = 0;

        rows = grid.length;
        cols = grid[0].length;

        // step 1). initialize the conditions for backtracking
        // i.e. initial state and final state
        for (int row = 0; row < rows; ++row)
            for (int col = 0; col < cols; ++col) {
                int cell = grid[row][col];
                if (cell >= 0)
                    toDo += 1;
                if (cell == 1) {
                    startRow = row;
                    startCol = col;
                }
            }

        counter = 0;
        this.grid = grid;

        // kick-off the backtracking
        backtrack(startRow, startCol, toDo);

        return counter;
    }

}
