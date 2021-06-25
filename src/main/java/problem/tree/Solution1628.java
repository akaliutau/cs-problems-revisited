package problem.tree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * Given the postfix tokens of an arithmetic expression, build and return the
 * binary expression tree that represents this expression.
 * 
 * Postfix notation is a notation for writing arithmetic expressions in which
 * the operands (numbers) appear before their operators. For example, the
 * postfix tokens of the expression 4*(5-(7+2)) are represented in the array
 * postfix = ["4","5","7","2","+","-","*"].
 * 
 * IDEA:
 * 
 * 1. if integer, then just add node to stack
 * 2. Else create operation node with left and right child nodes (note the order as  / and - operations are not associative)
 *  (associative operation is a calculation that gives the same result regardless of the way the numbers are grouped)
 *
 */
public class Solution1628 {

	static abstract class Node {
		public abstract int evaluate();
	};
	
	static class CNode extends Node{
		
		static Set<String> ops = new HashSet<>(Arrays.asList("/", "*", "-", "+"));
		
		String val;
		Node left;
		Node right;
		
		public CNode(String val) {
			this.val = val;
		}

		@Override
		public int evaluate() {
			if (isNumber()) {
				return Integer.valueOf(val);
			}
			if (val.equals("+")) {
				return left.evaluate() + right.evaluate();
			}
			if (val.equals("-")) {
				return left.evaluate() - right.evaluate();
			}
			if (val.equals("*")) {
				return left.evaluate() * right.evaluate();
			}
			if (val.equals("/")) {
				return left.evaluate() / right.evaluate();
			}
			return 0;
		}
		
		public boolean isNumber() {
			return !ops.contains(val);
		}
	};

	/**
	 * This is the TreeBuilder class. You can treat it as the driver code that takes
	 * the postinfix input and returns the expression tree represnting it as a Node.
	 */

	static class TreeBuilder {
		Node buildTree(String[] postfix) {
			Stack<Node> nodes = new Stack<>();
			int n = postfix.length;
			for (int i = 0; i < n; i++) {
				CNode node = new CNode(postfix[i]);
				if (node.isNumber()) {
					nodes.add(node);
				}else {
					node.right = nodes.pop();
					node.left = nodes.pop();
					nodes.add(node);
				}
			}
			return nodes.pop();
		}
	};

}
