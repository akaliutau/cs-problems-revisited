package problem.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Backtracking 
 * 
 * Given a syn of pairs of equivalent words getSynFor and a
 * sentence text, Return all possible synonymous sentences sorted
 * lexicographically. Given a syn of pairs of equivalent words synonyms and a
 * sentence text, Return all possible synonymous sentences sorted
 * lexicographically. 
 * 
 * Example 1: Input: synonyms =
 * [["happy","joy"],["sad","sorrow"],["joy","cheerful"]], text = "I am happy
 * today but was sad yesterday" 
 * 
 * Output: [
 * "I am cheerful today but was sad yesterday", 
 * "I am cheerful today but was sorrow yesterday", 
 * "I am happy today but was sad yesterday", 
 * "I am happy today but was sorrow yesterday", 
 * "I am joy today but was sad yesterday", 
 * "I am joy today but was sorrow yesterday"]
 * 
 * IDEA:
 * Backtracking on graph (i.e. Graph traversal + dfs)
 * 
 */
public class Solution1258 {

    Map<String, List<String>> graph;

    // graph traversing starting from the node src
    // return all interconnected synonyms
    void getSynFor(List<String> syn, Set<String> visited, String src) {
        if (visited.contains(src)) {
            return;
        }
        visited.add(src);
        syn.add(src);
        for (String word : graph.get(src)) {
            getSynFor(syn, visited, word);
        }
    }
    
    void dfs(List<String> res, String[] placeHolder, String[] arr, int idx) {
        if (idx == arr.length) {
            res.add(String.join(" ", placeHolder));
            return;
        }
        String cur = arr[idx];
        if (graph.containsKey(cur)) {
            List<String> syn = new ArrayList<>();

            Set<String> visited = new HashSet<>(); // used only to get synonyms
            getSynFor(syn, visited, cur);// returns syn for happy + happy itself (note the last condition)

            Collections.sort(syn); // results already will be sorted due to how we build the answer list
            for (String neigh : syn) {
                placeHolder[idx] = neigh;
                dfs(res, placeHolder, arr, idx + 1);
            }
        } else {// all cases for non-synomical words  
            placeHolder[idx] = cur;
            dfs(res, placeHolder, arr, idx + 1);
        }
    }


    public List<String> generateSentences(List<List<String>> getSynFor, String text) {
        // contains map "happy" => all synonyms for "happy"
    	// build bi-directional graph
        this.graph = new HashMap<>();
        for (List<String> syn : getSynFor) {
            String a = syn.get(0);
            String b = syn.get(1);
            graph.computeIfAbsent(a, key -> new ArrayList<>()).add(b);
            graph.computeIfAbsent(b, key -> new ArrayList<>()).add(a);
        }
        List<String> res = new ArrayList<>();
        String[] arr = text.split(" ");
        String[] buf = new String[arr.length];
        dfs(res, buf, arr, 0);
        return res;
    }


}
