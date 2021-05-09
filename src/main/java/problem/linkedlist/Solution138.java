package problem.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * The Linked List is represented in the input/output as a list of n nodes. Each
 * node is represented as a pair of [val, random_index] where:
 * 
 * val: an integer representing Node.val random_index: the index of the node
 * (range from 0 to n-1) where random pointer points to, or null if it does not
 * point to any node.
 * 
 */
public class Solution138 {

	static class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			next = null;
			random = null;
		}
	}

	// HashMap which holds old nodes as keys and new nodes as its values.
	Map<Node, Node> visitedHash = new HashMap<>();

	// returns a clone of head
	public Node copyRandomList(Node head) {

		if (head == null) {
			return null;
		}

		// If we have already processed the current node, then we simply return the
		// cloned version of it.
		if (visitedHash.containsKey(head)) {
			return visitedHash.get(head);
		}

		// Create a new node with the value same as old node. (i.e. copy the node)
		Node node = new Node(head.val);
		visitedHash.put(head, node);

		// Recursively copy the remaining linked list starting once from the next
		// pointer and then from the random pointer.
		node.next = copyRandomList(head.next);
		node.random = copyRandomList(head.random);

		return node;
	}


}
