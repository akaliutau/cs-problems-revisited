package problem.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there
 * is an edge between nodes ai and bi, and two nodes source and destination of
 * this graph, determine whether or not all paths starting from source
 * eventually, end at destination, that is:
 * 
 * At least one path exists from the source node to the destination node 
 * 
 * If a path exists from the source node to a node with no outgoing edges, then that
 * node is equal to destination. The number of possible paths from source to
 * destination is a finite number. Return true
 * 
 * IDEA:
 * 
 * optimized Brute Force, with path merging using 3 statuses
 * 
 * 1) create a graph of interlinked nodes
 * 2) invoke dfs on this graph
 * 3) use 3 states for nodes instead of just "visited"
 *    
 *    
 *           1
 *         /   \  
 *        2      3   
 *        |   /  | 
 *        |  /   |
 *        4      5
 *        |
 *        6
 *        
 *  paths 1 -> 2 -> 4 -> 6 and 1 -> 3 -> 4 -> 6 can be merged @ point 4      
 */
public class Solution1059 {

    enum Status { NOTVISITED, INPROGRESS, COMPLETE };
    
    static class Node {
    	int id;
    	List<Integer> children = new ArrayList<>();
    }
    
    boolean dfs(Node[] graph, int node, int dest, Status[] states) {
        
        // 1st option: the state is INPROGRESS, this is a backward edge and hence, it creates a loop.
    	// 2nd option: the state is COMPLETE, on can return here, because the tail coincides with already built path
        if (!states[node].equals(Status.NOTVISITED)) {
            return states[node] == Status.COMPLETE;
        }
        
        if (graph[node].children.isEmpty()) {
            return node == dest;
        }
        
        // before dfs lock the node with status INPROGRESS
        states[node] = Status.INPROGRESS;
        
        for (int next : graph[node].children) {
            if (!dfs(graph, next, dest, states)) {
                return false;
            }
        }
        // modified backtracking:
        // change the status to COMPLETE, meaning the path was built successfully
        states[node] = Status.COMPLETE;
        return true;
    }
    
    Node[] buildDigraph(int n, int[][] edges) {
        Node[] graph = new Node[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new Node();
        }
        
        for (int[] edge : edges) {
            graph[edge[0]].children.add(edge[1]);
        }
        
        return graph;
    }
    
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        
    	Node[] graph = buildDigraph(n, edges);
    	Status[] statuses = new Status[n];
    	Arrays.fill(statuses, Status.NOTVISITED);
        return dfs(graph, source, destination, statuses);
    }

}
