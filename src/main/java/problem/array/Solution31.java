package problem.array;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers. If such an arrangement
 * is not possible, it must rearrange it as the lowest possible order (i.e.,
 * sorted in ascending order). The replacement must be in place and use only
 * constant extra memory. Example 1: Input: nums = [1,2,3] Output: [1,3,2]
 * 
 * [1,3,5,4]
 *
 * [1,4,5,3] -> [1,4,3,5] reverse
 * 
 * 
 * 5     o
 * 4     oo
 * 3    ooo
 * 2    ooo
 * 1   oooo
 *
 * Algorithm:
 *
 * 1) find the 1st unordered pair in growing seq of digits
 * 2) get the point to elem just before this pair
 * 3) find the digit which is bigger than the pin-pointed one in after sequence
 * 4) swap
 *
 * IDEA:
 * 0) represent visually a number as a histogram 
 * 1) find a peak 
 * 2) the NEXT greater number obviously must be a number created from this one by 
 *    a) finding the smallest possible change - can be done by searching from the tail backwards
 *    b) swapping these elements  
 * 3) reversing the tail AFTER peak (including peak) is needed due to guaranteed smallest number  
 * 
 */
public class Solution31 {
	
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // reverses [1,2,3] -> [3,2,1]
    void reverse(int[] nums, int from) {
        int i = from, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {// find a peak in a histogram - an asc order from the tail
            i--;
        }
        // now [i+1] is the peak, [i] - the elem just before the peak
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {// find the 1st elem greater or equal than found one 
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

 

}
