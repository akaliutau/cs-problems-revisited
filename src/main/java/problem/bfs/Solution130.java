package problem.bfs;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions
 * surrounded by 'X'. A region is captured by flipping all 'O's into 'X's in
 * that surrounded region.
 * 
 *  Example: 
 *  X X X X 
 *  X O O X 
 *  X X O X 
 *  X O X X 
 *  
 *  After
 * running your function, the board should be: 
 * 
 * X X X X 
 * X X X X 
 * X X X X 
 * X O X X
 * 
 * IDEA:
 * 1) find all escaped cells using list of boundary cells and bfs from them (mark them temporarily as E)
 * 2) after run - convert all O -> X, and all E -> O
 * 
 * 
 */
public class Solution130 {

    Integer rows = 0;
    Integer cols = 0;

    void bfs(char[][] board, int r, int c) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { r, c });

        while (!queue.isEmpty()) {
            int[] pair = queue.pollFirst();
            int row = pair[0], col = pair[1];
            if (board[row][col] != 'O')
                continue;

            board[row][col] = 'E';
            if (col < cols - 1)
                queue.offer(new int[] { row, col + 1 });
            if (row < rows - 1)
                queue.offer(new int[] { row + 1, col });
            if (col > 0)
                queue.offer(new int[] { row, col - 1 });
            if (row > 0)
                queue.offer(new int[] { row - 1, col });
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        rows = board.length;
        cols = board[0].length;

        List<int[]> borders = new LinkedList<>();
        // Step 1). construct the list of border cells
        for (int r = 0; r < rows; ++r) {
            borders.add(new int[] { r, 0 });
            borders.add(new int[] { r, cols - 1 });
        }
        for (int c = 0; c < cols; ++c) {
            borders.add(new int[] { 0, c });
            borders.add(new int[] { rows - 1, c });
        }

        // Step 2). mark the escaped cells
        for (int[] pair : borders) {
            bfs(board, pair[0], pair[1]);
        }

        // Step 3). flip the cells to their correct final states
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                if (board[r][c] == 'O')
                    board[r][c] = 'X';
                if (board[r][c] == 'E')
                    board[r][c] = 'O';
            }
        }
    }

}
