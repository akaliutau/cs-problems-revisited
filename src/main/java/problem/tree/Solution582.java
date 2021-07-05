package problem.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * You have n processes forming a rooted tree structure. You are given two
 * integer arrays pid and ppid, where pid[i] is the ID of the ith process and
 * ppid[i] is the ID of the ith process's parent process.
 * 
 * Each process has only one parent process but may have multiple children
 * processes. Only one process has ppid[i] = 0, which means this process has no
 * parent process (the root of the tree).
 * 
 * When a process is killed, all of its children processes will also be killed.
 * 
 * Given an integer kill representing the ID of a process you want to kill,
 * return a list of the IDs of the processes that will be killed. You may return
 * the answer in any order.
 * 
 * IDEA:
 * 1. iterate through all nodes (both parent and child), and create 
 *
 */
public class Solution582 {
	
    static class Node {
		int id;
		List<Node> children = new ArrayList<>();
		
		public Node(int id) {
			this.id = id;
		}
        
        public String toString(){
            return "Node[" + id + "] ->" + children;
        }
		
	}
	
	void findKilledNodes(Node node, int kill, List<Integer> killed) {
		if (node == null) {
			return;
		}
		if (node.id == kill) {
			killed.add(node.id);
			for (Node childNode : node.children) {
				findKilledNodes(childNode, childNode.id, killed);
			}
		}else {
			for (Node childNode : node.children) {
				findKilledNodes(childNode, kill, killed);
			}
		}
	}

	public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
		Map<Integer,Node> nodes = new HashMap<>();
		int n = pid.size();
		for (int i = 0; i < n; i++) {
			int parentId = ppid.get(i);
			int nodeId = pid.get(i);
			Node parentNode = nodes.computeIfAbsent(parentId, id -> new Node(parentId));
			Node node = nodes.computeIfAbsent(nodeId, id -> new Node(nodeId));
            nodes.put(parentId, parentNode);
			nodes.put(nodeId, node);
			parentNode.children.add(node);// bootstrapping
		}
		Node root = nodes.get(0);
		List<Integer> killed = new ArrayList<>(); 
		findKilledNodes(root, kill, killed);
		return killed;
	}

}
