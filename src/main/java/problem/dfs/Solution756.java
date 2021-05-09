package problem.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We are stacking blocks to form a pyramid. Each block has a color which is a
 * one letter string. We are allowed to place any color block C on top of two
 * adjacent blocks of colors A and B, if and only if ABC is an allowed triple.
 * We start with a bottom row of bottom, represented as a single string. We also
 * start with a list of allowed triples allowed. Each allowed triple is
 * represented as a string of length 3. Return true if we can build the pyramid
 * all the way to the top, otherwise false. 
 * 
 *  Example 1: Input: bottom = "BCD",
 * allowed = ["BCG", "CDE", "GEA", "FFF"] Output: true 
 * 
 *  Explanation: We can stack
 *  the pyramid like this:  
 *              A 
 *             / \ 
 *            G   E
 *           / \ / \ 
 *          B   C   D 
 *          
 *          We are allowed to place G on
 * top of B and C because BCG is an allowed triple. Similarly, we can place E on
 * top of C and D, then A on top of G and E.
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
        if (bottom.length() == 1) {// top
            return true;
        }
        
        List<String> nextBottoms = new ArrayList<>();
        generateNextBottoms(bottom, nextBottoms, "", 0);// gen level using sliding window of len=2
        for (String next : nextBottoms) {// check all variants
            if (dfs(next)) {
                return true;
            }
        }
        return false;
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // build map
        map = new HashMap<>();// map BC => G
        for (String s : allowed) {
            String key = s.substring(0, 2);  // [BC] G
            String value = s.substring(2, 3);//  BC [G]
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }
        return dfs(bottom);
    }

  

}
