package problem.string;

/**
 * 
 * Write a function to find the longest common prefix string amongst an array of
 * strings.
 * 
 * If there is no common prefix, return an empty string "".
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: strs = ["flower","flow","flight"] Output: "fl"
 * 
 * IDEA:
 *  take the 1st word as a prefix, then decrease the length each time the divergence is found
 *  NOTE: the order is not important, as well as the relative length of the 1st word 
 */
public class Solution14 {

	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {//decrease prefix until match found
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty())
					return "";// early exit
			}
		}
		return prefix;
	}
}
