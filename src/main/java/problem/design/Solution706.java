package problem.design;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Design a HashMap without using any built-in hash table libraries.
 * 
 * To be specific, your design should include these functions:
 * 
 * put(key, value) : Insert a (key, value) pair into the HashMap. If the value
 * already exists in the HashMap, update the value. get(key): Returns the value
 * to which the specified key is mapped, or -1 if this map contains no mapping
 * for the key. remove(key) : Remove the mapping for the value key if this map
 * contains the mapping for the key.
 * 
 * IDEA:
 * 1. use bucket to hold the chained pairs 
 * 
 */
public class Solution706 {

	class Pair<U, V> {
		public U key;
		public V value;

		public Pair(U key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	class Bucket {
		private List<Pair<Integer, Integer>> bucket;

		public Bucket() {
			this.bucket = new LinkedList<Pair<Integer, Integer>>();
		}

		public Integer get(Integer key) {
			for (Pair<Integer, Integer> pair : this.bucket) {
				if (pair.key.equals(key))
					return pair.value;
			}
			return -1;
		}

		public void update(Integer key, Integer value) {
			boolean found = false;
			for (Pair<Integer, Integer> pair : this.bucket) {
				if (pair.key.equals(key)) {
					pair.value = value;
					found = true;
				}
			}
			if (!found)
				this.bucket.add(new Pair<Integer, Integer>(key, value));
		}

		public void remove(Integer key) {
			for (Pair<Integer, Integer> pair : this.bucket) {
				if (pair.key.equals(key)) {
					this.bucket.remove(pair);
					break;
				}
			}
		}
	}

	public class MyHashMap {
		private int keySpace;
		private List<Bucket> table;

		public MyHashMap() {
			this.keySpace = 1000;
			this.table = new ArrayList<Bucket>();
			for (int i = 0; i < this.keySpace; ++i) {
				this.table.add(new Bucket());
			}
		}

		/** value will always be non-negative. */
		public void put(int key, int value) {
			int hash = key % this.keySpace;
			this.table.get(hash).update(key, value);
		}

		/**
		 * Returns the value to which the specified key is mapped, or -1 if this map
		 * contains no mapping for the key
		 */
		public int get(int key) {
			int hash = key % this.keySpace;
			return this.table.get(hash).get(key);
		}

		/**
		 * Removes the mapping of the specified value key if this map contains a mapping
		 * for the key
		 */
		public void remove(int key) {
			int hash = key % this.keySpace;
			this.table.get(hash).remove(key);
		}
	}


}
