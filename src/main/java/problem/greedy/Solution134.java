package problem.greedy;

/**
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from station i to its next station (i+1). You begin the journey with
 * an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit
 * once in the clockwise direction, otherwise return -1.
 * 
 * Note:
 * 
 * If there exists a solution, it is guaranteed to be unique. Both input arrays
 * are non-empty and have the same length. Each element in the input arrays is a
 * non-negative integer. Example 1:
 * 
 * Input: gas = [1,2,3,4,5] cost = [3,4,5,1,2]
 * 
 * Output: 3
 * 
 * Explanation: 
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4 
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8 
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7 
 * Travel to station 1. Your tank = 7 -
 * 3 + 2 = 6 Travel to station 2. Your tank = 6 - 4 + 3 = 5 
 * Travel to station 3. The cost is 5. 
 * Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * 
 * IDEA:
 * gas:        [1, 2, 3, 4, 5, 0]
 * cost:       [3, 4, 5, 1, 2, 0]
 * 
 * tank_state: [1, 0, 0, 3, 2, 0]
 *                    |  |
 *                    |  pointer
 *                    |
 *                  reset here        
 */
public class Solution134 {

	public int canCompleteCircuit(int[] gas, int[] cost) {
		int start = 0;
		int n = gas.length;
		int fuelBalance = 0;

		long totalFuel = 0l;
		long totalCost = 0l;
		for (int i = 0; i < n; i++) {
			totalFuel += gas[i];
			totalCost += cost[i];
		}
		if (totalFuel < totalCost) {
			return -1;
		}
		
		for (int i = 0; i < n; i++) {

			fuelBalance += gas[i] - cost[i];// state of tank after transition i -> i + 1

			if (fuelBalance < 0) {// cannot reach point (i + 1) from (i), start from i+1
				start = i + 1;
				fuelBalance = 0;
			}

		}
		return start % n;
	}

}
