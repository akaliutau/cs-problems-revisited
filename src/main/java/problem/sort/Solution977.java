package problem.sort;

/**
 * Given an integer array nums sorted in non-decreasing order, return an array
 * of the squares of each number sorted in non-decreasing order. 
 * 
 * Example 1:
 * Input: nums = [-4,-1,0,3,10] Output: [0,1,9,16,100] 
 * 
 * Explanation: After
 * squaring, the array becomes [16,1,0,9,100]. After sorting, it becomes
 * [0,1,9,16,100].
 * 
 * IDEA:
 * merge 2 arrays for numbers < 0 and numbers > 0
 * dynamically form arrays - i.e. do not generate intermediate one
 */
public class Solution977 {
    
    static int sqr(int a) {
        return a * a;
    }

    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int l = 0, r = 0;
        while (l < n && nums[l] <= 0) {
            l++;
        }
        l --;
        r = l + 1;
        int i = 0;
        while (l >= 0 || r < n) {
            int n1 = l >= 0 ? sqr(nums[l]) : Integer.MAX_VALUE;
            int n2 = r < n ? sqr(nums[r]) : Integer.MAX_VALUE;
            if (n1 > n2) {
                ans[i++] = n2;
                r ++;
            }else {
                ans[i++] = n1;
                l --;
            }
        }
        return ans;
    }

}
