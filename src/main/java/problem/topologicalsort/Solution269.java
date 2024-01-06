package problem.topologicalsort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * There is a new alien language which uses the latin alphabet. 
 * However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, 
 * where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 * 
 * IDEA: 
 * 1) extract partial relations from known words 
 * 2) topologically sort them 
 * 3) output ordered letters 
 * 
 * Detail example:
 * 
 * Input: [ "wrt", "wrf", "er", "ett", "rftt" ] 
 * Output: "wertf"
 * 
 * 1) partial relations: 
 *    w->e, e->r,(1st letter)   t->f (3rd letter), r->t (2nd letter, er-ett)
 * 2) ordered:
 *    w->e, e->r, r->t, t->f
 * 3)  wertf     
 *     
 */
public class Solution269 {

    public String alienOrder(String[] words) {

        // Step 0: Create data structures and find all unique letters.
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> numberOfReferences = new HashMap<>();// map of all chars we have
        for (String word : words) {
            for (char c : word.toCharArray()) {
                numberOfReferences.put(c, 0);
                graph.put(c, new ArrayList<>());
            }
        }

        // Step 1: Find all edges.
        for (int i = 0; i < words.length - 1; i++) {
        	// pick up a pair of words
            char[] word1 = words[i].toCharArray();
            char[] word2 = words[i + 1].toCharArray();
            // Check that word2 is not a prefix of word1.
            if (word1.length > word2.length && words[i].startsWith(words[i + 1])) {// some letters will be without references
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length, word2.length); j++) {
                if (word1[j] != word2[j]) {
                    graph.get(word1[j]).add(word2[j]);                     // let1 -> let2, so let2 has been referenced by 1 letters (at least)
                    numberOfReferences.compute(word2[j], (k, v) -> v + 1); // record this fact
                    break;
                }
            }
        }

        // Step 2: topological sort
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : numberOfReferences.keySet()) {
            if (numberOfReferences.get(c).equals(0)) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.remove();
            sb.append(c);
            for (Character next : graph.get(c)) {
                numberOfReferences.compute(next, (k,v) -> v - 1);
                if (numberOfReferences.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }

        if (sb.length() < numberOfReferences.size()) {// some chars are non-mapped
            return "";
        }
        return sb.toString();
    }

}
