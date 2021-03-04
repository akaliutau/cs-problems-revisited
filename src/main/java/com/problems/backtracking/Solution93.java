package com.problems.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s containing only digits, return all possible valid IP
 * addresses that can be obtained from s. You can return them in any order.
 * 
 * A valid IP address consists of exactly four integers, each integer is between
 * 0 and 255, separated by single dots and cannot have leading zeros. For
 * example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and
 * "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "25525511135" Output: ["255.255.11.135","255.255.111.35"]
 * 
 * IDEA:
 * 1) try to cut a block from 1 to 3 symbols in length, check its validness, and if all is okay, go to step #2
 * 2) repeat until have non-empty input
 * 
 */
public class Solution93 {

	int n;
	String s;
	LinkedList<String> segments = new LinkedList<>();
	List<String> result = new ArrayList<String>();

	// criteria for validness:
	// 1) length of segment from 1 to 3 inclusive
	// 2) cannot have leading zeros - if it starts from 0, then the length must be 1
	// 3) parsed int must be in [0,255]
	boolean valid(String segment) {
		int m = segment.length();
		if (m > 3)
			return false;
		return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
	}

	void addResult(int cutPos) {
		String segment = s.substring(cutPos + 1, n);
		if (valid(segment)) {
			segments.add(segment);
			result.add(String.join(".", segments));
			segments.removeLast();
		}
	}

	/*
	 * prev : the position of the previous last symbol
	 */
	void backtrack(int prev, int dots) {
		int maxPos = Math.min(n - 1, prev + 4);
		for (int cutPos = prev + 1; cutPos < maxPos; cutPos++) {
			String segment = s.substring(prev + 1, cutPos + 1);
			if (valid(segment)) {
				segments.add(segment); // place dot
				
				if (dots - 1 == 0) // last segment was formed
					addResult(cutPos); // add the solution to result
				else
					backtrack(cutPos, dots - 1); // continue to cut
				
				segments.removeLast(); // remove the last cut for backtracking
			}
		}
	}

	public List<String> restoreIpAddresses(String s) {
		n = s.length();
		this.s = s;
		backtrack(-1, 3);
		return result;
	}


}
