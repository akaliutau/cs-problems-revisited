package com.problems.combinatorics;

/**
 * We distribute some number of candies, to a row of n = num_people people in
 * the following way:
 * 
 * We then give 1 candy to the first person, 2 candies to the second person, and
 * so on until we give n candies to the last person.
 * 
 * Then, we go back to the start of the row, giving n + 1 candies to the first
 * person, n + 2 candies to the second person, and so on until we give 2 * n
 * candies to the last person.
 * 
 * This process repeats (with us giving one more candy each time, and moving to
 * the start of the row after we reach the end) until we run out of candies. The
 * last person will receive all of our remaining candies (not necessarily one
 * more than the previous gift).
 * 
 * Return an array (of length num_people and sum candies) that represents the
 * final distribution of candies.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: candies = 7, num_people = 4 Output: [1,2,3,1] Explanation: On the
 * first turn, ans[0] += 1, and the array is [1,0,0,0]. On the second turn,
 * ans[1] += 2, and the array is [1,2,0,0]. On the third turn, ans[2] += 3, and
 * the array is [1,2,3,0]. On the fourth turn, ans[3] += 1 (because there is
 * only one candy left), and the final array is [1,2,3,1].
 * 
 * [1,2,3,1]
 *         \__potentially could get 4 candies, but only 1 left
 *         
 * 
 * [1,2,3,1]
 * 
 * IDEA:
 * 1) detect the last person situation:
 * total = 1 + 2 + ... + n = n * (n + 1) / 2 candies needed, => 
 * 
 * 2) count the cycle on the basis of detected n
 * 3) calculate array's values
 * [1,   2,  3,  4] = 0*4 + [1,   2,  3,  4]
 * [5,   6,  7,  8] = 1*4 + [1,   2,  3,  4]
 * [9,  10, 11, 12] = 2*4 + [1,   2,  3,  4]
 * [13, 14, 15    ]
 * 
 * cycles = 2
 * amount = 11 - cycles * 4
 * 
 * 
 * 1st elem = 1 + 5 + 9 = 1 + (1 + 1*4) + (1 + 2*4) = cycles * (i + 1) + numPeople * sum (0 + 1 + 2)
 *
 */
public class Solution1103 {
	
 	static int sum(int n) {
		if (n <= 0) {
			return 0;
		}
		return n * (n + 1) / 2;
	}

	public int[] distributeCandies(int candies, int numPeople) {
		int steps = 1;// the last valid step which is also the last amount of candies
		long total = 0;// total amount of candies
		int[] res = new int[numPeople];
		
		while (total + steps < candies) {
			total += steps ++;
		}
        steps --;
		// steps = 3 here
		// total = 6
		
		int cycles = steps / numPeople;

        for (int i = 0; i < numPeople; i++) {
			res[i] = cycles * (i + 1) + numPeople *  sum(cycles - 1);
            candies -= res[i];

		}
        
        // filling last line
		int amount = cycles * numPeople + 1;
		int i = 0;
		while (candies > 0) {
			if (candies - amount >= 0) {
				res[i++] += amount;
				candies -= amount;
			}else {
				res[i++] += candies;
				break;
			}
			amount ++;
		}
		return res;
	}

}
