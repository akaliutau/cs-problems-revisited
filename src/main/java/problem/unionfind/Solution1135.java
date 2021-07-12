package problem.unionfind;

import java.util.Arrays;

/**
 * There are N cities numbered from 1 to N. You are given connections, where
 * each connections[i] = [city1, city2, cost] represents the cost to connect
 * city1 and city2 together. (A connection is bidirectional: connecting city1
 * and city2 is the same as connecting city2 and city1.) 
 * 
 * Return the minimum cost
 * so that for every pair of cities, there exists a path of connections
 * (possibly of length 1) that connects those two cities together. The cost is
 * the sum of the connection costs used. If the task is impossible, return -1.
 * 
 * 
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]] Output: 6 Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 * 
 * IDEA:
 * Use Kruskal algorithm with Find-Union approach
 * Basically this will be a spanning tree
 */
public class Solution1135 {

    static class Graph {

        int[] set;// each element in set represents a city

        public Graph(int n) {
            set = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                set[i] = i;
            }
        }

        int find(int x) {
            if (set[x] == x)
                return x;
            return set[x] = find(set[x]);
        }

        void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x != y)
                set[x] = y;
        }

    }

    public int minimumCost(int n, int[][] connections) {
        int cnt = 0;
        int totalCost = 0;
        
        Arrays.sort(connections, (a, b) -> a[2] - b[2]);
        Graph g = new Graph(n);
        
        for (int[] e : connections) {
            if (g.find(e[0]) != g.find(e[1])) {// e[0] and e[1] are not in the same set yet
                totalCost += e[2];
                cnt++;
                g.union(e[0], e[1]);
                if (cnt == n - 1) {// connected all cities
                    break;
                }
            }
        }
        return cnt == n - 1 ? totalCost : -1;
    }

}
