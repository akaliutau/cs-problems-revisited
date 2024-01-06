package problem.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Least Recently Used (LRU) cache.
 * https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU
 * 
 * Design a data structure that follows the constraints of a Least Recently Used
 * (LRU) cache.
 * 
 * Implement the LRUCache class:
 * 
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise
 * return -1. void put(int key, int value) Update the value of the key if the
 * key exists. Otherwise, add the key-value pair to the cache. If the number of
 * keys exceeds the capacity from this operation, evict the least recently used
 * key.
 * 
 * IDEA:
 * use q queue with time ordering - the oldest nodes will be at the tail
 * 
 * 1) use a double-linked list to hold data
 * 2) on get: bubble up the requested node to the head
 * 3) on put:
 *   a) if exists - update the value and bubble up
 *   b) add new to the head and remove the oldest if needed
 * 
 */
public class Solution146 {

	public class LRUCache {

		class Node {
			int key;
			int value;
			Node prev;
			Node next;

			public Node() {
			}

			public Node(int key, int value) {
				this.key = key;
				this.value = value;
			}
		}
		
		private Map<Integer, Node> cache = new HashMap<>();
		private int size;
		private int capacity;
		private Node head, tail;

		public LRUCache(int capacity) {
			size = 0;
			this.capacity = capacity;

			head = new Node();
			tail = new Node();

			head.next = tail;
			tail.prev = head;
		}


		// adds node from the head (as in queue)
		private void addNode(Node node) {
			node.prev = head;
			node.next = head.next;
			
			head.next.prev = node;
			head.next = node;

		}

		private void removeNode(Node node) {
			Node prev = node.prev;
			Node next = node.next;

			prev.next = next;
			next.prev = prev;
		}

		// composite op
		// the same as add, but for existing node
		private void bubbleUp(Node node) {
			removeNode(node);
			addNode(node);
		}

		private Node removeOldest() {
			Node res = tail.prev;
			removeNode(res);
			return res;
		}


		public int get(int key) {
			Node node = cache.get(key);
			if (node == null)
				return -1;

			// move the accessed node to the head;
			bubbleUp(node);

			return node.value;
		}

		public void put(int key, int value) {
			Node node = cache.get(key);

			if (node == null) {// create new cached entry 
				Node newNode = new Node(key, value);

				cache.put(key, newNode);
				addNode(newNode); // note that newly added entries will be right at the head from the very beginning

				++size;

				if (size > capacity) {
					// pop the tail
					Node tail = removeOldest();// oldest elem
					cache.remove(tail.key);
					--size;
				}
			} else {
				// just update the value.
				node.value = value;
				bubbleUp(node);
			}
		}
	}

	

}
