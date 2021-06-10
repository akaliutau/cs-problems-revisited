package problem.segmenttree;

/**
 * 
 * Implement a MyCalendar class to store your events. A new event can be added
 * if adding the event will not cause a double booking.
 * 
 * Your class will have the method, book(int start, int end). Formally, this
 * represents a booking on the half open interval [start, end), the range of
 * real numbers x such that start <= x < end.
 * 
 * A double booking happens when two events have some non-empty intersection
 * (ie., there is some time that is common to both events.)
 * 
 * For each call to the method MyCalendar.book, return true if the event can be
 * added to the calendar successfully without causing a double booking.
 * Otherwise, return false and do not add the event to the calendar.
 * 
 * Your class will be called like this: MyCalendar cal = new MyCalendar();
 * MyCalendar.book(start, end) Example 1:
 * 
 * MyCalendar(); MyCalendar.book(10, 20); // returns true MyCalendar.book(15,
 * 25); // returns false MyCalendar.book(20, 30); // returns true
 * 
 * Explanation: The first event can be booked. The second can't because time 15
 * is already booked by another event. The third event can be booked, as the
 * first event takes every time less than 20, but not including 20.
 * 
 * IDEA:
 * 
 * use 3 node tree
 * 
 *  |-------------|=====|---------------------|
 *  
 *  |-------------|=====|-----|=====|----------|
 *                               
 *                               /|\
 *                                |
 */
public class Solution729 {

	static class Node {
		int[] range;
		Node left;
		Node central;
		Node right;
		boolean leaf = false;

		public Node(int start, int end) {
			this.range = new int[] { start, end };
		}

		public Node(int start, int end, boolean leaf) {
			this.range = new int[] { start, end };
			this.leaf = leaf;
		}

		public boolean intersection(int start, int end) {
			return !(start >= range[1] || end <= range[0]);
		}

	}

	static class MyCalendar {

		Node tree;

		public MyCalendar() {
			this.tree = new Node(0, 1_000_000_001);
		}

		public boolean book(int start, int end) {
			return add(this.tree, start, end);
		}

		private boolean add(Node node, int start, int end) {
			if (node == null) {
				return true;
			}

			// split on 3 parts
			if (node.central != null) {
				if (node.central.intersection(start, end)) {
					return false;
				}
				if (node.left.intersection(start, end)) {
					return add(node.left, start, end);
				}
				if (node.right.intersection(start, end)) {
					return add(node.right, start, end);
				}
			}
    		node.central = new Node(start, end, true);
			node.left = new Node(node.range[0], start);
			node.right = new Node(end, node.range[1]);
			return true;

		}
	}

}
