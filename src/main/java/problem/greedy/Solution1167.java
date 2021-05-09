package problem.greedy;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * You have some number of sticks with positive integer lengths. These lengths
 * are given as an array sticks, where sticks[i] is the length of the ith stick.
 * 
 * You can connect any two sticks of lengths x and y into one stick by paying a
 * cost of x + y. You must connect all the sticks until there is only one stick
 * remaining.
 * 
 * Return the minimum cost of connecting all the given sticks into one stick in
 * this way.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: sticks = [2,4,3] Output: 14 
 * 
 * Explanation: You start with sticks =
 * [2,4,3]. 1. Combine sticks 2 and 3 for a cost of 2 + 3 = 5. Now you have
 * sticks = [5,4]. 2. Combine sticks 5 and 4 for a cost of 5 + 4 = 9. Now you
 * have sticks = [9]. There is only one stick left, so you are done. The total
 * cost is 5 + 9 = 14.
 * 
 * 
 */
public class Solution1167 {

	public int connectSticks(int[] sticks) {
		List<Integer> parts = Arrays.stream(sticks).boxed().collect(Collectors.toList());
		Queue<Integer> pq = new PriorityQueue<>(parts);
		int totalCost = 0;
		while (pq.size() > 1) {
			int cost = pq.poll() + pq.poll();
			pq.add(cost);
			totalCost += cost;
		}
		return totalCost;

	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
