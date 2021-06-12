package problem.tree;

import java.util.Stack;

import problem.model.TreeNode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * IDEA:
 * 0) use inorder traversing (simulated via Stack); any disorder element is a swapped one 
 * 
 * 1) start from the leftmost elem ()
 * 2) compare it with central
 * 3) in case of disorder - save to x | to y
 * 
 *       7               7
 *      /               /
 *     6               6       <-- root
 *    / \             /
 *   4   5           4         <-- leftMost
 *   
 *     leftMost
 *    /   root
 *   /  / 
 *   | / /
 *   | | | /
 *   4 6 5 7
 */
public class Solution99 {

	void swap(TreeNode a, TreeNode b) {
		int tmp = a.val;
		a.val = b.val;
		b.val = tmp;
	}

	public void recoverTree(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		TreeNode x = null, y = null;
		TreeNode leftMost = null;// the most left element found so far

		while (!stack.isEmpty() || root != null) {
			while (root != null) {// find the leftmost elem
				stack.add(root);  // add all elems that lead to this elem, i.e form a path
				root = root.left;
			}
			// at this point root == null and the top of the stack contains the root which has no more left elements
			root = stack.pop();
			// this block should executed twice:
			// 1) find disorder - left_elem > right.elem
			// 2) find the next biggest elem to the found one
			if (leftMost != null && root.val < leftMost.val) {// omitted on the smallest elem in BST
				y = root;
				if (x == null)
					x = leftMost;
				else
					break;
			}
			leftMost = root;
			root = root.right;
		}

		swap(x, y);
	}


}
