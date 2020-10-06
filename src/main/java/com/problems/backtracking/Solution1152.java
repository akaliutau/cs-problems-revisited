package com.problems.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * hashtable, sort, backtracking
 */
public class Solution1152 {

    static class Visit {
        String username;
        int timestamp;
        String website;

        public Visit(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }
    }
    
    boolean isLexicographicallySmaller(List<String> key, List<String> result) {
        for (int i = 0; i < key.size(); i++) {
            if (!key.get(i).equals(result.get(i))) {
                return key.get(i).compareTo(result.get(i)) < 0;
            }
        }
        return false;
    }

    void helper(int currIdx, List<String> websites, List<String> currList, Map<List<String>, Integer> freqMap, Set<List<String>> visited) {
        if (currList.size() == 3) {
            if (visited.contains(currList)) {
                return;
            }
            List<String> copy = new ArrayList<>(currList);
            visited.add(copy);
            if (!freqMap.containsKey(copy)) {
                freqMap.put(copy, 0);
            }
            freqMap.put(copy, freqMap.get(copy) + 1);
            return;
        }
        for (int i = currIdx; i < websites.size(); i++) {
            currList.add(websites.get(i));
            helper(i + 1, websites, currList, freqMap, visited);
            currList.remove(currList.size() - 1);
        }
    }


    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int n = username.length;
        List<Visit> dataList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dataList.add(new Visit(username[i], timestamp[i], website[i]));
        }
        Comparator<Visit> byTimestamp = (o, p) -> Integer.compare(o.timestamp, p.timestamp);

        Collections.sort(dataList, byTimestamp);
        // group by username
        Map<String, List<String>> nameToWebsiteVisit = new HashMap<>();
        for (Visit visit : dataList) {
            if (!nameToWebsiteVisit.containsKey(visit.username)) {
                nameToWebsiteVisit.put(visit.username, new ArrayList<>());
            }
            nameToWebsiteVisit.get(visit.username).add(visit.website);
        }

        // backtracking, found all possible patterns
        Map<List<String>, Integer> freqMap = new HashMap<>();
        for (List<String> list : nameToWebsiteVisit.values()) {
            helper(0, list, new ArrayList<>(), freqMap, new HashSet<>());
        }
        // find the lower freq pattern and return it
        int maxFreq = 0;
        List<String> result = new ArrayList<>();
        for (List<String> lst : freqMap.keySet()) {
            int freq = freqMap.get(lst);
            if (freq > maxFreq) {
                maxFreq = freq;
                result = lst;
            } else if (freq == maxFreq && isLexicographicallySmaller(lst, result)) {
                result = lst;
            }
        }
        return result;
    }


    public static void main(String[] arg) {

        System.out.println("D");

    }

}
