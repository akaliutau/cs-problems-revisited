package problem.bfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10
 * slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can
 * rotate freely and wrap around: for example we can turn '9' to be '0', or '0'
 * to be '9'. Each move consists of turning one wheel one slot.
 * 
 * The lock initially starts at '0000', a string representing the state of the 4
 * wheels.
 * 
 * You are given a list of deadends dead ends, meaning if the lock displays any
 * of these codes, the wheels of the lock will stop turning and you will be
 * unable to open it.
 * 
 * Given a target representing the value of the wheels that will unlock the
 * lock, return the minimum total number of turns required to open the lock, or
 * -1 if it is impossible.
 * 
 * IDEA:
 * Start from 0000, then check children:
 * sign: -         +
 *          0000
 *       9000   1000
 *    8000 0000  <-- avoid already seen nodes using memoizing 
 *
 *
 * O(10^d), d = length of code
 */
public class Solution752 {

	private static final int[] dirs = new int[] { 1, -1 };
	private static final String INIT = "0000";

	public int openLock(String[] deadends, String target) {
		Set<String> deadendsSet = new HashSet<>(Arrays.asList(deadends));
		if (deadendsSet.contains(INIT)) {
			return -1;
		} else if (target.equals(INIT)) {
			return 0;
		}

		Queue<String> queue = new LinkedList<>();
		Map<String, Integer> stat = new HashMap<>();
		queue.offer(INIT);
		stat.put(INIT, 0);
		while (!queue.isEmpty()) {
			String str = queue.poll();
			for (int i = 0; i < 4; i++) {
				for (int d : dirs) {
					char[] next = str.toCharArray();
					int val = (next[i] - '0' + d + 10) % 10;// note: cyclic behavior
					next[i] = (char) (val + '0');
					String nextStr = new String(next);
					if (nextStr.equals(target)) {
						return stat.get(str) + 1;
					} else if (!deadendsSet.contains(nextStr) && !stat.containsKey(nextStr)) {
						stat.put(nextStr, stat.get(str) + 1);
						queue.offer(nextStr);
					}
				}
			}
		}

		return -1;
	}
}
