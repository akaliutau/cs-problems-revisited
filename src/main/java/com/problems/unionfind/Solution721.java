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
 * Input: accounts = 
 * [
 * ["John", "johnsmith@mail.com", "john00@mail.com"],
 * ["John", "johnnybravo@mail.com"], 
 * ["John", "johnsmith@mail.com", "john_newyork@mail.com"], 
 * ["Mary", "mary@mail.com"]] 
 * 
 * Output: [
 * ["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"], 
 * ["John", "johnnybravo@mail.com"], 
 * ["Mary", "mary@mail.com"]] 
 * 
 * Explanation: The first
 * and third John's are the same person as they have the common email
 * "johnsmith@mail.com". The second John and Mary are different people as none
 * of their email addresses are used by other accounts. 
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
		Map<String, String> emailToName = new HashMap<>();
		Map<String, Integer> emailToID = new HashMap<>();
		int id = 0;
		for (List<String> account : accounts) {
			String name = "";
			for (String email : account) {
				if (name == "") {
					name = email;
					continue;
				}
				emailToName.put(email, name);
				if (!emailToID.containsKey(email)) {
					emailToID.put(email, id++);
				}
				g.union(emailToID.get(account.get(1)), emailToID.get(email));
			}
		}

		Map<Integer, List<String>> ans = new HashMap<>();
		for (String email : emailToName.keySet()) {
			int index = g.find(emailToID.get(email));
			ans.computeIfAbsent(index, x -> new ArrayList<>()).add(email);
		}
		for (List<String> component : ans.values()) {
			Collections.sort(component);
			component.add(0, emailToName.get(component.get(0)));
		}
		return new ArrayList<>(ans.values());
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
