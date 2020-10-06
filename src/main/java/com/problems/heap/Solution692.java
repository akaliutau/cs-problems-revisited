package com.problems.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * 
 */
public class Solution692 {
	static class Elem {
	public int freq = 0;
	public String word;
	
	public Elem(String word) {
		this.word = word;
	}

}


public List<String> topKFrequent(String[] words, int k) {
	int n = words.length;
	Comparator<Elem> byFreq = (o,p) -> Integer.compare(p.freq, o.freq);
	Comparator<Elem> byWord = (o,p) -> o.word.compareToIgnoreCase(p.word);
	PriorityQueue<Elem> queue = new PriorityQueue<>(n, byFreq.thenComparing(byWord));
	Map<String,Elem> map = new HashMap<>();
	for (String word : words) {
		if (!map.containsKey(word)) {
			map.put(word, new Elem(word));
		}
		map.get(word).freq ++;
	}
	queue.addAll(map.values());
	
	List<String> res = new ArrayList<>();
	for (int i = 0; i < k; i++) {
		res.add(queue.poll().word);
	}
	return res;

}

}
