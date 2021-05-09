package problem.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * A sequence X_1, X_2, ..., X_n is fibonacci-like if: n >= 3 X_i + X_{i+1} =
 * X_{i+2} for all i + 2 <= n Given a strictly increasing array A of positive
 * integers forming a sequence, find the length of the dp fibonacci-like
 * subsequence of A. If one does not exist, return 0. (Recall that a subsequence
 * is derived from another sequence A by deleting any number of elements
 * (including none) from A, without changing the order of the remaining
 * elements. For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)
 * 
 * Example 1: Input: [1,2,3,4,5,6,7,8] Output: 5 Explanation: The dp
 * subsequence that is fibonacci-like: [1,2,3,5,8]
 * 
 * IDEA: use the pair transitioning as a key for dp
 */
public class Solution873 {

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> index = new HashMap<>();// map number => its position
        for (int i = 0; i < n; ++i) {
            index.put(arr[i], i);
        }

        Map<Integer, Integer> dp = new HashMap<>();//dp: map (i,j) => length of seq ending at (i -> j)
        int length = 0;

        for (int k = 0; k < n; ++k)
            for (int j = 0; j < k; ++j) {
                int iNum = arr[k] - arr[j];// calc number i;
                int i = index.getOrDefault(iNum, -1);// calc its index;
                if (i >= 0 && i < j) {// check that (i,j,k) is a correct tuple 
                    // Encoding tuple (i, j) as integer (i * N + j)
                    int cand = dp.getOrDefault(i * n + j, 2) + 1;// length(i -> j)  + length(-> k) == length(i -> j)  + 1
                    dp.put(j * n + k, cand);
                    length = Math.max(length, cand);
                }
            }

        return length >= 3 ? length : 0;
    }

}
