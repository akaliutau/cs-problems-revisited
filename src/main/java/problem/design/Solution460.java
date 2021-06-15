package problem.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://en.wikipedia.org/wiki/Least_frequently_used
 * 
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1. put(key, value) - Set or insert the
 * value if the key is not already present. When the cache reaches its capacity,
 * it should invalidate the least frequently used item before inserting a new
 * item. For the purpose of this problem, when there is a tie (i.e., two or more
 * keys that have the same frequency), the least recently used key would be
 * evicted.
 * 
 * Note that the number of times an item is used is the number of calls to the
 * get and put functions for that item since it was inserted. This number is set
 * to zero when the item is removed.
 * 
 * IDEA:
 * use 2 structures to track nodes:
 * 1) map for fast access
 * 2) nodes grouped by frequency
 * 
 * Note, that the tricky part is node migration (from group to group)
 * 
 * When we GET node, then
 * 1) find and remove it from data structure
 * 2) calc new frequency
 * 3) add it to the new group
 * 
 * 
 */
public class Solution460 {

	class LFUCache {

		// node with statistics
		class Node {
			int key;
			int value;
			int count;

			Node prev;
			Node next;// == null if no next node

			Node(int key, int value) {
				this.key = key;
				this.value = value;
			}

			// attach to this node the next one
			void chain(Node node) {
				node.prev = this;
				next = node;
			}

			// remove from 2-linked list
			void remove() {
				if (next != null) {
					next.prev = prev;
				}

				if (prev != null) {
					prev.next = next;
				}
			}
		}

		int maxSize;
		int size;

		// helper structures
		Map<Integer, LinkedList<Node>> counter; // groups nodes by frequency
		Map<Integer, Node> nodeMap;             // mapping key => node for fast access

		// sequence structure (all that does not fit must be evicted, and position is determined by statistics)
		Node head;// points to the first node in cache
		Node tail;// points to the last node in cache

		int minCount;

		public LFUCache(int capacity) {
			maxSize = capacity;
			minCount = 0;
			size = 0;

			counter = new HashMap<>();
			nodeMap = new HashMap<>();

			head = null;
		}

		public int get(int key) {
			if (!nodeMap.containsKey(key)) {
				return -1;
			}

			Node requestedNode = nodeMap.get(key);// requested node

			LinkedList<Node> nodes = counter.get(requestedNode.count);
			nodes.remove(requestedNode);

			if (nodes.size() > 0) {
				Node node1 = nodes.peekFirst();
				requestedNode.chain(node1);// bubble up requested node to the top, Note: use a 3rd structure
				if (requestedNode == tail) {
					tail = node1;
				}
			}

			requestedNode.count ++;

			LinkedList<Node> updatesNodes = counter.computeIfAbsent(requestedNode.count, k -> new LinkedList<>());
			
			if (updatesNodes.size() > 0) {
				Node node1 = updatesNodes.peekLast();
				node1.chain(requestedNode);
				if (node1 == tail) {
					tail = requestedNode;
				}
			}

			updatesNodes.offer(requestedNode);

			return requestedNode.value;
		}

		public void put(int key, int value) {
			if (maxSize == 0) {
				return;
			}

			if (nodeMap.containsKey(key)) {
				get(key);

				Node node = nodeMap.get(key);
				node.value = value;
				return;
			}

			if (size == maxSize) {
				int count = tail.count;
				Node node = counter.get(count).pollFirst();
				remove(node);

				nodeMap.remove(node.key);

				counter.get(node.count).remove(node);
			}

			Node node = addToTail(key, value);

			nodeMap.put(key, node);

			counter.computeIfAbsent(node.count, k -> new LinkedList<>()).offer(node);
		}

		void remove(Node node) {
			node.remove();

			if (head == node) {
				head = head.next;
				if (head != null) {
					head.prev = null;
				}
			}

			if (tail == node) {
				tail = node.prev;
			}

			size--;
		}

		Node addToTail(int key, int value) {
			Node node = new Node(key, value);

			if (head == null) {
				head = node;
				tail = head;
			} else {
				tail.chain(node);
				tail = node;
			}

			size++;

			return node;
		}
	}



}
