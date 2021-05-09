package problem.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * We are given some website visits: the user with name username[i] visited the
 * website website[i] at time timestamp[i].
 * 
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by
 * the time of their visits. (The websites in a 3-sequence are not necessarily
 * distinct.)
 * 
 * Find the 3-sequence visited by the largest number of users. If there is more
 * than one solution, return the lexicographically smallest such 3-sequence.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: username =
 * ["joe","joe","joe","james","james","james","james","mary","mary","mary"],
 * timestamp = [1,2,3,4,5,6,7,8,9,10], 
 * website =
 * ["home","about","career","home","cart","maps","home","home","about","career"]
 * Output: ["home","about","career"] 
 * Explanation: 
 * The tuples in this example are: 
 * ["joe", 1, "home"] 
 * ["joe", 2, "about"] 
 * ["joe", 3, "career"] 
 * ["james", 4, "home"] 
 * ["james", 5, "cart"] 
 * ["james", 6, "maps"] 
 * ["james", 7, "home"]
 * ["mary", 8, "home"] 
 * ["mary", 9, "about"] 
 * ["mary", 10, "career"] 
 * The
 * 3-sequence ("home", "about", "career") was visited at least once by 2 users.
 * The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
 * The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
 * The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
 * The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 * 
 * IDEA: generate smart statistics
 * 1) generate all possible ordered permutations of visited sites, save freqs for each combination
 * 2) pick up the combination with the max freq
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

	// return true, if tuple key is Lexicographically Smaller than result
	boolean isLexicographicallySmaller(List<String> key, List<String> result) {
		for (int i = 0; i < key.size(); i++) {
			if (!key.get(i).equals(result.get(i))) {
				return key.get(i).compareTo(result.get(i)) < 0;
			}
		}
		return false;
	}

	void helper(int currIdx, List<String> websites, List<String> currList, Map<List<String>, Integer> freqMap,
			Set<List<String>> visited) {
		
		if (currList.size() == 3) {// ready to add to statistics
			if (visited.contains(currList)) {
				return;
			}
			List<String> copy = new ArrayList<>(currList);
			visited.add(copy);
			freqMap.compute(copy, (k,v) -> v == null ? 1 : v + 1);
			return;
		}
		// else continue chaining and backtracking
		for (int i = currIdx; i < websites.size(); i++) {
			currList.add(websites.get(i));// add candidate
			helper(i + 1, websites, currList, freqMap, visited);
			currList.remove(currList.size() - 1);
		}
	}

	public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		int n = username.length;
		// generate tuples from the original data
		List<Visit> dataList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			dataList.add(new Visit(username[i], timestamp[i], website[i]));
		}
		Comparator<Visit> byTimestamp = (o, p) -> Integer.compare(o.timestamp, p.timestamp);
		Collections.sort(dataList, byTimestamp);// needed due to "sorted in ascending order by the time of their visits" condition
		
		// group by username
		Map<String, List<String>> nameToWebsiteVisit = new HashMap<>();
		for (Visit visit : dataList) {
			nameToWebsiteVisit.computeIfAbsent(visit.username, k -> new ArrayList<>()).add(visit.website);
		}

		// backtracking, found all possible patterns
		Map<List<String>, Integer> freqMap = new HashMap<>();
		for (List<String> list : nameToWebsiteVisit.values()) {// for each user
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


}
