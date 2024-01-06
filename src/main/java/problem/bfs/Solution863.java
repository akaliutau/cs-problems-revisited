package problem.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import problem.model.TreeNode;

/**
 * We are given a binary tree (with root node root), a target node, and an
 * integer value K.
 * 
 * Return a list of the values of all nodes that have a distance K from the
 * target node. The answer can be returned in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 
 * Output: [7,4,1]
 * 
 * Explanation: The nodes that are a distance 2 from the target node (with value
 * 5) have values 7, 4, and 1.
 * 
 * IDEA:
 * 1) calc all parents for each node
 * 2) use layered BFS for each node: left, right, parent
 */
public class Solution863 {

	void dfs(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> parents) {
		if (node != null) {
			parents.put(node, parent);
			dfs(node.left, node, parents);
			dfs(node.right, node, parents);
		}
	}

	public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
		// first calculates parents for all nodes
		Map<TreeNode, TreeNode> parents = new HashMap<>();
		dfs(root, null, parents);

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(target);

		Set<TreeNode> seen = new HashSet<>();
		seen.add(target);
		seen.add(null);// all null

		int dist = 0;
		while (!queue.isEmpty()) {
			if (dist == k) {
				List<Integer> ans = new ArrayList<>();
				for (TreeNode n : queue) {// layer #k
					if (n != null) {
						ans.add(n.val);
					}
				}
				return ans;
			}
			// use layered BFS
			int layer = queue.size();
			for (int i = 0; i < layer; i++) {
				TreeNode node = queue.poll();
				if (node != null) {// left, right, parent
					if (!seen.contains(node.left)) {
						seen.add(node.left);
						queue.add(node.left);
					}
					if (!seen.contains(node.right)) {
						seen.add(node.right);
						queue.add(node.right);
					}
					TreeNode parent = parents.get(node);
					if (!seen.contains(parent)) {
						seen.add(parent);
						queue.add(parent);
					}
				}
			}
			dist++;
		}

		return new ArrayList<Integer>();
	}



}
