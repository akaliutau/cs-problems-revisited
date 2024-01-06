package problem.twopointers;

/**
 * Given a matrix mat where every row is sorted in strictly increasing order,
 * return the smallest common element in all rows. If there is no common
 * element, return -1. 
 * 
 * Example 1: Input: mat = 
 * [
 *  
 * pos 
 * 0   [1,2,3,4,5 ], 
 * 0   [2,4,5,8,10],
 * 0   [3,5,7,9,11], 
 * 0   [1,3,5,7,9 ] 
 * 
 * ] 
 * Output: 5 
 * 
 * Note: all elems > 0
 * 
 * Initialize row positions, current max and counter with zeros. 
 * 
 * IDEA:
 * use multi-row tracking
 * For each row: Increment the row position until the
 * value is equal or greater than the current max. If we reach the end of the
 * row, return -1. If the value equals the current max, increase the counter.
 * Otherwise, reset the counter to 1 and update the current max. If the counter
 * is equal to n, return the current max. Repeat step.
 */
public class Solution1198 {

    public int smallestCommonElement(int[][] mat) {
        int rows = mat.length, m = mat[0].length;
        int pos[] = new int[rows];
        int globalMax = Integer.MIN_VALUE, maxCounter = 0;
        while (true) {
            for (int i = 0; i < rows; ++i) {
                
                while (pos[i] < m && mat[i][pos[i]] < globalMax) {// find elem >= globalMax
                    ++pos[i];
                }
                if (pos[i] >= m) {// not found
                    return -1;
                }
                int col = pos[i];
                // here: curElem >= globalMax
                int curElem = mat[i][col];
                if (curElem > globalMax) {// it must be > case, new globalMax found,
                	maxCounter = 1;
                    globalMax = curElem;
                } else if (++maxCounter == rows) {// == case
                    return globalMax;
                }
            }
        }
    }

}
