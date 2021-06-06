package problem.tree;

import java.util.HashSet;
import java.util.Set;

import problem.model.TreeNode;

/**
 * Given a binary tree, we install cameras on the nodes of the tree.
 * 
 * Each camera at a node can monitor its parent, itself, and its immediate
 * children.
 * 
 * Calculate the minimum number of cameras needed to monitor all nodes of the
 * tree.
 * 
 * IDEA:
 * use post order traversing holding all litted nodes in the Set 
 * 
 * The reason - it's more optimal to set a litter NOT in leaf, but higher in the hierarchy.
 * If to start from root, there is a danger to fall in to the leaf
 *          o
 *         / \
 *        o   o
 *       / \   
 *      o   o
 *        
 */      

 
public class Solution968 {

	int ans;
	
	void dfs(TreeNode node, TreeNode parent, Set<TreeNode> covered) {
		if (node != null) {
			dfs(node.left, node, covered);
			dfs(node.right, node, covered);

			if (parent == null && !covered.contains(node) 
					|| !covered.contains(node.left)	|| !covered.contains(node.right)) {
				ans++;
				covered.add(node);
				covered.add(parent);
				covered.add(node.left);
				covered.add(node.right);
			}
		}
	}

	public int minCameraCover(TreeNode root) {
		ans = 0;
		Set<TreeNode> covered = new HashSet<>();
		covered.add(null);// to cover leafs by default

		dfs(root, null, covered);
		return ans;
	}

}
