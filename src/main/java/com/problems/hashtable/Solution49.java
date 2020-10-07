package com.problems.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hashtable
 */
public class Solution49 {
    
    public static class FootPrint {
        public int[] chars = new int[26];

        public FootPrint(String s){
            int n = s.length();
            for (int i = 0; i < n; i++) {
                chars[(int)(s.charAt(i)-'a')]++;
            }
        }

        @Override
        public boolean equals(Object o) {
            FootPrint fp = (FootPrint) o;
            if (chars.length == fp .chars.length) {
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] != fp.chars[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(chars);
        }
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> results = new ArrayList<>();
        if (strs.length == 0) {
            return results;
        }
        Map<FootPrint,List<String>> groupped = new HashMap<>();
        for (String s: strs) {
            FootPrint fp = new FootPrint(s);
            if (!groupped.containsKey(fp)) {
                groupped.put(fp, new ArrayList<>());
            }
            groupped.get(fp).add(s);
        }
        
        results.addAll(groupped.values());
        return results;
        
    }



	public static void main(String[] arg) {
		
		System.out.println();

	}

}
