package problem.tree;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children
 * Populate each next pointer to point to its next right node. If there is no
 * next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * IDEA:
 * 
 * Note: this approach can work only because of perfectness of the tree
 * 
 * traverse tree simultaneously for left and right branches
 *           
 *              1
 *          /         \
 *        2             3  
 *     /     \        /    \ 
 *   4        5      6       7  
 *  / \     /  \    / \     /  \
 * 8   9  10   11  12  13  14  16
 * 
 * there are 2 types of connections available:
 * 
 * 1) connect left - right
 *  
 * 2) connect inner nodes, f.e. 5 - 6
 *     
 */
public class Solution116 {

	static class Node {
		public int val;
		public Node left;
		public Node right;
		public Node next;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	};
	
	
	static void connect(Node left, Node right) {
		if (left == null || right == null) {
			return;
		}
		if (left.next == null) {
			left.next = right;
		}
		connect(left.left, left.right);
		connect(right.left, right.right);
		connect(left.right, right.left);
		
	}

	public Node connect(Node root) {
		if (root == null) {
			return null;
		}
		connect(root.left, root.right);
		return root;
	}
}
