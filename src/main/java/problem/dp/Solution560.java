package problem.dp;

import java.util.HashMap;

/**
 * Given an array of integers and an integer k, you need to find the total
 * number of continuous subarrays whose sum equals to k. 
 * 
 * Example 1: Input:nums =
 * [1,1,1], k = 2 Output: 2 
 * 
 * sum array 
 * 
 * [1,2,3] 
 * 
 * The idea behind this approach is as follows: 
 * 
 * We traverse over the array nums and keep
 * on finding the cumulative sum. Every time we encounter a new sum, we make a
 * new entry in the hashmap corresponding to that sum. If the same sum occurs
 * again, we increment the count corresponding to that sum in the hashmap.
 * 
 * IDEA:
 * 1. complementary sum + cumulative add up:
 * 
 * [1]  <-- have array
 * [2]  <-- having an element 2 we can compose only 1 pair with complementary sum, namely 1-2, => increase the count of subarrays on 1
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
            // just compose all possible arrays with current point, i.e. (j_1,i), (j_2,i), ... (j_n,i) where n = count in map
            if (map.containsKey(partSum)) {
                count += map.get(partSum);
            }
            map.compute(sum, (u,v) -> v == null ? 0 : v + 1);
        }
        return count;
    }

}
