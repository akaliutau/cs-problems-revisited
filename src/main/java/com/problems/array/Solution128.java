package com.problems.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence. Your algorithm should run in O(n) complexity.
 * 
 * Example: Input: [100, 4, 200, 1, 3, 2] Output: 4 Explanation: The longest
 * consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4
 * 
 * IDEA: find the beginning of some sequence
 * as a result the total time complexity will be O(n) - if contains operation is O(1)
 */
public class Solution128 {

    public int longestConsecutive(int[] nums) {

        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            numsSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numsSet) {
        	// check that num is a beginning of some streak
            if (!numsSet.contains(num - 1)) {// this block is executed if and only if num is the FIRST element (i.e. [num-1] is absent)
                int currentNum = num;
                int curSubseq = 1;// num is an elem

                while (numsSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    curSubseq += 1;
                }
                // update longest length
                longestStreak = Math.max(longestStreak, curSubseq);
            }
        }

        return longestStreak;
    }

}
