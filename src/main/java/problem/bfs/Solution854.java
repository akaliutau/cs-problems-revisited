package problem.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Strings a and b are K-similar (for some non-negative integer K) if we can
 * swap the positions of two letters in a exactly K times so that the resulting
 * string equals b. Given two anagrams a and b, return the smallest K for which
 * a and b are K-similar. 
 * Example 1: Input: a = "ab", b = "ba" Output: 1 
 * Example 2: Input: a = "abc", b = "bca" Output: 2
 * 
 * IDEA:
 * 1) generate a space of all words-neighbors 
 * 2) perform a BFS on this space starting from word = a with target at b
 * 
 */
public class Solution854 {

	// generate all words from src which can be made closer to the target by the way of switching of 2 random letters
	// note: we are generating not all variants
    List<String> neighbors(String src, String target) {
        List<String> ans = new ArrayList<>();
        int i = 0;
        while(i < src.length() && src.charAt(i) == target.charAt(i)) {// find first difference
            i++;
        }
        // at this point i=index of different letter
        char[] word = src.toCharArray();
        for (int j = i + 1; j < src.length(); ++j) {
            if (src.charAt(j) == target.charAt(i)) {// after switch 2 letters will be equal 
                swap(word, i, j);
                ans.add(new String(word));
                swap(word, i, j);
            }
        }

        return ans;
    }

    void swap(char[] T, int i, int j) {
        char tmp = T[i];
        T[i] = T[j];
        T[j] = tmp;
    }

    public int kSimilarity(String a, String b) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(a);

        Map<String, Integer> dist = new HashMap<>();// stat
        dist.put(a, 0);

        while (!queue.isEmpty()) {
            String s = queue.poll();
            if (s.equals(b)) {
                return dist.get(s);
            }
            for (String t : neighbors(s, b)) {
                if (!dist.containsKey(t)) {
                    dist.put(t, dist.get(s) + 1);// 1 swap
                    queue.add(t);
                }
            }
        }

        throw null;
    }

}
