package problem.twopointers;

/**
 * DP, 2 pointers
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * 
 * IDEA:
 * the vertical bar of water at point i is defined as the highest available level at this point (line 33)
 * 
 */
public class Solution42 {

	public int trap(int[] arr) {
		int n = arr.length;
        if (n == 0){
            return 0;
        }
        int left[] = new int[n]; 
        int right[] = new int[n]; 
  
        int maxVol = 0; 
  
        left[0] = arr[0]; 
        for (int i = 1; i < n; i++) { 
            left[i] = Math.max(left[i - 1], arr[i]);
        }
        right[n - 1] = arr[n - 1]; 
        for (int i = n - 2; i >= 0; i--) { 
            right[i] = Math.max(right[i + 1], arr[i]);
        }
  
        for (int i = 0; i < n; i++) { 
            maxVol += Math.min(left[i], right[i]) - arr[i];// for stable bar of water boundary must the the min of 2
        }
  
        return maxVol; 

	}


}
