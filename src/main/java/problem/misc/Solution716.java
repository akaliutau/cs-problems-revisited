package problem.misc;

import java.util.PriorityQueue;

/**
 * Design a max stack data structure that supports the stack operations and
 * supports finding the stack's maximum element.
 * 
 * Implement the MaxStack class:
 * 
 * MaxStack() Initializes the stack object.
 *  
 * void push(int x) Pushes element x onto the stack. 
 * 
 * int pop() Removes the element on top of the stack and returns it.
 *  
 * int top() Gets the element on the top of the stack without removing it.
 * 
 * int peekMax() Retrieves the maximum element in the stack without removing it.
 * 
 * int popMax() Retrieves the maximum element in the stack and removes it. 
 * 
 * If there is more than one maximum element, only remove the top-most one.
 * 
 * IDEA:
 * 1. use PriorityQueue to track the max element 
 *   with additional index to track the order if there >1 maximum elements
 *   
 * 2. use DoublyLinked list to hold elements in the stack
 *
 */
public class Solution716 {
	static class MaxStack {

		static class Node {
			public int value;
			public int index;
			public Node prev;
			public Node next;

			public Node(int value, int index) {
				this.value = value;
				this.index = index;
			}
		}

		Node head;
		Node tail;
		PriorityQueue<Node> queue;
		int index; // used to establish an order in Nodes

		public MaxStack() {
			head = new Node(0, 0);
			tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
			index = 0;
			queue = new PriorityQueue<>((o, p) -> {
				return o.value == p.value ? p.index - o.index : p.value - o.value;
			});
		}
		
		
		// attach to the last elem
		public void push(int x) {
			Node node = new Node(x, index++);
			
			// bootstrapping
			node.prev = tail.prev;
			node.next = tail;
			tail.prev.next = node;
			tail.prev = node;
			
			queue.add(node);
		}

		public int pop() {
			int value = tail.prev.value;
			queue.remove(tail.prev);
			removeNode(tail.prev);
			return value;
		}

		public int top() {
			return tail.prev.value;
		}

		public int peekMax() {
			return queue.peek().value;
		}

		public int popMax() {
			Node maxNode = queue.poll();
			removeNode(maxNode);
			return maxNode.value;
		}

		private void removeNode(Node node) {
			Node prev = node.prev;
			Node next = node.next;
			prev.next = next;
			next.prev = prev;
		}

	}
}