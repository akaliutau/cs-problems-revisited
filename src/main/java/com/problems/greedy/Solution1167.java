package com.problems.greedy;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Greedy
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
