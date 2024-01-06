package problem.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You are given a string s, and an array of pairs of indices in the string
 * pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 * You can swap the characters at any pair of indices in the given pairs any
 * number of times. Return the lexicographically smallest string which can get from s  using the swaps. 
 * 
 * Example 1: Input: s = "dcab", pairs =
 * [[0,3],[1,2]] Output: "bacd" 
 * 
 * Explanation: Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * 
 * IDEA:
 * 1) build a network of bi-directional connections between letters
 * 2) for each node-letter: traverse through all graph and find the connected set
 *    f.e. for the example case it should be
 *     d <-> b , c <-> a
 * 3) sort and re-map all subsets
 * 
 */
public class Solution1202 {
    
    static class Letter {
        boolean visited = false;
        char letter;
        int id;
        List<Letter> next = new ArrayList<>();
        
        public Letter(char letter, int id) {
            this.letter = letter;
            this.id = id;
        }
        
    }
    
      static void find(Letter node, List<Letter> visited) {
        for (Letter n : node.next) {
            if  (n.visited) {
                continue;
            }
            visited.add(n);
            n.visited = true;
            find(n, visited);
        }
    }
    
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] str = s.toCharArray();
        int n = str.length;
        Letter[] nodes = new Letter[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Letter(str[i], i);
        }
        for (List<Integer> pair : pairs) {
            nodes[pair.get(0)].next.add(nodes[pair.get(1)]);
            nodes[pair.get(1)].next.add(nodes[pair.get(0)]);
        }
        for (Letter node : nodes) {
            if (node.visited) {
                continue;
            }
            List<Letter> linked = new ArrayList<>();
            linked.add(node);
            node.visited = true;
            find(node, linked);
            Collections.sort(linked, (o,p) -> o.id - p.id);
            List<Letter> chain = new ArrayList<>(linked);
            
            Collections.sort(chain, (o,p) -> o.letter - p.letter);
            
            // transfer and map sorted letters to old ones
            char[] vals = new char[chain.size()];
            for (int i = 0; i < chain.size(); i++) {
                vals[i] =  chain.get(i).letter;
            }
            for (int i = 0; i < chain.size(); i++) {
                linked.get(i).letter =  vals[i];
            }
        }
        
        // extract letters from nodes and put them into final string
        StringBuilder sb = new StringBuilder();
        for (Letter node : nodes) {
            sb.append(node.letter);
        }       
        return sb.toString();
        
    }
}
