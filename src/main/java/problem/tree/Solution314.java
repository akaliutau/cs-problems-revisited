package problem.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import problem.model.TreeNode;

/**
 * 
 * Given a binary tree, return the vertical order traversal of its nodes'
 * values. (ie, from top to bottom, column by column).
 * 
 * If two nodes are in the same row and column, the order should be from left to
 * right.
 * 
 * Examples 1:
 * 
 * Input: [3,9,20,null,null,15,7]
 * 
 *       3 
 *      /  \ 
 *     9    20
 *         /  \ 
 *        15   7
 *       
 *    -1 0 1 2 3
 * 
 * Output:
 * 
 * [ 
 *  [9],    <-- each array represents the vertical slope
 *  [3,15], 
 *  [20], 
 *  [7]
 * ]
 * 
 * IDEA:
 * Straightforward - 
 * 1) preorder traversing + be aware of index of current column
 * 
 * 2) collect in  map: col_index => list all the nodes from specific column
 * 
 */
public class Solution314 {

	static class Pair {
		TreeNode node;
		int col;

		public Pair(TreeNode node, int col) {
			this.node = node;
			this.col = col;
		}
	}

	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> output = new ArrayList<>();
		if (root == null) {
			return output;
		}

		Map<Integer, List<Integer>> columnTable = new HashMap<>();// one can use TreeMap
		// Pair of node and its column offset
		Queue<Pair> queue = new ArrayDeque<>();
		int column = 0;
		queue.add(new Pair(root, column));

		int minColumn = 0, maxColumn = 0;

		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			root = p.node;
			column = p.col;

			if (root != null) {
				if (!columnTable.containsKey(column)) {
					columnTable.put(column, new ArrayList<>());
				}
				columnTable.get(column).add(root.val);
				minColumn = Math.min(minColumn, column);// one can use TreeMap instead
				maxColumn = Math.max(maxColumn, column);// NOTE: this approach is O(1), more efficient

				queue.add(new Pair(root.left, column - 1));
				queue.add(new Pair(root.right, column + 1));
			}
		}

		for (int i = minColumn; i < maxColumn + 1; ++i) {
			output.add(columnTable.get(i));
		}

		return output;
	}

	
}
