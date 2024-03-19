package problem.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * There is a long table with a line of plates and candles arranged on top of it.
 * You are given a 0-indexed string s consisting of characters '*' and '|' only,
 * where a '*' represents a plate and a '|' represents a candle.
 *
 * You are also given a 0-indexed 2D integer array queries
 * where queries[i] = [lefti, righti] denotes the substring s[lefti...righti] (inclusive).
 * For each query, you need to find the number of plates between candles that are in the substring.
 * A plate is considered between candles if there is at least one candle to its left and at least one candle to its right in the substring.
 *
 * For example, s = "||**||**|*", and a query [3, 8] denotes the substring "*||**|".
 * The number of plates between candles in this substring is 2, as each of the two plates has at least
 * one candle in the substring to its left and right.
 * Return an integer array answer where answer[i] is the answer to the ith query.
 *
 */

class Solution2055 {

    static int calc(int[] q, Node node) {
        if (node == null) return 0;
        if (q[0] <= node.from && q[1] >= node.to) return node.getSize();
        if (q[0] >= node.to || q[1] <= node.from) return 0; // check intersection
        int r = 0;
        r += calc(q, node.left);
        r += calc(q, node.right);
        return r;
    }

    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] ans = new int[queries.length];
        int l = 0, r = s.length() - 1;
        if (s.charAt(s.length() - 1) == '|') s = s + " ";
        String[] pl = s.split("\\|");
        Node[] nodes = new Node[pl.length];
        int idx = -1;
        int i = 0;
        for (String p : pl) {
            Node n = new Node();
            n.from = idx;
            n.to = idx + p.length() + 1;
            n.size = p.isBlank() ? 0 : p.length();
            nodes[i++] = n;
            idx = n.to;
        }
        if (nodes.length > 0) {
            nodes[0].edge = true;
            nodes[nodes.length - 1].edge = true;
        }
        Deque<Node> pq = new LinkedList<>();
        Arrays.stream(nodes).forEach(pq::add);

        while (pq.size() > 1) {
            Node ln = pq.poll();
            if (ln.from > pq.peek().from) {
                pq.add(ln);
                continue;
            }
            Node rn = pq.poll();
            if (ln.from > rn.from) {
                Node t = ln;
                ln = rn;
                rn = t;
            }
            Node next = new Node();
            next.from = ln.from;
            next.to = rn.to;
            next.size = ln.getSize() + rn.getSize();
            next.left = ln;
            next.right = rn;
            pq.add(next);
            //System.out.println("toadd: " + next.from + " " + next.to + ": " + next.size);
        }
        Node root = pq.poll();

        i = 0;
        for (int[] q : queries) {
            ans[i++] = calc(q, root);
        }
        return ans;
    }

    class Node {
        Node left;
        Node right;
        int from;
        int to;
        int size = 0;
        boolean edge = false;

        public int getSize() {
            return edge ? 0 : size;
        }
    }
}