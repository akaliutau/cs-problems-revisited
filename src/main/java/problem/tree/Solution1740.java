package problem.tree;

import java.util.ArrayList;
import java.util.List;

import problem.model.TreeNode;

/**
 * Given the root of a binary tree and two integers p and q, return the distance
 * between the nodes of value p and value q in the tree.
 * 
 * The distance between two nodes is the number of edges on the path from one to
 * the other.
 * 
 * IDEA:
 * 1. find the path to each number
 * 2. remove the common part in paths:
 * [-1, 1, 1, 1]
 * [-1, 0, 1, 1]
 *     |
 *     idx = 1
 */
public class Solution1740 {
	
    boolean find(TreeNode node, int num, List<Integer> path, int side) {
		if (node == null) {
			return false;
		}
		if (node.val == num) {// we are adding values if and only if we have found a match
			path.add(side);
			return true;
		}
		boolean foundInLeft = find(node.left, num, path, 0);
		if (foundInLeft) {
			path.add(side);
			return true;
		}
		boolean foundInRight = find(node.right, num, path, 1);
		if (foundInRight) {
			path.add(side);
			return true;
		}
		return false;
	}
	
	public int findDistance(TreeNode root, int p, int q) {
		if (p == q) {// edge case
			return 0;
		}
		
		List<Integer> pathP = new ArrayList<>();
		boolean foundP = find(root, p, pathP, -1);
		List<Integer> pathQ = new ArrayList<>();
		boolean foundQ = find(root, q, pathQ, -1);
		
		if (!foundP || ! foundQ) {
			return 0;
		}
		int ip = pathP.size()-1;
		int iq = pathQ.size()-1;
		while (ip >=0 && iq >=0 && pathP.get(ip) == pathQ.get(iq)) {
			ip --;
            iq --;
		}
        if (ip == pathP.size()-2 || iq == pathQ.size()-2){// the very first difference is just after root node = -1, => p & q are in different branches
            return ip + iq + 2;
        }
		return Math.abs(ip - iq);
	}

}
