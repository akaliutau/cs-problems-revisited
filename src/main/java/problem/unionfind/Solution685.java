package problem.unionfind;

import java.util.Arrays;

/**
 * In this problem, a rooted tree is a directed graph such that, there is
 * exactly one node (the root) for which all other nodes are descendants of this
 * node, plus every node has exactly one parent, except for the root node which
 * has no parents.
 * 
 * The given input is a directed graph that started as a rooted tree with n
 * nodes (with distinct values from 1 to n), with one additional directed edge
 * added. The added edge has two different vertices chosen from 1 to n, and was
 * not an edge that already existed.
 * 
 * The resulting graph is given as a 2D-array of edges. Each element of edges is
 * a pair [ui, vi] that represents a directed edge connecting nodes ui and vi,
 * where ui is a parent of child vi.
 * 
 * Return an edge that can be removed so that the resulting graph is a rooted
 * tree of n nodes. If there are multiple answers, return the answer that occurs
 * last in the given 2D-array.
 *
 * IDEA:
 * 
 *
 */
public class Solution685 {

	int[] parent;
    
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        
        parent = new int[n + 1];
        Arrays.fill(parent, -1);

        int[] refTo = new int[n + 1];
        
        for (int[] e: edges) {
        	refTo[e[1]]++;
        }

        int node = -1;
        for (int i = 1; i <= n; i++) {
            if (refTo[i] == 2) {
                node = i;
                break;
            }
        }
        // Case 1: there is one and only one node, n, with number of dependent children > 2, so one of them must be removed.
        if (node != -1) {
            // union all edges except for the in edges of node n
            for (int[] e: edges) {
                int u = e[0];
                int v = e[1];
                if (v == node) continue;
                union(u, v);
            }
            for (int[] e: edges) {
                int u = e[0];
                int v = e[1];
                if (v == node) {
                    if (find(u) != find(v)) {
                        union(u, v);
                    } else {
                        return e;
                    }
                }
            } 
            return null;
        }
        
        // Case 2: there is no nodes with number of dependent children > 1
        for (int[] e: edges) {
            int u = e[0];
            int v = e[1];
            if (find(u) == find(v)) return e;
            union(u, v);
        }
        return null;
    }
    
    int find(int x) {
        int r = x;
        while (parent[r] >= 0) {
            r = parent[r];
        }
        
        if (r == x) return r;
        int p = parent[x];
        while (p >= 0) {
            parent[x] = r;
            x = p;
            p = parent[x];
        }
        return r;
    }
    
    void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        
        if (parent[px] > parent[py]) {
            parent[py] += parent[px];
            parent[px] = py;
        } else {
            parent[px] += parent[py];
            parent[py] = px;
        }
    }

}
