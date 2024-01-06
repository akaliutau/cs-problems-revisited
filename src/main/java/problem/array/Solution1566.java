package problem.array;

/**
 * Given an array of positive integers arr, find a pattern of length m that is
 * repeated k or more times. A pattern is a subarray (consecutive sub-sequence)
 * that consists of one or more values, repeated multiple times consecutively
 * without overlapping. A pattern is defined by its length and the number of
 * repetitions. Return true if there exists a pattern of length m that is
 * repeated k or more times, otherwise return false. 
 * 
 * Example 1: Input: arr = [1,2,4,4,4,4], m = 1, k = 3 Output: true 
 * 
 * Explanation: The pattern (4) of
 * length 1 is repeated 4 consecutive times. Notice that pattern can be repeated
 * k or more times but not less.
 * 
 * IDEA:
 * ___  ___  ___
 * 1,2, 1,2, 1,2
 * 
 * b1   b2   b3  
 * 
 * Go though block [b_i], comparing it simultaneously with block [b_i+1]
 * if total length of filled cells ==   (k - 1) * m, pattern found
 * 
 * Like so:
 * b1 -> b2 (use 2 pointers inside blocks b1 & b2 to go through each elem of each block)
 * b2 -> b3
 * 
 */
public class Solution1566 {

    public boolean containsPattern(int[] arr, int m, int k) {
        int commonLength = 0;
        for (int i = 0; i + m < arr.length; i++) {
        	if (arr[i] == arr[i + m]) {// some element in the next block
        		commonLength ++;
        	}else {
        		commonLength = 0;// pattern broken because blocks needed to be consequent, so reset the total length
        	}
            if (commonLength == (k - 1) * m) {// total number of common elements
                return true;
            }
        }
        return false;
    }

}
