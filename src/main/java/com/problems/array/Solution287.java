package com.problems.array;

/**
 * Given an array of integers nums containing n + 1 integers where each integer
 * is in the range [1, n] inclusive. There is only one duplicate number in nums,
 * return this duplicate number. Follow-ups: How can we prove that at least one
 * duplicate number must exist in nums? Can you solve the problem without
 * modifying the array nums? Can you solve the problem using only constant, O(1)
 * extra space? Can you solve the problem with runtime complexity less than
 * O(n2)? 
 * 
 * Example 1: Input: nums = [1,3,4,2,2] Output: 2 
 * 
 * 
 * Example 2: Input: nums = [3,1,3,4,2] Output: 3
 * 
 * IDEA: nums is a group
 * lets nums is an array of ref, double means a cycle
 */
public class Solution287 {

    public int findDuplicate(int[] nums) {
        int firstPtr = nums[0];
        int secondPtr = nums[0];
        firstPtr = nums[firstPtr];
        secondPtr = nums[nums[secondPtr]];
        while (firstPtr != secondPtr) {
            firstPtr = nums[firstPtr];
            secondPtr = nums[nums[secondPtr]];
        }

        // this part in needed  because the intersection point is not the cycle entrance in the general case 
        // [1,3,4,2,2]
        // [2,5,9,6,4,3,8,9,7,1]
        int ptr1 = nums[0];
        int ptr2 = firstPtr;// intersection
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;

    }

}
