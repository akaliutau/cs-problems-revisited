package problem.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums, return the number of all the arithmetic subsequences of nums.
 *
 * A sequence of numbers is called arithmetic if it consists of at least three elements
 * and if the difference between any two consecutive elements is the same.
 *
 * For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
 * For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
 * A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
 *
 * For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
 *
 * IDEA:
 * 1) Use the array of maps (Entry) to keep the record of number of paths with jump = diff which start at this point
 * 2) Sum all up
 *
 */
public class Solution446 {

    static class Entry {
        Map<Integer, Integer> map = new HashMap<>();
    }

    public int numberOfArithmeticSlices(int[] arr) {
        int n = arr.length;
        long ans = 0;
        Entry[] count = new Entry[n];
        for (int i = 0; i < n; i++) {
            count[i] = new Entry();
            for (int j = 0; j < i; j++) {
                long delta = (long)arr[i] - (long)arr[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int)delta;
                int sum = count[j].map.getOrDefault(diff, 0);
                int orig = count[i].map.getOrDefault(diff, 0); // Note: 0, because of new entry always
                count[i].map.put(diff, orig + sum + 1); // added to old path the cur element
                ans += sum;
            }
        }
        return (int)ans;
    }
}
