package problem.bfs;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * In an infinite chess board with coordinates from -infinity to +infinity, you
 * have a knight at square [0, 0]. A knight has 8 possible moves it can make, as
 * illustrated below. Each move is two squares in a cardinal direction, then one
 * square in an orthogonal direction. 
 * 
 * Return the minimum number of steps needed
 * to move the knight to the square [x, y]. It is guaranteed the answer exists.
 * 
 * Example 1: Input: x = 2, y = 1 Output: 1 Explanation: [0, 0] → [2, 1]
 * 
 * IDEA:
 * combination of greedy approach and A* technique
 * 
 * 
 */
public class Solution1197 {

    static class Node {
        int x, y;
        long g = 0, f = 0;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public long h() {
            return g + f;
        }
    }
    
    long sqr(int a) {
        return a * a;
    }

    long f(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(sqr(x1 - x2) + sqr(y1 - y2));
    }

    long hash(int a, int b) {
        return (long) 20000 * a + b + 10000;
    }
    
    // defines all possible jumps from the cell (0,0)
    static int[][] dirs = new int[][] { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 }, };


    public int minKnightMoves(int x, int y) {
        Set<Long> visited = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>((o, p) -> Long.compare(o.h(), p.h()));// min goal function
        Node n = new Node(0, 0);
        n.f = f(0 , 0, x, y);
        pq.add(n);
        visited.add(hash(0, 0));
        while (pq.size() > 0) {
            Node cur = pq.poll();
            if (cur.x == x && cur.y == y) {
                return (int) (cur.g / 3);// a little hack, just in order not to add extra vars -  each step took 3 substeps
            }
            for (int[] dir : dirs) {
                int nx = dir[0] + cur.x;
                int ny = dir[1] + cur.y;
                Long key = hash(nx, ny);
                if (!visited.contains(key)) {
                    Node next = new Node(nx, ny);
                    next.g = cur.g + 3;// passed distance
                    next.f = f(nx, ny, x, y);// to pass

                    visited.add(key);
                    pq.add(next);
                }
            }
        }
        return -1;
    }
}
