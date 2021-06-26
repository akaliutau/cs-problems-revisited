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

			public Node(int value, int index, Node previous) {
				this.value = value;
				this.index = index;
				this.prev = previous;
				next = null;
			}
		}

		Node head;
		Node tail;
		PriorityQueue<Node> queue;
		int index;

		public MaxStack() {
			head = null;
			tail = null;
			index = 0;
			queue = new PriorityQueue<>((o, p) -> {
				return o.value == p.value ? p.index - o.index : p.value - o.value;
			});
		}

		public void push(int x) {
			Node node = new Node(x, index++, tail);
			if (tail == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				tail = node;
			}
			queue.add(node);
		}

		public int pop() {
			int value = tail.value;
			queue.remove(tail);
			removeNode(tail);
			return value;
		}

		public int top() {
			return tail.value;
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
			if (head != node && tail != node) {
				Node prev = node.prev;
				Node next = node.next;
				prev.next = next;
				next.prev = prev;
			} else {
				if (node == head) {
					head = head.next;
				}
				if (node == tail) {
					tail = tail.prev;
				}
			}
			node.prev = null;
			node.next = null;
		}

	}
}
