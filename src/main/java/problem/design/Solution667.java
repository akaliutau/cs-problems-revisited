package problem.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement the MapSum class:
 * 
 * MapSum() Initializes the MapSum object. void insert(String key, int val)
 * Inserts the key-val pair into the map. If the key already existed, the
 * original key-value pair will be overridden to the new one. int sum(string
 * prefix) Returns the sum of all the pairs' value whose key starts with the
 * prefix.
 * 
 * 
 * Example 1:
 * 
 * Input    ["MapSum",    "insert",   "sum",   "insert",   "sum"] 
 *          [  [],     ["apple", 3],  ["ap"], ["app", 2], ["ap"]] 
 * Output   [null,      null,          3,       null,        5  ]
 *
 * IDEA:
 * 
 * Use dynamic trie and update all intermediate nodes during traversal * 
 */
public class Solution667 {
	class MapSum {

		class Node {
			Map<Character, Node> children = new HashMap<>();
			int val = 0;
		}

		private Node root = new Node();
		private Map<String, Integer> have = new HashMap<>();

		public MapSum() {
		}

		public void insert(String key, int val) {
			int delta = val - have.getOrDefault(key, 0);
			have.put(key, val);
			Node cur = root;
			cur.val += delta;
			for (char c : key.toCharArray()) {
				cur.children.putIfAbsent(c, new Node());
				cur = cur.children.get(c);
				cur.val += delta;
			}
		}

		public int sum(String prefix) {
			Node cur = root;
			for (char c : prefix.toCharArray()) {
				cur = cur.children.get(c);
				if (cur == null) {
					return 0;
				}
			}
			return cur.val;
		}
	}
}
