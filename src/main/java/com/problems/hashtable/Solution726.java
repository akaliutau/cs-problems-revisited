package com.problems.hashtable;

import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * HashTable
 * 
 */
public class Solution726 {

	int i;

	int parseNumber(String formula) {
		int start = i;
		int n = formula.length();
		while (i < n && Character.isDigit(formula.charAt(i))) {
			i++;
		}
		int num = start < i ? Integer.parseInt(formula.substring(start, i)) : 1;
		return num;
	}

	Map<String, Integer> parse(String formula) {
		int n = formula.length();
		Map<String, Integer> count = new TreeMap<>();
		while (i < n && formula.charAt(i) != ')') {
			if (formula.charAt(i) == '(') {
				i++;
				// copy stat from inside ()
				for (Map.Entry<String, Integer> entry : parse(formula).entrySet()) {
					count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue());
				}
			} else {
				// parse name and number
				int iStart = i++;
				while (i < n && Character.isLowerCase(formula.charAt(i))) {
					i++;
				}
				String name = formula.substring(iStart, i);

				int multiplicity = parseNumber(formula);
				count.put(name, count.getOrDefault(name, 0) + multiplicity);
			}
		}
		// end of formula or )
		++i;
		// parse number if exists and multiply all in ()
		int multiplicity = parseNumber(formula);
		for (String key : count.keySet()) {
			count.put(key, count.get(key) * multiplicity);
		}
		return count;
	}

	public String countOfAtoms(String formula) {
		StringBuilder ans = new StringBuilder();
		i = 0;
		Map<String, Integer> count = parse(formula);
		for (String name : count.keySet()) {
			ans.append(name);
			int multiplicity = count.get(name);
			if (multiplicity > 1)
				ans.append("" + multiplicity);
		}
		return new String(ans);
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
