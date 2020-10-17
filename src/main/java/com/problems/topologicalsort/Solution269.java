package com.problems.topologicalsort;

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
 * Idea: 
 * 1) extract partial relations from known words 
 * 2) topologically sort them 
 * 3) output ordered letters 
 * 
 * Input: [ "wrt", "wrf", "er", "ett", "rftt" ] 
 * Output: "wertf"
 * 
 * 1) partial relations: 
 *    w->e, e->r,   t->f, r->t
 * 2) ordered:
 *    w->e, e->r, r->t, t->f
 * 3)  wertf     
 *     
 */
public class Solution269 {

    public String alienOrder(String[] words) {

        // Step 0: Create data structures and find all unique letters.
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> counts = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }

        // Step 1: Find all edges.
        for (int i = 0; i < words.length - 1; i++) {
            char[] word1 = words[i].toCharArray();
            char[] word2 = words[i + 1].toCharArray();
            // Check that word2 is not a prefix of word1.
            if (word1.length > word2.length && words[i].startsWith(words[i + 1])) {
                return "";
            }
            // Find the first non match and insert the corresponding relation.
            for (int j = 0; j < Math.min(word1.length, word2.length); j++) {
                if (word1[j] != word2[j]) {
                    adjList.get(word1[j]).add(word2[j]);
                    counts.put(word2[j], counts.get(word2[j]) + 1);
                    break;
                }
            }
        }

        // Step 2: Breadth-first search.
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : counts.keySet()) {
            if (counts.get(c).equals(0)) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.remove();
            sb.append(c);
            for (Character next : adjList.get(c)) {
                counts.put(next, counts.get(next) - 1);
                if (counts.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }

        if (sb.length() < counts.size()) {
            return "";
        }
        return sb.toString();
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
