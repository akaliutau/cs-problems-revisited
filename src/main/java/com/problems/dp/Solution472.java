package com.problems.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * DP
 * 
 * Input: ["cat","cats","catsdogcats","dog"
 * Output: ["catsdogcats","dogcatsdog"]
 */
public class Solution472 {

    static boolean findWord(String str, int from, Set<String> hset, List<String> result) {
        String s = str.substring(from);
        if (s.isEmpty()) {
            return false;
        }

        /* Add to the result if last part of the string is found */
        if (from != 0 && hset.contains(s)) {
            result.add(str);
            return true;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            /* Check if we can complete the string using smaller words recursively */
            if (hset.contains(sb.toString())) {
                if (findWord(str, from + i, hset, result)) {
                    return true;
                }
            }
            sb.append(s.charAt(i));
        }

        return false;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        HashSet<String> hset = new HashSet<>();
        List<String> res = new ArrayList<>();

        for (String s : words) {
            hset.add(s);
        }

        for (String s : words) {
            findWord(s, 0, hset, res);
        }

        return res;
    }

    public static void main(String[] arg) {

        System.out.println("D");

    }

}
