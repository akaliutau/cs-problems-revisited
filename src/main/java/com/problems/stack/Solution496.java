package com.problems.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * Stack
 */
public class Solution496 {

    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = findNums.length;
        
        // rearrange numbers from main set:
        // build a map for chain: nums[i] => next bigger elem
        for (int i = 0; i < nums.length; i++) {
            while (!stack.empty() && nums[i] > stack.peek()) {
                map.put(stack.pop(), nums[i]);
            }
            stack.push(nums[i]);
        }
        // all the rest do not have a bigger one
        while (!stack.empty()) {
            map.put(stack.pop(), -1);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = map.get(findNums[i]);// always exists because it is a subset
        }
        return res;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
