package problem.backtracking;

import java.util.HashMap;

/**
 * You are given nums, an array of positive integers of size 2 * n. You must
 * perform n operations on this array.
 * 
 * In the ith operation (1-indexed), you will:
 * 
 * Choose two elements, x and y. Receive a score of i * gcd(x, y). Remove x and
 * y from nums. Return the maximum score you can receive after performing n
 * operations.
 * 
 * The function gcd(x, y) is the greatest common divisor of x and y.
 * 
 * Example 1:
 * 
 * Input: nums = [1,2] Output: 1
 * 
 * Explanation: The optimal choice of operations is: (1 * gcd(1, 2)) = 1 Example
 * 2:
 * 
 * Input: nums = [3,4,6,8] Output: 11
 * 
 * Explanation: The optimal choice of operations is: (1 * gcd(3, 6)) + (2 *
 * gcd(4, 8)) = 3 + 8 = 11
 * 
 * IDEA:
 * 
 * This is pure backtracking solution (i.e. Brute Force with filter). It's possible only because n < 32
 * 
 * A. try to build an optimal sequence using set for already seen positions and chaining the available ones from free slots
 * B. use bit masking for state vector (one can use boolean array instead)
 * 
 * NOTE: Euclide algorithm:
 * 
 * gcd(12, 4):
 * 
 * 12 % 4 == 0 => gcd=4
 * 
 * 12 % 8 != 0 => gcd(12, 8) = gcd(8, 4) = 4
 * 
 * O(n^2 * n) = O(n^3)
 *
 */
public class Solution1799 {
   	int res;
	int n;
	HashMap<Long, Integer> memo;
	
	int gcd(int a, int b) {

		return (a % b) == 0 ? b : gcd(b, a % b);
	}

	long key(int i, int set) {
		return 1000000 * i + set;
	}
	
	int track(int[] nums, int i, int set) {
		if (i > n) {
			return 0;
		}

		long key = key(i, set);
		if (memo.containsKey(key)) {
			return memo.get(key);
		}

		int n = nums.length;
		int res = 0;
		
		for (int j = 0; j < n; j++) {
            int jbit = 1 << j;
			if ((set & jbit) == 0) {        // position j is not taken yet
				for (int k = j + 1; k < n; k++) {
                    int kbit = 1 << k;
					if ((set & kbit) == 0) {// position k is not taken yet
						set |= jbit;
						set |= kbit;
						int val = i * (gcd(nums[j], nums[k])) + track(nums, i + 1, set);// possible at most n levels of recursion
						res = Math.max(res, val);
						set ^= jbit;
						set ^= kbit;
					}
				}
			}
		}

		memo.put(key, res);
		return res;
	}


	public int maxScore(int[] nums) {
		res = 0;
		n = nums.length / 2;
		memo = new HashMap<>();
		return track(nums, 1, 0);
	}

}
