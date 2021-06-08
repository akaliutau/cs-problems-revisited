package problem.segmenttree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts
 * array. The counts array has the property where counts[i] is the number of
 * smaller elements to the right of nums[i].
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [5,2,6,1] Output: [2,1,1,0]
 * 
 * Explanation: To the right of 5 there are 2 smaller elements (2 and 1). To the
 * right of 2 there is only 1 smaller element (1). To the right of 6 there is 1
 * smaller element (1). To the right of 1 there is 0 smaller element.
 * 
 * IDEA:
 * Build Segment Tree to solve the problem.
 * 
 *                       [0 5 2 6 1]
 *                 
 *   [0,6: 0]                  [0,6: 1]                 
 *                          /           \
 *                      [0,3:1]          [4,6:0] 
 *                      /     \
 *                  [0,1:1]   [2,3:0]    
 * 
 * Initial state: []
 * add(1)                    
 * 
 * 
 * Acceleration:
 * 
 * For a particular element in nums, located at index i, we want to count how many of the numbers on the right side of index i are smaller than nums[i].
 * Since we need counts of values, we can use an approach similar to bucket sort, where we have buckets of values and buckets[value] stores the count of value
 * 
 * nums:         [5,2,6,1]
 * buckets:  [0 1 1 0 0 0 1]           ]
 *            0 1 2 3 4 5 6     <-- needed max(nums) buckets to track all numbers
 *            
 * number of elems < 5 == number of 1s in the range (-inf,5)
 * 
 * 1. Dynamically build Segment Tree during traversing 1 -> 6 -> 2 -> 5
 * 2. Query tree to find the # of 1s in the range
 * 
 */
public class Solution315a {
	
    void update(int idx, int value, int[] tree, int size) {
        idx += size; // shift the idx to the leaf
        // update from leaf to root
        tree[idx] += value;
        while (idx > 1) {
            idx /= 2;
            tree[idx] = tree[idx * 2] + tree[idx * 2 + 1];
        }
    }

    int query(int left, int right, int[] tree, int size) {
        int result = 0;
        left += size; // shift the idx to the leaf
        right += size;
        while (left < right) {
            if (left % 2 == 1) {
                result += tree[left];
                left++;
            }
            left /= 2;
            if (right % 2 == 1) {
                right--;
                result += tree[right];
            }
            right /= 2;
        }
        return result;
    }


	public List<Integer> countSmaller(int[] nums) {
        int offset = 10000; // offset negative to non-negative
        int size = 2 * 10000 + 1; // total possible values in nums
        int[] buskets = new int[size * 2];
        List<Integer> result = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {// from the last elem to the 1st one
            int smaller = query(0, nums[i] + offset, buskets, size);
            result.add(smaller);
            update(nums[i] + offset, 1, buskets, size);
        }
        Collections.reverse(result);
        return result;
    }


}
