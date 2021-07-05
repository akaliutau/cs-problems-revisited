package problem.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Design a data structure that will be initialized with a string array, and
 * then it should answer queries of the shortest distance between two different
 * strings from the array.
 * 
 * Implement the WordDistance class:
 * 
 * WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict. 
 * 
 * int shortest(String word1, String word2) returns the
 * shortest distance between word1 and word2 in the array wordsDict.
 * 
 * 
 * Example 1:
 * 
 * Input ["WordDistance", "shortest", "shortest"] [[["practice", "makes",
 * "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * Output [null, 3, 1]
 * 
 * Explanation 
 * WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]); 
 * 
 * wordDistance.shortest("coding", "practice"); // return 3 
 * 
 * wordDistance.shortest("makes", "coding"); // return 1
 * 
 * IDEA:
 * 
 * 1. use the sorted lists of indexes for each word in pair
 * 2. compare only the closest elements in lists
 * 
 */
public class Solution244 {

	class WordDistance {
	 Map<String,List<Integer>> positionMap = new HashMap<>();

	    public WordDistance(String[] wordsDict) {
	        int idx = 0;
	        for (String word : wordsDict){
	            positionMap.computeIfAbsent(word, w -> new ArrayList<>()).add(idx++);
	        }
	    }
	    
	    public int shortest(String word1, String word2) {
	        int idx1 = 0;
	        int idx2 = 0;
	        List<Integer> lst1 = positionMap.get(word1);
	        List<Integer> lst2 = positionMap.get(word2);
	        int dist = Integer.MAX_VALUE;
	        while (idx1 < lst1.size() && idx2 < lst2.size()){
	            dist = Math.min(dist, Math.abs(lst1.get(idx1) - lst2.get(idx2)));
	            if (dist == 0){
	                return 0;
	            }
	            if (lst1.get(idx1) <= lst2.get(idx2)){
	                idx1 ++;
	            }else{
	                idx2 ++;   
	            }
	        }
	        return dist;
	    }
	}
}
