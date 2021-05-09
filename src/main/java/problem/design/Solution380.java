package problem.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 
 * Design O(1) random generator
 * 
 * IDEA:
 * 1) use map (number) => (its position in list) for fast access
 * 2) when removing element, exchange it first with the last in the list
 * 
 */
public class Solution380 {

	static class RandomizedSet {
		Map<Integer, Integer> dict;// map (number) => (its position in list)
		List<Integer> list;// plain array containing the random numbers
		Random rand = new Random();

		/** Initialize your data structure here. */
		public RandomizedSet() {
			dict = new HashMap<>();
			list = new ArrayList<>();
		}

		/**
		 * Inserts a value to the set. Returns true if the set did not already contain
		 * the specified element.
		 */
		public boolean insert(int val) {
			if (dict.containsKey(val)) {
				return false;
			}

			dict.put(val, list.size());// O(1) for integers - buckets for integer is intNumb % d
			list.add(list.size(), val);// O(1)
			return true;
		}

		/**
		 * Removes a value from the set. Returns true if the set contained the specified
		 * element.
		 */
		public boolean remove(int val) {
			if (!dict.containsKey(val))
				return false;

			// move the last element to the place idx of the element to delete - in order to preserve the length
			int lastElement = list.get(list.size() - 1);
			int idx = dict.get(val);
			list.set(idx, lastElement);// O(1)
			dict.put(lastElement, idx);// O(1)
			
			// delete the last element
			list.remove(list.size() - 1);
			dict.remove(val);
			return true;
		}

		/** Get a random element from the set. */
		public int getRandom() {
			return list.get(rand.nextInt(list.size()));
		}
	}



}
