package com.problems.graph;

/**
 * In a town, there are N people labelled from 1 to N. There is a rumor that one
 * of these people is secretly the town judge.
 * 
 * If the town judge exists, then:
 * 
 * The town judge trusts nobody. Everybody (except for the town judge) trusts
 * the town judge. There is exactly one person that satisfies properties 1 and
 * 2. You are given trust, an array of pairs trust[i] = [a, b] representing that
 * the person labelled a trusts the person labelled b.
 * 
 * If the town judge exists and can be identified, return the label of the town
 * judge. Otherwise, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: N = 2, trust = [[1,2]] Output: 2
 * 
 * 
 */
public class Solution997 {

	public int findJudge(int n, int[][] trust) {

		if (trust.length < n - 1) {
			return -1;
		}

		int[] in = new int[n + 1];
		int[] out = new int[n + 1];

		for (int[] relation : trust) {
			out[relation[0]]++;
			in[relation[1]]++;
		}

		for (int i = 1; i <= n; i++) {
			if (in[i] == n - 1 && out[i] == 0) {
				return i;
			}
		}
		return -1;
	}

	

}
