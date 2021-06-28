package problem.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given an integer array cuts where cuts[i] denotes a position you should
 * perform a cut at.
 * 
 * You should perform the cuts in order, you can change the order of the cuts as
 * you wish.
 * 
 * The cost of one cut is the length of the stick to be cut, the total cost is
 * the sum of costs of all cuts. When you cut a stick, it will be split into two
 * smaller sticks (i.e. the sum of their lengths is the length of the stick
 * before the cut). Please refer to the first example for a better explanation.
 * 
 * Return the minimum total cost of the cuts.
 * 
 * IDEA:
 * 
 * Brute Force with optimization
 * 
 * 1. use recursion with memoization to speed up the process
 * 
 * 0  2   5  7   10
 * |oo|ooo|oo|ooo|
 * 
 * 
 * Memoizing: the result of cutting of stick with left:right (NOTE the position is important)
 */
public class Solution1547 {
	
	int cost(int[] cuts, int left, int right, Map<String,Integer> costMap) {
		if (left + 1 >= right) {
			return 0;
		}
		int len = cuts[right] - cuts[left];
        String key = right + ":" + left;
		if (costMap.containsKey(key)) {
			return costMap.get(key);
		}
		int minCost = Integer.MAX_VALUE;
		for (int i = left + 1; i < right; i++) {
			int evalCostLeft = cost(cuts, left, i, costMap);
			int evalCostRight = cost(cuts, i, right, costMap);
			minCost = Math.min(minCost, evalCostLeft + evalCostRight + len);
		}
		costMap.put(key, minCost);
		return minCost;
	}
	
	public int minCost(int n, int[] cuts) {
		
        Arrays.sort(cuts);
		Map<String,Integer> costMap = new HashMap<>();
		int[] cutsExt = new int[cuts.length + 2];
		cutsExt[0] = 0;
		for (int i = 0; i < cuts.length; i++) {
			cutsExt[i+1] = cuts[i];
		}
		cutsExt[cuts.length + 1] = n;
		return cost(cutsExt, 0, cuts.length + 1, costMap);
    }}
