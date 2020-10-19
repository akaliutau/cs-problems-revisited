package com.problems.binarysearch;

/**
 * Binary search
 * 
 * 1   3   5   7
 * 10  11  16  20
 * 23  30  34  50
 * 
 * 
 * O(n+m) 
 */
public class Solution74 {

    public boolean searchMatrix(int[][] m, int target) {
        int col = 0, row = 0;
        if (m == null){
            return false;
        }
        int rows = m.length;
        int cols = 0;
        if (rows > 0){
            cols = m[0].length;
        }
        if (rows == 0 || cols == 0){
            return false;
        }
        while(col < cols){
            if (m[row][col] == target){
                return true;
            }
            if (row + 1 < rows && m[row + 1][col] <= target){
                row++;
                continue;
            }
            col++;
        }
        
        return false;
        
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
