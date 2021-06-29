package problem.math;

/**
 * 
 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t
 * (t concatenated with itself 1 or more times)
 * 
 * Given two strings str1 and str2, return the largest string x such that x
 * divides both str1 and str2.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: str1 = "ABCABC", str2 = "ABC" Output: "ABC" 
 * 
 * Example 2:
 * 
 * Input: str1 = "ABABAB", str2 = "ABAB" Output: "AB"
 * 
 * IDEA:
 * 
 */
public class Solution1071 {
	
	public String gcdOfStrings(String str1, String str2) {
	       if (str2.length() > str1.length()) {
	            return gcdOfStrings(str2, str1);
	        }else if (str1.equals(str2)) {
	            return str1;
	        }else if (str1.startsWith(str2)) {
	            return gcdOfStrings(str1.substring(str2.length()), str2);
	        }

	        return ""; 
	    }
}
