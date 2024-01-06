package problem.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import problem.model.TreeNode;

/**
 * 
 * Given the root of a binary tree, collect a tree's nodes as if you were doing
 * this:
 * 
 * Collect all the leaf nodes. Remove all the leaf nodes. Repeat until the tree
 * is empty.
 */
public class Solution366 {
	 void dfs(TreeNode node, Map<TreeNode,Integer> deps, Map<TreeNode,TreeNode> revMap){
	        if (node == null){
	            return;
	        }
	        int count = 0;
	        if (node.left != null){
	            count ++;
	        }
	        if (node.right != null){
	            count ++;
	        }
	        deps.put(node, count);
	        if (node.left != null){
	            revMap.put(node.left, node);
	        }
	        if (node.right != null){
	            revMap.put(node.right, node);
	        }
	        dfs(node.left, deps, revMap);
	        dfs(node.right, deps, revMap);
	    }
	    
	    public List<List<Integer>> findLeaves(TreeNode root) {
	        List<List<Integer>> ans = new ArrayList<>();
	        Map<TreeNode,Integer> deps = new HashMap<>();
	        Map<TreeNode,TreeNode> revMap = new HashMap<>();
	        dfs(root, deps, revMap);
	        Queue<TreeNode> q = new LinkedList<>();
	        for (TreeNode node : deps.keySet()){
	            if (deps.get(node) == 0){
	                q.add(node);
	            }
	        }
	        while (!q.isEmpty()){
	            List<Integer> layer = new ArrayList<>();
	            int sz = q.size();
	            for (int i = 0; i < sz; i ++){
	                TreeNode n = q.poll();
	                layer.add(n.val);
	                TreeNode parent = revMap.get(n);
	                if (parent != null){
	                    deps.compute(parent, (k, v) -> v - 1);
    	                if (deps.get(parent) == 0){
	                        q.add(parent);
	                    }
	                }
	            }
	            ans.add(layer);
	        }
	        
	        return ans;
	    }
}
