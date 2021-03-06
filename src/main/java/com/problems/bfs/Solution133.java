package com.problems.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.problems.model.Node;

/**
 * Given a reference of a node in a connected undirected graph. Return a deep
 * copy (clone) of the graph.
 * 
 * IDEA:
 * traverse through all nodes, and create new nodes with appropriate topology during traversing
 * 
 */
public class Solution133 {

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }

        // Hash map to save the cloned node and it's respective clone
        // as key and value respectively. This helps to avoid cycles.
        HashMap<Node, Node> cloned = new HashMap<>();

        // Put the first node in the queue
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(node);
        // Clone the node and put it in the cloned dictionary.
        cloned.put(node, new Node(node.val, new ArrayList<>()));

        // Start BFS traversal
        while (!queue.isEmpty()) {
            // Pop a node say "n" from the from the front of the queue.
            Node n = queue.remove();
            // Iterate through all the neighbors of the node "n"
            for (Node neighbor : n.children) {
                if (!cloned.containsKey(neighbor)) {
                    // Clone the neighbor and put in the cloned, if not present already
                    cloned.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    // Add the newly encountered node to the queue.
                    queue.add(neighbor);
                }
                // Add the clone of the neighbor to the neighbors of the clone node "n".
                cloned.get(n).children.add(cloned.get(neighbor));
            }
        }

        // Return the clone of the node from cloned.
        return cloned.get(node);
    }

 

}
