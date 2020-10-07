package com.problems.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.problems.model.TreeNode;

/**
 * Tree, BFS
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
        // first calc parents for all nodes
        Map<TreeNode, TreeNode> parents = new HashMap<>();
        dfs(root, null, parents);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);

        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!queue.isEmpty()) {
            if (dist == k) {
                List<Integer> ans = new ArrayList<>();
                for (TreeNode n : queue) {
                    if (n != null){
                        ans.add(n.val);
                    }
                }
                return ans;
            }
            // use layered BFS
            int layer = queue.size();
            for (int i = 0; i < layer; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
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

    public static void main(String[] arg) {

        System.out.println();

    }

}
