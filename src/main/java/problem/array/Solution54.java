package problem.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 
 * [
 * [1, 1, 1, 1, 1, 1, 1], <- top layer    1, 1, 1, 1, 1, 1, 1
 * [1, 2, 2, 2, 2, 2, 1], 
 * [1, 2, 3, 3, 3, 2, 1],
 * [1, 2, 2, 2, 2, 2, 1], 
 * [1, 1, 1, 1, 1, 1, 1]  <- bottom layer    1, 1, 1, 1, 1
 * ]
 */
public class Solution54 {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) {
            return ans;
        }
        
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        
        while (r1 <= r2 && c1 <= c2) {
            // top layer
            for (int c = c1; c <= c2; c++) {
                ans.add(matrix[r1][c]);
            }
            // right side
            for (int r = r1 + 1; r <= r2; r++) {
                ans.add(matrix[r][c2]);
            }
            if (r1 < r2 && c1 < c2) {// to avoid duplicates
                // bottom layer
                for (int c = c2 - 1; c > c1; c--) {
                    ans.add(matrix[r2][c]);
                }
                // left side
                for (int r = r2; r > r1; r--) {
                    ans.add(matrix[r][c1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
