package problem.slidingwindow;

/**
 * Sliding window 
 * 
 * Given an array A of 0s and 1s, we may change up to K values
 * from 0 to 1. Return the length of the longest (contiguous) subarray that
 * contains only 1s. 
 * 
 * Input: A =   [1,1,1,0,0,0,1,1,1,1,0], K = 2 
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * 
 * IDEA: 
 * use [left,right] as contiguous block and dynamically count 0s
 * Rules:
 * 1) if there is no lack of 0s, increase the right boundary, leave the left one untouched
 * 
 * 
 * |
 * 1,1,1,0,0,0,1,1,1,1,0,0
 *           | k=-1, get run of 0s to change
 * 
 *         |
 * 1,1,1,0,0,0,1,1,1,1,0,0
 *           | k = 0, got rid of 1 zero, now can continue extend right boundary
 * 
 *         |
 * 1,1,1,0,0,0,1,1,1,1,0,0
 *                     | k=-1
 * 
 *           |
 * 1,1,1,0,0,0,1,1,1,1,0,0
 *                       |
 * 
 * 
 */
public class Solution1004 {

	 public int longestOnes(int[] arr, int k) {
	        int left = 0;
	        int len = 0;
	        for (int right = 0; right < arr.length; right++) {
	            // If we included a zero in the window we reduce the value of K.
	            // Since K is the maximum zeros allowed in a window.
	            if (arr[right] == 0)
	                k--;
	            // A negative K denotes we have consumed all allowed flips and window has
	            // more than allowed zeros, thus increment left pointer by 1 to keep the window
	            // size same.
	            if (k < 0) {
	                // If the left element to be thrown out is zero we increase K.
	                if (arr[left] == 0)
	                    k++;
	                left++;
	            }
	            if (k >= 0) {
	            	len = Math.max(len, right - left + 1);
	            }
	        }
	        return len;
	    }   

}
