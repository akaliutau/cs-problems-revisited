package problem.hashtable;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 
 * Given a chemical formula (given as a string), return the count of each atom.
 * 
 * The atomic element always starts with an uppercase character, then zero or
 * more lowercase letters, representing the name.
 * 
 * One or more digits representing that element's count may follow if the count
 * is greater than 1. If the count is 1, no digits will follow. For example, H2O
 * and H2O2 are possible, but H1O2 is impossible.
 * 
 * Two formulas concatenated together to produce another formula. For example,
 * H2O2He3Mg4 is also a formula.
 * 
 * A formula placed in parentheses, and a count (optionally added) is also a
 * formula. For example, (H2O2) and (H2O2)3 are formulas.
 * 
 * Given a formula, return the count of all elements as a string in the
 * following form: the first name (in sorted order), followed by its count (if
 * that count is more than 1), followed by the second name (in sorted order),
 * followed by its count (if that count is more than 1), and so on.
 * 
 * 
 * Example 1:
 * 
 * Input: formula = "H2O" Output: "H2O" Explanation: The count of elements are
 * {'H': 2, 'O': 1}.
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
				for (Entry<String, Integer> entry : parse(formula).entrySet()) {
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
			count.compute(key, (k,v) -> v * multiplicity);
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



}
