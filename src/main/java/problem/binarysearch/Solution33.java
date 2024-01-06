package problem.binarysearch;

/**
 * You are given an integer array nums sorted in ascending order, and an integer
 * target. Suppose that nums is rotated at some pivot unknown to you beforehand
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). If target is found in
 * the array return its index, otherwise, return -1
 * 
 * IDEA:
 *  cut the array in half and analyze the parts
 * 
 * 
 * [4,5,6,7,0,1,2]
 *  |     | 
 *  case 1
 * 
 * start  mid
 *  /    /
 * [4,5,6,7,0,1,2]
 *  |   |   |   |
 *  case 2   case 2
 * 
 * [4,5,6,7,0,1,2]
 *      |     | 
 *   case 3
 */
public class Solution33 {

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[start] <= nums[mid]) {
                if (nums[start] <= target && target < nums[mid]) {// tgt in growing part
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;// safe to update right as target somewhere to the right of mid
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

 
}
