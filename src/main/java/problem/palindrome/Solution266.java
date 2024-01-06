package problem.palindrome;

/**
 * 
 * Given a string s, return true if a permutation of the string could form a palindrome.
 */
public class Solution266 {
	 public boolean canPermutePalindrome(String s) {
	        char[] stat = new char[26];
	        
	        for (char c : s.toCharArray()){
	            stat[c - 'a'] ++;
	        }
	        int odds = 0;
	        for (int i = 0; i < 26; i++){
	            if (stat[i] % 2 == 1){
	                odds ++;
	            }
	            if (odds > 1){
	                return false;
	            }
	        }
	        return true;
	    }
}
