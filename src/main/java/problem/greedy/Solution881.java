package problem.greedy;

import java.util.Arrays;

/**
 * The i-th person has weight people[i], and each boat can carry a maximum
 * weight of limit.
 * 
 * Each boat carries at most 2 people at the same time, provided the sum of the
 * weight of those people is at most limit.
 * 
 * Return the minimum number of boats to carry every given person. (It is
 * guaranteed each person can be carried by a boat.)
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: people = [1,2], limit = 3 Output: 1 Explanation: 1 boat (1, 2)
 * 
 * IDEA:
 * 
 * Let people[i] to the currently lightest person, and people[j] to the
 * heaviest.
 * 
 * Then, as described above, if the heaviest person can share a boat with the
 * lightest person (if people[j] + people[i] <= limit) then do so; otherwise,
 * the heaviest person sits in their own boat.
 * 
 */
public class Solution881 {

	public int numRescueBoats(int[] people, int limit) {
		Arrays.sort(people);
		int i = 0, j = people.length - 1;
		int ans = 0;

		while (i <= j) {
			ans++;
			if (people[i] + people[j] <= limit)
				i++;
			j--;
		}

		return ans;
	}

}
