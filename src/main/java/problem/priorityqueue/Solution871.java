package problem.priorityqueue;

import java.util.PriorityQueue;

/**
 * A car travels from a starting position to a destination which is target miles
 * east of the starting position.
 * 
 * Along the way, there are gas stations. Each station[i] represents a gas
 * station that is station[i][0] miles east of the starting position, and has
 * station[i][1] liters of gas.
 * 
 * The car starts with an infinite tank of gas, which initially has startFuel
 * liters of fuel in it. It uses 1 liter of gas per 1 mile that it drives.
 * 
 * When the car reaches a gas station, it may stop and refuel, transferring all
 * the gas from the station into the car.
 * 
 * What is the least number of refueling stops the car must make in order to
 * reach its destination? If it cannot reach the destination, return -1.
 * 
 * Note that if the car reaches a gas station with 0 fuel left, the car can
 * still refuel there. If the car reaches the destination with 0 fuel left, it
 * is still considered to have arrived.
 * 
 * IDEA:
 * 
 * When we run out of fuel before reaching the next station, we'll retroactively fuel up: 
 * greedily choosing the largest gas stations first.
 *
 * This is guaranteed to succeed because we drive the largest distance possible before each refueling stop, 
 * and therefore have the largest choice of gas stations to (retroactively) stop at.
 * 
 * at each station decide what might be done on the previous one: to take it OR continue to drive on the left gas
 * 
 * make choice: take or continue			
 * |           |         |
 * add 5       add 4    add2
 * 
 */
public class Solution871 {

	public int minRefuelStops(int target, int tank, int[][] stations) {
		// pq is a maxheap of gas station capacities
		PriorityQueue<Integer> pq = new PriorityQueue<>((o, p) -> p - o);
		int ans = 0, prevLocation = 0;
		
		for (int[] station : stations) {
			int location = station[0];
			int addFuel = station[1];
			int transitionCost = location - prevLocation;
			tank -= transitionCost;
			
			while (!pq.isEmpty() && tank < 0) {// decide what might be done on the previous one
				tank += pq.poll();// choose only the biggest vol first, because we want to minimize # of tanks => choose the biggest
				ans++;
			}

			if (tank < 0)
				return -1;
			pq.add(addFuel);// remember for potential using in the future
			prevLocation = location;// chain location to calculate delta on each step
		}
		
		// the final segment of the path - process in the same way
		tank -= target - prevLocation;
		while (!pq.isEmpty() && tank < 0) {
			tank += pq.poll();
			ans++;
		}

		return tank < 0 ? -1 : ans;
	}

}
