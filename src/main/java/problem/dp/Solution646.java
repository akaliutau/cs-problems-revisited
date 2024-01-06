package problem.dp;

import java.util.Arrays;

/**
 * 
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and
 * lefti < righti.
 * 
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can
 * be formed in this fashion.
 * 
 * Return the length longest chain which can be formed.
 * 
 * You do not need to use up all the given intervals. You can select pairs in
 * any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: pairs = [[1,2],[2,3],[3,4]] Output: 2 Explanation: The longest chain
 * is [1,2] -> [3,4].
 * 
 * IDEA:
 * the same as LIS
 * 
 * for each chain: 
 *   - consider it as the last segment in chain and find the longest subchain in the history
 *   - if the new length is bigger then previous one, attach the segment 
 * 
 */
public class Solution646 {
	
	 public int findLongestChain(int[][] pairs) {
	        Arrays.sort(pairs, (o,p) -> o[0] - p[0]);
	        int n = pairs.length;
	        int[] len = new int[n]; // the max length of chain ending at i
	        Arrays.fill(len, 1);
	        len[0] = 1;
	        for (int i = 1; i < n; i++){
	            int[] lastPair = pairs[i];
	            for (int j = 0; j < i; j++){
	                int[] curPair = pairs[j];
	                if (curPair[1] < lastPair[0] && len[j] + 1 > len[i]){
	                    len[i] =  len[j] + 1;
	                }
	            }
	        }
	        int maxLen = 0;
	        for (int i = 0; i < n; i++){
	            maxLen = Math.max(maxLen, len[i]);
	        }
	        return maxLen;
	        
	    }

}
