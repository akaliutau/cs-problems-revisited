package problem.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import problem.model.TreeNode;

/**
 * 
 * Given the root of a binary tree, collect a tree's nodes as if you were doing
 * this:
 * 
 * Collect all the leaf nodes. Remove all the leaf nodes. Repeat until the tree
 * is empty.
 * 
 * IDEA:
 * 1. invoke collectLeaves until collector brings non-empty set of nodes
 * 2. add the node only of both children nodes either null || were added previously (== as null)
 * 
 * O(d * n) 
 * 
 * 
 */
public class Solution366 {
	void collectLeaves(TreeNode node, List<Integer> collector, Set<TreeNode> collected){
        if (node == null){
            return;
        }
        if ((node.left == null || collected.contains(node.left)) && 
            (node.right == null || collected.contains(node.right)) &&
             !collected.contains(node)){
            collector.add(node.val);
            collected.add(node);
        } 
        collectLeaves(node.left, collector, collected);
        collectLeaves(node.right, collector, collected);
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<TreeNode> collected = new HashSet<>();
        while(true){
            List<Integer> collector = new ArrayList<>();
            collectLeaves(root, collector, collected);
                if (collector.isEmpty()){
                    break;
                }
            ans.add(collector);
        }
        return ans;
    }
    
}
