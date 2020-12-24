package com.problems.bfs;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a positive integer n, you can apply one of the following operations: If
 * n is even, replace n with n / 2. If n is odd, replace n with either n + 1 or
 * n - 1. Return the minimum number of operations needed for n to become 1.
 * 
 * Example 1: Input: n = 8 Output: 3 Explanation: 8 -> 4 -> 2 -> 1
 * 
 * IDEA:
 * Use BFS on all possibilities for curr Number
 * Use memoization for cache for already calculated numbers
 * 
 * time: O(3n) ~ O(n), because ((curr+1)+1) became divisible by 2
 * 
 */
public class Solution397 {

    static int traverse(long curr, Map<Long, Integer> memo) {
        if (curr == 1) {
            return 0;
        }
        
        if (memo.containsKey(curr)) {// use cache
            return memo.get(curr);
        }
        
        if (curr % 2 == 0) {
            memo.put(curr, 1 + traverse(curr / 2, memo));
        } else {// Crucial moment - choose the min from 2 possibilities
            memo.put(curr, 1 + Math.min(traverse(curr + 1, memo), traverse(curr - 1, memo)));
        }
        
        return memo.get(curr);
    }

    public int integerReplacement(int n) {
        return traverse(n, new HashMap<>());
    }

}
