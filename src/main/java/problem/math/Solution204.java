package problem.math;

/**
 * 
 * Count the number of prime numbers less than a non-negative number, n.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: n = 10 Output: 4 
 * 
 * Explanation: There are 4 prime numbers less than 10,
 * they are 2, 3, 5, 7.
 * 
 * IDEA:
 * 
 */
public class Solution204 {

	public int countPrimes(int n) {
		if (n <= 2) {
			return 0;
		}

		boolean[] numbers = new boolean[n];
		for (int p = 2; p <= (int) Math.sqrt(n); ++p) {
			if (!numbers[p]) {
				for (int j = p * p; j < n; j += p) {
					numbers[j] = true;
				}
			}
		}

		int counter = 0;
		for (int i = 2; i < n; i++) {
			if (!numbers[i]) {
				++counter;
			}
		}

		return counter;
	}

}
