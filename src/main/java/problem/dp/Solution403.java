package problem.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. 
 * The frog can jump on a stone, but it must not jump into the water.
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. 
 * Note that the frog can only jump in the forward direction.
 * 
 * 1
 * 
 * 0    1     3    5 
 * 
 * 0    1     1
 * 
 */
public class Solution403 {

	public boolean canCross(int[] stones) {
		int len = stones.length;
		// key refers to the position at which a stone is present and
		// value is a set containing the jumpsize which can lead to the current stone
		// position
		Map<Integer, Set<Integer>> map = new HashMap<>();

		for (int i = 0; i < len; i++) {
			map.put(stones[i], new HashSet<Integer>());// stone positions
		}
		map.get(0).add(0);
		for (int i = 0; i < len; i++) {

			for (int k : map.get(stones[i])) {// the size of possible jumps
				for (int jumpsize = k - 1; jumpsize <= k + 1; jumpsize ++) {
					// can jump only forward and check for stone at landing position
					int landingPos = stones[i] + jumpsize;
					if (jumpsize > 0 && map.containsKey(landingPos)) {
						map.get(landingPos).add(jumpsize);// collect jump sizes for future use
					}
				}
			}

		}
		return map.get(stones[len - 1]).size() > 0;
	}

	
}
