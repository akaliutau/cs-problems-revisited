package com.problems.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * String
 */
public class Solution681 {

	public String nextClosestTime(String time) {
		int cur = 60 * Integer.parseInt(time.substring(0, 2));// time in minutes
		cur += Integer.parseInt(time.substring(3));
		Set<Integer> allowed = new HashSet<>();
		for (char c : time.toCharArray()) {
			if (c != ':') {
				allowed.add(c - '0');
			}
		}

		while (true) {
			cur = (cur + 1) % (24 * 60); // check all 3600 possibilities
			int[] digits = new int[] { (cur / 60) / 10, (cur / 60) % 10, (cur % 60) / 10, (cur % 60) % 10 };
			boolean valid = true;
			for (int d : digits) {
				if (!allowed.contains(d)) {
					valid = false;
					break;
				}
			}
			if (valid) {
				return String.format("%02d:%02d", cur / 60, cur % 60);
			}
		}
	}

	

}
