package problem.bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus
 * repeats forever. For example if routes[0] = [1, 5, 7], this means that the
 * first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->...
 * forever.
 * 
 * We start at bus stop S (initially not on a bus), and we want to go to bus
 * stop T. Traveling by buses only, what is the least number of buses we must
 * take to reach our destination? Return -1 if it is not possible.
 * 
 * Example: Input: routes = [[1, 2, 7], [3, 6, 7]] S = 1 T = 6 Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then
 * take the second bus to the bus stop 6.
 *
 * IDEA:
 * 
 * Simulate the process of hops as a propagation between buses:
 * 
 * for src 1 -> 6
 * #0: 1 -> 2 -> 7
 * #1: 3 -> 6 -> 7
 * 
 * 1. create a stopsMap:
 * 
 * 1 => 0
 * 2 => 0
 * 7 => [0,1]
 * 3 => 1
 * 6 => 1
 * 
 * 2. find all the buses we can start from
 * 3. start BFS using usedBuses to avoid using the same bus twice
 * 
 * 
 * 
 * 
 */
public class Solution815 {
	
    class Bus {
		int bus;
		int hopCount;

		Bus(int bus, int count) {
			this.bus = bus;
			this.hopCount = count;
		}
	}
	
	public int numBusesToDestination(int[][] routes, int source, int target) {
		Map<Integer, Set<Integer>> stopsMap = new HashMap<>();// stopsMap stop => all buses which can be used to reach this stop
		if (source == target) {
			return 0;
		}
		for (int bus = 0; bus < routes.length; bus++) {
			for (int stop : routes[bus]) {
				stopsMap.computeIfAbsent(stop, s -> new HashSet<>()).add(bus);
			}
		}

		Set<Integer> usedBuses = new HashSet<>();
		Queue<Bus> q = new LinkedList<>();
		for (int bus : stopsMap.get(source)) {// find all the buses we can start from
			q.add(new Bus(bus, 1));
		}
		int hops = Integer.MAX_VALUE;  
		while (!q.isEmpty()) {
			int n = q.size();
			for (int i = 0; i < n; i++) {
				Bus currentBus = q.poll();// take the bus
				if (!usedBuses.contains(currentBus.bus)) {
					for (int stop : routes[currentBus.bus]) {// 
						if (stop == target && currentBus.hopCount < hops) {
							hops = currentBus.hopCount;
                            break;
						}
						// find all buses we can switch to at this point
						for (int bus : stopsMap.get(stop)) {
							q.add(new Bus(bus, currentBus.hopCount + 1));
						}
					}
					usedBuses.add(currentBus.bus);
				}
			}
            if (hops != Integer.MAX_VALUE){
                return hops;
            }
		}
		
		return hops == Integer.MAX_VALUE ? -1 : hops;
	}

}
