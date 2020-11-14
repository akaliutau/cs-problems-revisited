package com.problems.unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Union Find
 * 
 * 
 * Input: accounts = [ ["John", "johnsmith@mail.com", "john00@mail.com"],
 * ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com",
 * "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
 * 
 * Output: [ ["John", "john00@mail.com", "john_newyork@mail.com",
 * "johnsmith@mail.com"], ["John", "johnnybravo@mail.com"], ["Mary",
 * "mary@mail.com"]]
 * 
 * Explanation: The first and third John's are the same person as they have the
 * common email "johnsmith@mail.com". The second John and Mary are different
 * people as none of their email addresses are used by other accounts.
 * 
 * IDEA:
 * Union set structure will contain info about all sets
 * 
 */
public class Solution721 {

	static class Graph {
		int[] parent;

		public Graph() {
			parent = new int[10001];
			for (int i = 0; i <= 10000; ++i)
				parent[i] = i;
		}

		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}

		public void union(int x, int y) {
			parent[find(x)] = find(y);
		}
	}

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Graph g = new Graph();
		Map<String, String> emailToName = new HashMap<>();// needed to restore name
		Map<String, Integer> emailToID = new HashMap<>();// replace unique string with unique int id
		int uuid = 0;
		for (List<String> account : accounts) {
			String name = account.get(0);
			for (int i = 1; i < account.size(); i++) {
				String email = account.get(i);
				emailToName.put(email, name);
				if (!emailToID.containsKey(email)) {
					emailToID.put(email, uuid ++);
				}
				g.union(emailToID.get(account.get(1)), emailToID.get(email));// if sets intersected, then union them
			}
		}

		Map<Integer, List<String>> grouppedByParent = new HashMap<>();// map parent id => all emails in set
		for (String email : emailToName.keySet()) {
			int index = g.find(emailToID.get(email));
			grouppedByParent.computeIfAbsent(index, x -> new ArrayList<>()).add(email);// group by parent of set
		}
		for (List<String> component : grouppedByParent.values()) {
			Collections.sort(component);
			component.add(0, emailToName.get(component.get(0)));// sort and attach name at pos=0
		}
		return new ArrayList<>(grouppedByParent.values());
	}



}
