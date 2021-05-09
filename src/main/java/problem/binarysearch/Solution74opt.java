package problem.binarysearch;

/**
 * Binary search
 * 
 * 1   3   5   7
 * 10  11  16  20
 * 23  30  34  50
 * 
 * 1   3   5   7  10  11  16  20  23  30  34  50
 * |                  |                       |
 *               pivotElement       
 * 
 * O(log(n) + log(m))
 */
public class Solution74opt {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0)
            return false;
        int n = matrix[0].length;

        // binary search
        int left = 0, right = m * n - 1;
        int mid, pivotElement;
        while (left <= right) {
            mid = (left + right) / 2;
            pivotElement = matrix[mid / n][mid % n];
            if (target == pivotElement) {
                return true;
            }else {
                if (target < pivotElement) {
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }
    

 

}
