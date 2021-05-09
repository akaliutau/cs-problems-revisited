package problem.array;

/**
 * Given a circular array C of integers represented by a, find the maximum
 * possible sum of a non-empty subarray of C. 
 * 
 * Here, a circular array means the
 * end of the array connects to the beginning of the array. (Formally, C[i] =
 * a[i] when 0 <= i < a.length, and C[i+a.length] = C[i] when i >= 0.) also, a
 * subarray may only include each element of the fixed buffer a at most once.
 * (Formally, for a subarray C[i], C[i+1], ..., C[j], there does not exist i <=
 * k1, k2 <= j with k1 % a.length = k2 % a.length.) 
 * 
 * Example 1: Input:
 * [1,-2,3,-2] Output: 3 Explanation: Subarray [3] has maximum sum 3
 * 
 * 
 */
public class Solution918 {
    
    long kadane(int[] a, int i, int j, int sign) {
        // The maximum non-empty subarray for array
        // [sign * a[i], sign * a[i+1], ..., sign * a[j]]
        long ans = Integer.MIN_VALUE;
        long cur = Integer.MIN_VALUE;
        for (int k = i; k <= j; ++k) {
            cur = sign * a[k] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public int maxSubarraySumCircular(int[] a) {
        int s = 0; // S = sum(a)
        for (int x : a)
            s += x;

        long ans1 = kadane(a, 0, a.length - 1, 1);
        long ans2 = s + kadane(a, 1, a.length - 1, -1);
        long ans3 = s + kadane(a, 0, a.length - 2, -1);
        return (int)Math.max(ans1, Math.max(ans2, ans3));
    }

}
