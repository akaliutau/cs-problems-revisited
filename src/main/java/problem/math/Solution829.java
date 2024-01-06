package problem.math;

/**
 * 
 * Given a positive integer n, how many ways can we write it as a sum of
 * consecutive positive integers?
 * 
 * Example 1:
 * 
 * Input: n = 5 Output: 2 Explanation: 5 = 5 = 2 + 3 
 * 
 * IDEA: 
 * Mathematical: Decrease N Gradually, O( sqrt{N} ) time
 * 
 * At each step, we check if N can be composed by the sum of k consecutive numbers, 
 * i.e. N = (x + 1) + (x + 2) + ... + (x + k) = xk + (1 + 2 + ... + k) = xk + k * (k + 1) / 2
 * 
 * N = 15, N = (x + 1) + (x + 2) + ... + (x + k)
 * 
 * 1. k = 1. Check 15 = (x+1)
 *  N = N - 1 = 14
 *  x = 14 % 1 = 0, => add (14 + 1)
 * 
 * 2. k = 2. Check 15 = (x+1) + (x+2)
 *  N = N - 1 - 2 = 12
 *  x = 12 % 2 = 0, => add (6 + 1)
 * 
 * 3. k = 3. Check 15 = (x+1) + (x+2) + (x+3)
 *  N = N - 1 - 2 - 3 = 9
 *  x = 9 % 3 = 0, => add (3 + 1)
 * 
 * 4. k = 4. Check 15 = (x+1) + (x+2) + (x+3) + (x+4)
 *  N = N - 1 - 2 - 3 - 4 = 5
 *  x = 5 % 4 != 0, => no solution
 * 
 * 
 * 
 */
public class Solution829 {

	public int consecutiveNumbersSum(int N) {
		int count = 0;
		int limit = (int) (Math.sqrt(2 * N + 0.25) - 0.5);
		for (int k = 1; k <= limit; ++k) {
			N -= k;
			if (N % k == 0) {
				count++;
			}
		}
		return count;
	}

}
