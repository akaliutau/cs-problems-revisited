package problem.design;

import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 * 
 * Example:
 * 
 * // Init an array with set 1, 2, and 3. int[] nums = {1,2,3}; Solution
 * solution = new Solution(nums);
 * 
 * // Shuffle the array [1,2,3] and return its result. Any permutation of
 * [1,2,3] must equally likely to be returned. solution.shuffle();
 * 
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * 
 * // Returns the random shuffling of array [1,2,3]. solution.shuffle();
 * 
 * IDEA:
 * gen a random number in a range
 * 
 */
public class Solution384 {

	class Solution {
		private int[] array;
		private int[] original;

		Random rand = new Random();

		// returns the random number in [min, max)
		private int randInRange(int min, int max) {
			return rand.nextInt(max - min) + min;
		}

		private void swap(int i, int j) {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}

		public Solution(int[] nums) {
			array = nums;
			original = nums.clone();
		}

		public int[] reset() {
			array = original;
			original = original.clone();
			return original;
		}

		public int[] shuffle() {
			for (int i = 0; i < array.length; i++) {
				swap(i, randInRange(i, array.length));
			}
			return array;
		}
	}

}
