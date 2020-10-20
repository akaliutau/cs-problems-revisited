package com.problems.dp;

import java.util.Arrays;

/**
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest
 * rectangle containing only 1's and return its area. 
 * Input: matrix = [
 * ["1","0","1","0","0"], 
 * ["1","0","1","1","1"], 
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ] 
 * 
 * Output: 6 Explanation: The maximal rectangle is shown in the above picture
 * 
 * Imagine an algorithm where for each point we computed a rectangle by doing the following:
 * 1) Finding the maximum height of the rectangle by iterating upwards until a 0 is reached
 * 2) Finding the maximum width of the rectangle by iterating outwards left and right
 *    until a height that doesn't accommodate the maximum height of the rectangle
 */
public class Solution85 {

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;

        int[] left = new int[n]; // left[i] - contains leftmost coord (inclusive) of rectangle containing pixel with y=x
        int[] right = new int[n];// right[i] - contains rightmost coord (not-inclusive) of rectangle containing pixel with y=x
        int[] height = new int[n];
        
        Arrays.fill(left, 0);  // initialize left as the leftmost boundary possible
        Arrays.fill(right, n - 1); // initialize right as the rightmost boundary possible

        int maxArea = 0;
        
        for (int i = 0; i < m; i++) {// for each row do:
            int curLeft = 0, curRight = n;
            
            // update height for each column
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                }else { // reset height
                    height[j] = 0;
                }
            }
            // update left for each column
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], curLeft);
                } else {// reset left 
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            // update right for each column
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1')
                    right[j] = Math.min(right[j], curRight);
                else {// reset right
                    right[j] = n - 1;
                    curRight = j - 1;
                }
            }
            // update area
            for (int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, (right[j] - left[j] + 1) * height[j]);
            }
        }
        return maxArea;
    }

}
