package problem.graph;

/**
 * You are given an undirected graph. You are given an integer n which is the
 * number of nodes in the graph and an array edges, where each edges[i] = [ui,
 * vi] indicates that there is an undirected edge between ui and vi.
 * 
 * A connected trio is a set of three nodes where there is an edge between every
 * pair of them.
 * 
 * The degree of a connected trio is the number of edges where one endpoint is
 * in the trio, and the other is not.
 * 
 * Return the minimum degree of a connected trio in the graph, or -1 if the
 * graph has no connected trios.
 * 
 * IDEA:
 * 
 * use adjacency matrix as we only have to know that nodes u  and v connected or not
 * 
 * 1. build adjacency matrix (connected) first:
 * 
 *    | 0 1 2 3 4 
 * ___|____________
 * 0  | 1
 * 1  |   1 1   1
 * 2  |   1 1 0 1 
 * 3  |       1
 * 4  |   1 1   1
 *  
 *  
 * 2. iterate through all edges and for  each already connected u & v find a such w, that w <-> u & w <-> v.
 * 3. calculate degree taking into account that in triangle base degree == 6 
 * 
 * 
 */
public class Solution1761 {
	private boolean[][] connected;
    private int[] degrees;
    
    void build(int[][] edges) {
        for (int[] edge : edges) {      
            int u = edge[0];
            int v = edge[1];
            connected[u][v] = true;
            connected[v][u] = true;
            degrees[u] ++;// each connection increases degree
            degrees[v] ++;// each connection increases degree
        }
    }
    
    public int minTrioDegree(int n, int[][] edges) {
        this.connected = new boolean[n + 1][n + 1];
        this.degrees = new int[n + 1];
        build(edges);             // O(E)
        
        int res = Integer.MAX_VALUE;
        for (int[] edge : edges) {      // O(E)
            int u = edge[0];
            int v = edge[1];
            // note: the vertices in edge: u-v  are already connected!
            for (int w = 0; w <= n; w ++) {    // O(N)
                if (w == u || v == w) continue;
                if (connected[u][w] && connected[v][w]) {
                    res = Math.min(res, degrees[u] + degrees[v] + degrees[w] - 6);
                }
            }
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }
    
    
}
