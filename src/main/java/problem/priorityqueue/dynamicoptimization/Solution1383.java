package problem.priorityqueue.dynamicoptimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integers n and k and two integer arrays speed and
 * efficiency both of length n. There are n engineers numbered from 1 to n.
 * speed[i] and efficiency[i] represent the speed and efficiency of the ith
 * engineer respectively.
 * 
 * Choose at most k different engineers out of the n engineers to form a team
 * with the maximum performance.
 * 
 * The performance of a team is the sum of their engineers' speeds multiplied by
 * the minimum efficiency among their engineers.
 * 
 * Return the maximum performance of this team. Since the answer can be a huge
 * number, return it modulo 109 + 7.
 * 
 * Example 1:
 * 
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60 Explanation: We have the maximum performance of the team by
 * selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with
 * speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * 
 * IDEA:
 * 1. zip arrays into tuples
 * 2. sort them by efficiency
 * 3. build team using the most efficient member as a seed, and continue build up the optimal set using speed:
 * 
 * speed       [2, 10, 3, 1, 5, 8]
 * efficiency  [5,  4, 3, 9, 7, 2]
 * 
 * 
 * after sorting:
 * 
 * speed       [1, 5, 2,10, 3, 8]
 * efficiency  [9, 7, 5, 4, 3, 2]
 * 
 * start from (1,9)
 * add to the team (5,7) <-- perf = 6*7 = 42,   team: (1,9),  (5,7)
 * 
 * replace (1,9) -> (2,5) <-- perf = 7*5 = 35,  team: (2,5),  (5,7)
 * 
 * replace (1,9) -> (2,5) <-- perf = 4*15 = 60, team: (10,4), (5,7)  // this will be the optimal solution
 * 
 * an so on
 * 
 * What this is working:
 * The aim is to form a set of max nodes, tracking down the efficiency
 * Sorting out the efficiency allows to apply DP technique: find the most efficient set 
 * for current state of efficiency
 * 
 * 
 */
public class Solution1383 {
	
	static int MOD = 1_000_000_007;

	public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {

		List<int[]> candidates = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			candidates.add(new int[] { efficiency[i], speed[i] });
		}
		// sort the members by their efficiencies
		Collections.sort(candidates, (o, p) -> p[0] - o[0]);

		// create a heap to keep the top (k-1) speeds
		PriorityQueue<Integer> speedHeap = new PriorityQueue<>((o, p) -> o - p);

		long speedSum = 0, perf = 0;
		for (int[] p : candidates) {
			int currEfficiency = p[0];// will be the part of the product because candidates is a sorted array
			int currSpeed = p[1];
			
			// maintain a heap for the fastest (k-1) speeds
			if (speedHeap.size() > k - 1) {
				int worstSpeed = speedHeap.poll();
				speedSum -= worstSpeed;// remove the worst member
			}
			// calculate the maximum performance with
			// the current member as the least efficient one in the team
			speedSum += currSpeed;
			
			speedHeap.add(currSpeed);

			perf = Math.max(perf, speedSum * currEfficiency);
		}
		
		return (int) (perf % MOD);
	}

}
