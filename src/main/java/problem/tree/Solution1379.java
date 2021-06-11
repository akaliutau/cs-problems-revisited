package problem.tree;

import problem.model.TreeNode;

/**
 * Given two binary trees original and cloned and given a reference to a node
 * target in the original tree.
 * 
 * The cloned tree is a copy of the original tree.
 * 
 * Return a reference to the same node in the cloned tree.
 * 
 * Note that you are not allowed to change any of the two trees or the target
 * node and the answer must be a reference to a node in the cloned tree.
 * 
 * Follow up: Solve the problem if repeated values on the tree are allowed.
 * 
 * IDEA:
 * 1. strait-forward: just traverse tree using pre-order and compare for simple equality
 * 2. exit condition - when the target is met
 * 
 */
public class Solution1379 {

	static TreeNode traverse(final TreeNode original, final TreeNode cloned, final TreeNode target) {
		if (original == null) {
			return null;
		}
		if (original == target) {
			return cloned;
		}
		TreeNode result = traverse(original.left, cloned.left, target); // check left-branch traversing first
		if (result != null) {
			return result;
		}
		return traverse(original.right, cloned.right, target); // if not found check right-branch traversing first
	}

	public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
		return traverse(original, cloned, target);
	}

}
