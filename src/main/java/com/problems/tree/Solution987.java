package com.problems.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.problems.model.TreeNode;

/**
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * 
 * For each node at position (X, Y), its left and right children respectively
 * will be at positions (X-1, Y-1) and (X+1, Y-1).
 * 
 * Running a vertical line from X = -infinity to X = +infinity, whenever the
 * vertical line touches some nodes, we report the values of the nodes in order
 * from top to bottom (decreasing Y coordinates).
 * 
 * If two nodes have the same position, then the value of the node that is
 * reported first is the value that is smaller. (SPECIAL COND)
 * 
 * Return an list of non-empty reports in order of X coordinate. Every report
 * will have a list of values of nodes.
 * 
 * 
 * 
 * Input: [3,9,20,null,null,15,7] 
 * Output: [[9],[3,15],[20],[7]] 
 * 
 *           3
 *          / \
 *         9   20
 *            /  \
 *           15   7
 * 
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0): 
 * Then, the node with value 9 occurs at position (-1, -1); 
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2); 
 * The node with value 20 occurs at position (1, -1); 
 * The node with value 7 occurs at position (2, -2).
 * 
 */
public class Solution987 {

	static class Pair {
		int row;
		int val;

		public Pair(int row, int val) {
			this.row = row;
			this.val = val;
		}
	}

	Map<Integer, ArrayList<Pair>> columnTable = new HashMap<>();// map column => list of (rows+value) in this column

	int minColumn = 0, maxColumn = 0;

	void dfs(TreeNode node, Integer row, Integer column) {
		if (node == null)
			return;

		if (!columnTable.containsKey(column)) {
			columnTable.put(column, new ArrayList<>());
		}

		columnTable.get(column).add(new Pair(row, node.val));
		
		minColumn = Math.min(minColumn, column);
		maxColumn = Math.max(maxColumn, column);
		
		// preorder dfs traversal
		this.dfs(node.left, row + 1, column - 1);
		this.dfs(node.right, row + 1, column + 1);
	}

	public List<List<Integer>> verticalTraversal(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}

		Comparator<Pair> byValue = (o,p) -> Integer.compare(o.val, p.val);
		Comparator<Pair> byRow = (o,p) -> Integer.compare(o.row, p.row);
		// step 1). dfs traversal
		dfs(root, 0, 0);

		// step 2). retrieve the value from the columnTable
		for (int i = minColumn; i < maxColumn + 1; ++i) {
			// order by both "row" and "value"
			Collections.sort(columnTable.get(i), byRow.thenComparing(byValue));// up -> down, SPECIAL COND

			List<Integer> sortedColumn = new ArrayList<>();
			for (Pair p : columnTable.get(i)) {
				sortedColumn.add(p.val);
			}
			result.add(sortedColumn);
		}

		return result;
	}

	

}
