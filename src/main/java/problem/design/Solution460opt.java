package problem.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
 * use 2 structures to track group:
 * 1) map for fast access
 * 2) group grouped by frequency
 * 
 * Note, that the tricky part is node migration (from group to group)
 * 
 * When we GET node, then
 * 1) find and remove it from frequency map structure
 * 2) calc new frequency
 * 3) add it to the new group at frequency map structure
 * 
 * 
 * Lets assume we have the following set:
 * 
 * frequency |    Node
 * -----------------
 *   0         ->   [10, 20, 25]  explain: most resent key must be at head, and all older keys - at the tail
 *   1         ->   [15]
 *   
 *   
 *   HEAD -> 10 -> 20 -> 15 -> TAIL
 *   
 * On adding 30:
 * 
 * frequency |    Node
 * -----------------
 *   0         ->   [30, 10, 20]
 *   1         ->   [15]
 *    
 * On get 10:
 * 
 * frequency |    Node
 * -----------------
 *   0         ->   [30, 20]
 *   1         ->   [10, 15]
 *   
 *   NOTE: optimized version, to pass TLE test
 */
public class Solution460opt {

	class LFUCache {

		// node with statistics
		class Node {
			int key;
			int value;
			int count = 0;
			
			Node prev;
			Node next;

			Node(int key, int value) {
				this.key = key;
				this.value = value;
			}
			
			public void remove() {
				prev.next = next;
				next.prev = prev;
			}

		}
		
		class DList {
			Node head;
			Node tail;
			
			public DList() {
				head = new Node(0, 0);
				tail = new Node(0, 0);
				head.next = tail;
				tail.prev = head;
			}
			
			public Node poll() {
				if (tail.prev == head) {
					return null;
				}
				Node ret = tail.prev;
				tail.prev = ret.prev;
				tail.prev.next = tail;
				return ret;
			}
			
			public void add(Node node) {
				node.prev = head;
				node.next = head.next;
				head.next = node;
			}
			
			public boolean isEmpty() {
				return head.next == tail;
			}
			
			
		}

		int maxSize;
		int size;

		// helper structures
		TreeMap<Integer, DList> freq; // groups group by frequency - each group is a list of group
		Map<Integer, Node> nodeMap;          // mapping key => node for fast access


		int minCount;

		public LFUCache(int capacity) {
			maxSize = capacity;
			minCount = 0;
			size = 0;

			freq = new TreeMap<>();
			nodeMap = new HashMap<>();

		}

		public int get(int key) {
			if (!nodeMap.containsKey(key)) {
				return -1;
			}

			Node requestedNode = nodeMap.get(key);// requested node
			
			DList group = freq.get(requestedNode.count);
			requestedNode.remove();
            if (group.isEmpty()){
                freq.remove(requestedNode.count);
            }

			requestedNode.count ++;

			DList newGroup = freq.computeIfAbsent(requestedNode.count, k -> new DList());
			newGroup.add(requestedNode);

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
				int count = freq.firstKey();
                //System.out.println("first key = " + count);
				Node node = freq.get(count).poll();
				nodeMap.remove(node.key);
                size --;
			}

			Node node = new Node(key, value);
			nodeMap.put(key, node);
			freq.computeIfAbsent(node.count, k -> new DList()).add(node);
            size ++;
		}
	}



}
