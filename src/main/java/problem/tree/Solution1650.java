package problem.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 * 
 * IDEA:
 * 
 * 1. for node p: add all nodes on the path to the root node
 * 2. for node q: do the same - add all nodes on the path to the root node until seen has been met, return seen node as a LCA
 * 
 * 
 */
public class Solution1650 {
	
	static class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node parent;
	};
	
	public Node lowestCommonAncestor(Node p, Node q) {
        
        Set<Node> parent = new HashSet<>();
        
        while(p != null){
            parent.add(p);
            p = p.parent;
        }
        
        while(!parent.contains(q)){
            q = q.parent;
        }
      return q;
    }
}
