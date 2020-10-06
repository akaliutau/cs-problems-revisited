package com.problems.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * BFS 
 * 
 * Input: beginWord = "hit", endWord = "cog", 
 * wordList = ["hot","dot","dog","lot","log","cog"] 
 * 
 * Output: [
 * ["hit","hot","dot","dog","cog"], 
 * ["hit","hot","lot","log","cog"] 
 * ]
 */
public class Solution126 {

    class Node {
        String word;
        Node parent;

        public Node(String word, Node parent) {
            this.word = word;
            this.parent = parent;
        }
    }

    class Solution {
        int minDist = Integer.MAX_VALUE; // looking for only shortest paths
        
        List<String> paths(String word, Map<String, List<String>> tiesMap, Set<String> visited) {
            Set<String> ans = new HashSet<>();
            for (int i = 0; i < word.length(); i++) {
                String key = String.format("%s-%s", word.substring(0, i), word.substring(i + 1));
                if (tiesMap.containsKey(key)) {
                    for (String w : tiesMap.get(key)) {
                        if (!visited.contains(w)) {
                            ans.add(w);
                        }
                    }
                }
            }
            return new ArrayList<>(ans);
        }

        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            // fast check
            List<List<String>> ans = new ArrayList<>();
            Set<String> words = new HashSet<>(wordList);
            if (!words.contains(endWord)) {
                return ans;
            }
            // building graph
            // "?og" => [dog, cog, log]
            // "d?g" => dog
            // "do?" => dog
            
            Map<String, List<String>> tiesMap = new HashMap<>();
            for (String word : wordList) {
                for (int i = 0; i < word.length(); i++) {
                    String key = String.format("%s-%s", word.substring(0, i), word.substring(i + 1));
                    if (!tiesMap.containsKey(key)) {
                        tiesMap.put(key, new ArrayList<>());
                    }
                    tiesMap.get(key).add(word);
                }
            }
            

            Node node = new Node(beginWord, null);
            Queue<Node> q = new LinkedList<>();
            q.add(node);
            Set<String> visited = new HashSet<>();
            int dist = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    node = q.remove();
                    String word = node.word;
                    if (word.equals(endWord) && dist <= minDist) {// reached the end
                        minDist = dist;
                        List<String> path = new ArrayList<>();
                        // backtracing to restore the path
                        while (node != null) {
                            path.add(0, node.word);
                            node = node.parent;
                        }
                        // filter out only the shortest paths
                        if (ans.isEmpty() || ans.get(0).size() == path.size()) {
                            ans.add(path);
                        } else {
                            if (ans.get(0).size() > path.size()) {// the very first path is too long
                                ans = new ArrayList<>();
                                ans.add(path);
                            }
                        }
                    }
                    // mark processed word as visited
                    visited.add(word);
                    // get variations
                    List<String> next = paths(word, tiesMap, visited);
                    for (String w : next) {
                        q.add(new Node(w, node));
                    }
                }
                dist++;
            }
            return ans;
        }

    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
