package problem.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 
 * Implement the RandomizedCollection class:
 * 
 * RandomizedCollection() Initializes the RandomizedCollection object.
 * 
 * bool insert(int val) Inserts an item val into the multiset if not present.
 * Returns true if the item was not present, false otherwise.
 * 
 * bool remove(int val) Removes an item val from the multiset if present.
 * Returns true if the item was present, false otherwise. Note that if val has
 * multiple occurrences in the multiset, we only remove one of them.
 * 
 * int getRandom() Returns a random element from the current multiset of
 * elements (it's guaranteed that at least one element exists when this method
 * is called). The probability of each element being returned is linearly
 * related to the number of same values the multiset contains.
 * 
 * You must implement the functions of the class such that each function works
 * in average O(1) time complexity.
 * 
 * We will keep a list to store all our elements. In order to make finding the
 * index of elements we want to remove O(1), we will use a HashMap or dictionary
 * to map values to all indices that have those values. To make this work each
 * value will be mapped to a set of indices. The tricky part is properly
 * updating the HashMap as we modify the list.
 * 
 * insert: Append the element to the list and add the index to HashMap[element].
 * 
 * remove: This is the tricky part. We find the index of the element using the
 * HashMap. We use the trick discussed in the intuition to remove the element
 * from the list in O(1). Since the last element in the list gets moved around,
 * we have to update its value in the HashMap. We also have to get rid of the
 * index of the element we removed from the HashMap.
 * 
 * getRandom: Sample a random element from the list.
 * 
 * IDEA:
 * 1. use list to collect the elements of collection, and swap between last and index(of this element) to avoid 
 * list resizing
 * 
 *
 */
public class Solution381 {

	public class RandomizedCollection {
		List<Integer> lst;
		Map<Integer, Set<Integer>> idx;
		Random rand = new Random();

		public RandomizedCollection() {
			lst = new ArrayList<>();
			idx = new HashMap<>();
		}

		/**
		 * Inserts a value to the collection. Returns true if the collection did not
		 * already contain the specified element.
		 */
		public boolean insert(int val) {
			idx.computeIfAbsent(val, v -> new LinkedHashSet<>()).add(lst.size());
			lst.add(val);
			return idx.get(val).size() == 1;
		}

		/**
		 * Removes a value from the collection. Returns true if the collection contained
		 * the specified element.
		 */
		public boolean remove(int val) {
			if (!idx.containsKey(val) || idx.get(val).size() == 0)
				return false;
			int toRemove = idx.get(val).iterator().next();
			idx.get(val).remove(toRemove);
			int last = lst.get(lst.size() - 1);
			lst.set(toRemove, last);
			idx.get(last).add(toRemove);
			idx.get(last).remove(lst.size() - 1);

			lst.remove(lst.size() - 1);
			return true;
		}

		/** Get a random element from the collection. */
		public int getRandom() {
			return lst.get(rand.nextInt(lst.size()));
		}
	}

}
