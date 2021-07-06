package problem.dp;

/**
 * 
 * You are given two sorted arrays of distinct integers nums1 and nums2.
 * 
 * A valid path is defined as follows:
 * 
 * Choose array nums1 or nums2 to traverse (from index-0). Traverse the current
 * array from left to right. If you are reading any value that is present in
 * nums1 and nums2 you are allowed to change your path to the other array. (Only
 * one repeated value is considered in the valid path). Score is defined as the
 * sum of uniques values in a valid path.
 * 
 * Return the maximum score you can obtain of all possible valid paths.
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * IDEA:
 * 
 * 
 * 
 */
public class Solution1537 {
	
	int mod = 1000000007;
    public int maxSum(int[] nums1, int[] nums2) {
        long sum1 = 0L, sum2 = 0L;
        
        int i1 = 0, i2 = 0;
        int n = nums1.length;
        int m = nums2.length;
        
        long sum = 0;
        while(i1 < n && i2 < m) {
			// sum elements up to the join point
            while(i1 < n && nums1[i1] < nums2[i2]) {
            	sum1 += nums1[i1++];
            }
            
            while(i1 < n && i2 < m && nums2[i2] < nums1[i1]) {
            	sum2 += nums2[i2++];
            }
            
            if(i1<n && i2 < m && nums1[i1] == nums2[i2]){
				// add larger sum to the global sum variable
                sum += Math.max(sum1 + nums1[i1], sum2 + nums2[i2]);
                sum %= mod;
                i1++;
                i2++;
                sum1 = 0;
                sum2 = 0;
            }
        }
		// add the elements of the other array
        while(i1 < n) {
            sum1 += nums1[i1++];
        }
        while(i2 < m) {
            sum2 += nums2[i2++];
        }
        
        sum += Math.max(sum1, sum2);
        
        return (int)(sum%mod);
    }

}
