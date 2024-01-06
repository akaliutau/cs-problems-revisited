package problem.unionfind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two sentences words1, words2 (each represented as an array of strings),
 * and a list of similar word pairs pairs, determine if two sentences are
 * similar. For example, words1 = ["great", "acting", "skills"] and words2 =
 * ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs
 * = [["great", "good"], ["fine", "good"], ["acting","drama"],
 * ["skills","talent"]]. 
 * 
 * Note that the similarity relation is transitive. For
 * example, if "great" and "good" are similar, and "fine" and "good" are
 * similar, then "great" and "fine" are similar. Similarity is also symmetric.
 * 
 * 
 * For example, "great" and "fine" being similar is the same as "fine" and
 * "great" being similar. Also, a word is always similar with itself. For
 * example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are
 * similar, even though there are no specified similar word pairs. Finally,
 * sentences can only be similar if they have the same number of words. So a
 * sentence like words1 = ["great"] can never be similar to words2 =
 * ["doubleplus","good"].
 * 
 * IDEA:
 * 1) think words as nodes and pairs as edges between words
 * 2) build union sets
 * 3) answer is true, if ALL WORDS from BOTH sentences are in the same set
 * 
 */
public class Solution737 {

    // used to create a sets of similar words
    class Graph {
        int[] parent;

        public Graph(int n) {
            parent = new int[n];
            for (int i = 0; i < n; ++i)
                parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length)
            return false;
        Map<String, Integer> index = new HashMap<>();// mapping String => uid to simplify comparison
        int count = 0;
        Graph g = new Graph(2 * pairs.size());
        for (List<String> pair : pairs) {
            for (String p : pair)
                if (!index.containsKey(p)) {
                    index.put(p, count++);
                }
            g.union(index.get(pair.get(0)), index.get(pair.get(1)));// union by word's indecies
        }

        for (int i = 0; i < words1.length; ++i) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (w1.equals(w2)) {
                continue;// similar
            }
            
            if (!index.containsKey(w1) || !index.containsKey(w2) || g.find(index.get(w1)) != g.find(index.get(w2))) {
                return false;// words are not equal but they are in different association sets
            }
        }
        return true;
    }

}
