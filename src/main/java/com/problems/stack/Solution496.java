package com.problems.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * Stack
 * 
 * O(n + m)
 */
public class Solution496 {

    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();// next bigger elem to the key
        int n = findNums.length;
        
        // rearrange numbers from main set:
        // build a map for chain: nums[i] => next bigger elem
        for (int i = 0; i < nums.length; i++) {
            int latestBiggest = nums[i];
            while (!stack.empty() && latestBiggest > stack.peek()) {// find all elems on stack which are smaller than current one
                map.put(stack.pop(), latestBiggest);
            }
            stack.push(latestBiggest);// and push this bigger elem to to stack
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
