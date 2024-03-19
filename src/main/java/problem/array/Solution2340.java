package problem.array;

/**
 * You are given a 0-indexed integer array nums.
 *
 * Swaps of adjacent elements are able to be performed on nums.
 *
 * A valid array meets the following conditions:
 *
 * The largest element (any of the largest elements if there are multiple) is at the rightmost position in the array.
 * The smallest element (any of the smallest elements if there are multiple) is at the leftmost position in the array.
 * Return the minimum swaps required to make nums a valid array.
 *
 */
class Solution2340 {

    public int minimumSwaps(int[] nums) {
        if (nums.length == 1) return 0;
        int[] minElem = new int[]{1_000_000, -1};
        int[] maxElem = new int[]{0, -1};
        int n = nums.length;
        for (int i = 0; i < n; i++){
            if (nums[i] < minElem[0]){
                minElem[0] = nums[i];
                minElem[1] = i;
            }
        }
        for (int i = n - 1; i > -1; i--){
            if (nums[i] > maxElem[0]){
                maxElem[0] = nums[i];
                maxElem[1] = i;
            }
        }
        int ans = 0;
        ans += minElem[1];
        ans += n-1 - maxElem[1];
        if (minElem[1] > maxElem[1]) ans --;
        return ans;

    }
}