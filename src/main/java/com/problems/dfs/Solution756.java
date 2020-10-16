package com.problems.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Solution756 {

    Map<String, List<String>> map;
    
 
    void generateNextBottoms(String bottom, List<String> res, String cur, int i) {
        if (cur.length() == bottom.length() - 1) {
            res.add(cur);
            return;
        }
        String key = bottom.substring(i, i + 2);
        if (!map.containsKey(key)) {
            return;
        }
        List<String> values = map.get(key);
        for (String c : values) {
            generateNextBottoms(bottom, res, cur + c, i + 1);
        }
    }

    boolean dfs(String bottom) {
        if (bottom.length() == 1)
            return true;
        List<String> nextBottoms = new ArrayList<>();
        generateNextBottoms(bottom, nextBottoms, "", 0);
        for (String next : nextBottoms) {
            if (dfs(next)) {
                return true;
            }
        }
        return false;
    }


    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // build map
        map = new HashMap<>();
        for (String s : allowed) {
            String key = s.substring(0, 2);
            String value = s.substring(2, 3);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }
        return dfs(bottom);
    }

     public static void main(String[] arg) {

        System.out.println();

    }

}
