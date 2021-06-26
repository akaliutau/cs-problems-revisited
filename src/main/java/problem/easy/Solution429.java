package problem.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 *
 */
public class Solution429 {

	static class Node {
		public int val;
		public List<Node> children;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	};

	static void buildLevel(Node node, int level, List<List<Integer>> res) {
		if (level >= res.size()) {
			res.add(new ArrayList<>());
		}
		res.get(level).add(node.val);
		if (node.children != null)
			for (Node nd : node.children) {
				buildLevel(nd, level + 1, res);
			}
	}

	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		buildLevel(root, 0, res);
		return res;
	}

}
