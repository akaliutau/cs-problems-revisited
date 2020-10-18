package com.problems.binarysearch;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix. Note that it is
 * the kth smallest element in the sorted order, not the kth distinct element.
 * 
 * Example: matrix = 
 * [ 
 * [ 1, 5, 9], 
 * [10, 11, 13], 
 * [12, 13, 15] 
 * ], 
 * k = 8, return 13.
 */
public class Solution378 {
    
    int countOnRangeLE(int[][] matrix, int mid, int[] smallLargePair) {

        int count = 0;
        int n = matrix.length, row = n - 1, col = 0;

        while (row >= 0 && col < n) {

            if (matrix[row][col] > mid) {

                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;

            } else {

                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;// ate least all elems on [0,row] are smaller
                col++;
            }
        }

        return count;
    }

    public int kthSmallest(int[][] matrix, int k) {

        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n - 1][n - 1];
        while (start < end) {

            int mid = start + (end - start) / 2;
            // first number is the smallest elem on range
            // second number is the largest elem on range
            
            int[] smallLargePair = { matrix[0][0], matrix[n - 1][n - 1] };

            int count = countOnRangeLE(matrix, mid, smallLargePair);

            if (count == k)
                return smallLargePair[0];

            if (count < k)
                start = smallLargePair[1]; // search higher
            else
                end = smallLargePair[0]; // search lower
        }
        return start;
    }

    

    public static void main(String[] arg) {

        System.out.println();

    }

}
