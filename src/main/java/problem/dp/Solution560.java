package problem.dp;

import java.util.HashMap;

/**
 * Given an array of integers and an integer k, you need to find the total
 * number of continuous subarrays whose sum equals to k. 
 * Example 1: Input:nums =
 * [1,1,1], k = 2 Output: 2 sum array 
 * 
 * [1,2,3] 
 * 
 * The idea behind this approach is as follows: If
 * the cumulative sum(represented by sum[i] for sum ith index) upto two indices
 * is the same, the sum of the elements lying in between those indices is zero.
 * Extending the same thought further, if the cumulative sum upto two indices,
 * say i and j is at a difference: sum[i]−sum[j]=tgt, the sum of elements lying
 * between indices i and j is tgt 
 * 
 * We traverse over the array numsnums and keep
 * on finding the cumulative sum. Every time we encounter a new sum, we make a
 * new entry in the hashmap corresponding to that sum. If the same sum occurs
 * again, we increment the count corresponding to that sum in the hashmap.
 * 
 */
public class Solution560 {

    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();/// map sum => count of subarrays with such sum
        map.put(0, 1);// edge case - empty array (1)

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int partSum = sum - k;
            // if true, then at some point in the past we already had a sum_j = partSum
            // sum in between = sum - (sum - k) = k
            // just compose all possible arrays with current point, i.e. (j1,i), (j2,i), ... (jn,i) where n = count in map
            if (map.containsKey(partSum)) {
                count += map.get(partSum);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

}
