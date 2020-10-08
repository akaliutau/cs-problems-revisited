package com.problems.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * DP
 * 
 * 
 * map which contains key:valuekey:value pairs such that keykey refers to the
 * position at which a stone is present and valuevalue is a set containing the
 * jumpsizejumpsize which can lead to the current stone position
 * 
 */
public class Solution403 {
	
	public boolean canCross(int[] stones) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int len = stones.length;
        for (int i = 0; i < len; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        map.get(0).add(0);
        for (int i = 0; i < len; i++) {
            for (int k : map.get(stones[i])) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }
        return map.get(stones[len - 1]).size() > 0;
    }

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
