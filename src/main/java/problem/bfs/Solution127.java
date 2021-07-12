package problem.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * the length of shortest transformation sequence from beginWord to endWord,
 * such that:
 * 
 * Only one letter can be changed at a time. Each transformed word must exist in
 * the word list. Note:
 * 
 * Return 0 if there is no such transformation sequence. All words have the same
 * length. All words contain only lowercase alphabetic characters. You may
 * assume no duplicates in the word list. You may assume beginWord and endWord
 * are non-empty and are not the same. Example 1:
 * 
 * Input: beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * 
 * Output: 5
 * 
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" ->
 * "dog" -> "cog", return its length 5.
 * 
 * Input: beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * 
 * 
 * Output: [ 
 * ["hit","hot","dot","dog","cog"], 
 * ["hit","hot","lot","log","cog"] ]
 * 
 * IDEA:
 * use graph to build a valid path, but decrease the number of possible variants using:
 * 1) filter on possible routes
 * 2) bfs to find the shortest path
 * 
 */
public class Solution127 {
	class Node {
		String word;
		Node parent;

		public Node(String word, Node parent) {
			this.word = word;
			this.parent = parent;
		}
	}

	int minDist = Integer.MAX_VALUE; // looking for only shortest paths

	// used to find all possible words which can be built from word by replacing only 1 symbol
	// "?og" => [dog, cog, log]
	// "d?g" => dog
	// "do?" => dog

	List<String> getSeeds(String word, Map<Integer, List<String>> graph, Set<String> visited) {
		Set<String> ans = new HashSet<>();
		char[] orig = word.toCharArray();
		for (int i = 0; i < word.length(); i++) {
			char[] key = orig.clone();
			key[i] = '?';
			int hashCode = Arrays.hashCode(key);
			if (graph.containsKey(hashCode)) {
				for (String w : graph.get(hashCode)) {
					if (!visited.contains(w)) {
						ans.add(w);
					}
				}
			}
		}
		return new ArrayList<>(ans);
	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		// fast check
		List<List<String>> ans = new ArrayList<>();
		Set<String> words = new HashSet<>(wordList);
		if (!words.contains(endWord)) {
			return ans;
		}
		
		
		// building graph
		// "?og" => [dog, cog, log]
		// "d?g" => dog
		// "do?" => dog

		Map<Integer, List<String>> graph = new HashMap<>();
		for (String word : wordList) {// iterate through all words we have
			char[] orig = word.toCharArray();
			for (int i = 0; i < word.length(); i++) {
				char[] key = orig.clone();
				key[i] = '?';
				int hashCode = Arrays.hashCode(key);
				graph.computeIfAbsent(hashCode, k -> new ArrayList<>()).add(word);
			}
		}

		Node node = new Node(beginWord, null);// start with start word without parent
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		Set<String> visited = new HashSet<>();// to avoid loops
		int dist = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				node = q.poll();
				String word = node.word;
				if (word.equals(endWord) && dist <= minDist) {// reached the end, ignore sequences which are too long
					minDist = dist;
					List<String> path = new ArrayList<>();
					// backtracing to restore the path
					while (node != null) {
						path.add(0, node.word);
						node = node.parent;
					}
					// filter out only the shortest paths
					if (ans.isEmpty() || ans.get(0).size() == path.size()) {
						ans.add(path);// works also for the duplicates
					} else {
						if (ans.get(0).size() > path.size()) {// the very first path we found earlier is too long, then drop all and reset
							ans = new ArrayList<>();
							ans.add(path);
						}else {
							// ignore because the found path is longer
						}
					}
				}
				// mark processed word as visited
				visited.add(word);
				// get variations and link them to the current node
				List<String> linked = getSeeds(word, graph, visited);
				for (String w : linked) {
					q.add(new Node(w, node));
				}
			}
			dist++;
		}
		return ans;
	}



}
