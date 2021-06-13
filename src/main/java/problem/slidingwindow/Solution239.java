package problem.slidingwindow;

/**
 * You are given an array of integers nums, there is a sliding window of size k 
 * which is moving from the very left of the array to the very right. 
 * You can only see the k numbers in the window. 
 * Each time the sliding window moves right by one position.
 * 
 * Window position              Max 
 * ---------------             ----- 
 * [1  3  -1] -3  5  3  6  7     3 
 *  1 [3  -1  -3] 5  3  6  7     3 
 *  1  3 [-1  -3  5] 3  6  7     5 
 *  1  3  -1 [-3  5  3] 6  7     5 
 *  1  3  -1  -3 [5  3  6] 7     6 
 *  1  3  -1  -3  5 [3  6  7]    7
 *  
 *  |      |   |     |  |  |
 *    blk1       blk2   blk3
 *    
 *  1  3  3| -3  5  5|  6  7     :: left  
 *  3  3 -1|  5  5  3|  7  7     :: right
 *  
 *  
 *  In details:
 *  [-3  5  3]
 *            [6  3  6]  <-- non-overlapping windows are not a problem
 *  
 *   <-------------
 *       |  | 
 *  [-3  5  3]             + pre-calculated 
 *      [5  3  6]
 *            [6  3  6]    + pre-calculated 
 *             |
 *       ------------->   
 *      [5  3][6]  
 *       max1  max2   
 *        max = max(max1, max2)   
 *                           
 *  IDEA: 
 *  split on k-blocks and use partial maxs to speed up 
 */
public class Solution239 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // fast check
        if (n * k == 0) {
            return new int[0];
        }
        
        if (k == 1) {
            return nums;
        }
        //  left[j] is a maximum element from the beginning of the block to index j, direction left->right
        int[] left = new int[n];
        left[0] = nums[0];
        
        // building left/right arrays 
        for (int i = 1; i < n; i++) {
            if (i % k == 0) {
                left[i] = nums[i]; // block_start
            }else {
                left[i] = Math.max(left[i - 1], nums[i]);
            }
        }

        // right[j] is a maximum element from the end of the block to index j, direction right->left
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        
        for (int j = n - 2; j > -1; j--) {
            if ((j + 1) % k == 0) {
                right[j] = nums[j]; // block_end
            }else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        int[] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++) {
            // use left elem of right blocks and right elem of left blocks
            output[i] = Math.max(right[i], left[i + k - 1]);
        }

        return output;
    }

  

}
