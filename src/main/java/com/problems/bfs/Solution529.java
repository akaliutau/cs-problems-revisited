package com.problems.bfs;

/**
 * Let's play the minesweeper game (Wikipedia, online game)! You are given a 2D
 * char matrix representing the game board. 'M' represents an unrevealed mine,
 * 'E' represents an unrevealed empty square, 'B' represents a revealed blank
 * square that has no adjacent (above, below, left, right, and all 4 diagonals)
 * mines, digit ('1' to '8') represents how many mines are adjacent to this
 * revealed square, and finally 'X' represents a revealed mine. Now given the
 * next click position (row and column indices) among all the unrevealed squares
 * ('M' or 'E'), return the board after revealing this position according to the
 * following rules: If a mine ('M') is revealed, then the game is over - change
 * it to 'X'. If an empty square ('E') with no adjacent mines is revealed, then
 * change it to revealed blank ('B') and all of its adjacent unrevealed squares
 * should be revealed recursively. If an empty square ('E') with at least one
 * adjacent mine is revealed, then change it to a digit ('1' to '8')
 * representing the number of adjacent mines. Return the board when no more
 * squares will be revealed. 
 * 
 * Example 1: 
 * Input: 
 * [
 * ['E', 'E', 'E', 'E', 'E'], 
 * ['E', 'E', 'M', 'E', 'E'], 
 * ['E', 'E', 'E', 'E', 'E'], 
 * ['E', 'E', 'E', 'E', 'E']
 * ]
 * Click : [3,0] 
 * 
 * Output: 
 * [
 * ['B', '1', 'E', '1', 'B'], 
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'], 
 * ['B', 'B', 'B', 'B', 'B']
 * ]
 */
public class Solution529 {
    
    
    int checkNeighbours(char[][] board, int r, int c, int n, int m) {
        int ans = 0;
        for (int i = 0; i < 9; i++) {
            int nx = r + xOffset[i]; 
            int ny = c + yOffset[i]; 
            if (isValid(nx, ny, n, m)) {
                if (board[nx][ny] == 'M') ans++;
            }
        }
        return ans;
    }
    
    boolean isValid(int x, int y, int n, int m) {
        return x >=0 && x < n && y >= 0 && y < m;
            
    }
    
    int[] xOffset = {-1,  0,  1, -1, 1, -1, 0, 1};
    int[] yOffset = {-1, -1, -1,  0, 0,  1, 1, 1};  
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0], c = click[1];
        int n = board.length;
        int m = 0;
        if (n > 0) {
           m = board[0].length;
        }
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
        }
        
        if (board[r][c] == 'E') {
            int mines = checkNeighbours(board, r, c, n, m);
            if (mines > 0) {
                board[r][c] = String.valueOf(mines).charAt(0);
            } else {
                board[r][c] = 'B';
                for (int i = 0; i < 8; i++) {
                    int nx = r + xOffset[i]; 
                    int ny = c + yOffset[i]; 
                    if (isValid(nx, ny, n, m)) {
                        int[] nextClick = {nx, ny};
                        if (board[nx][ny] == 'E') {
                            updateBoard(board, nextClick);
                        }
                    }
                }
            }
        }
        return board;
    }
    
    


}
