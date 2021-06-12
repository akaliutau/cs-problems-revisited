package problem.combinatorics;

/**
 * You are given a list of songs where the ith song has a duration of time[i]
 * seconds.
 * 
 * Return the number of pairs of songs for which their total duration in seconds
 * is divisible by 60. Formally, we want the number of indices i, j such that i
 * < j with (time[i] + time[j]) % 60 == 0.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: time = [30,20,150,100,40] Output: 3 
 * 
 * Explanation: Three pairs have a
 * total duration divisible by 60: (time[0] = 30, time[2] = 150): total duration
 * 180 (time[1] = 20, time[3] = 100): total duration 120 (time[1] = 20, time[4]
 * = 40): total duration 60
 * 
 * IDEA:
 * 
 * use modular mathematics:
 * collect in map[60] the remainders of all times seen so far
 * all numbers from [0,60] will be mapped to [0,60], all extra will be added to the 2nd, 3rd, etc, layer
 * f.e. 150 % 60 -> 30
 * 
 * 
 * [30,20,150,100,40]
 *  |   |  |   |   |
 *  |   |  |   |   
 *  |   |  |      
 *  |   |          
 *  |
 *  add[30 % 60 = 30]
 *                 
 */
public class Solution1010 {

	public int numPairsDivisibleBy60(int[] time) {
		int rem[] = new int[60]; // Contains the information how many songs can be complimentary to some number % by 60
		int count = 0;
		for (int t : time) {
			int remainder = t % 60;// == mapping [song duration] => [0,59]
			if (remainder == 0) { // can form pairs with each of previous songs of length 60 (because 60 maps to 0)
				count += rem[0];
			} else { 
				count += rem[60 - remainder]; // check complimentary, f.e. if remainder = 40, we can form valid pair with all songs which have remainder = 20
			}
			rem[t % 60]++; // remember to update the remainders
		}
		return count;
	}

}
