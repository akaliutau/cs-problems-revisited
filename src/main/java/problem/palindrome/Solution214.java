package problem.palindrome;

/**
 * Given a string s, you can convert it to a palindrome by adding characters in
 * front of it. Find and return the shortest palindrome you can find by
 * performing this transformation. 
 * 
 * Example 1: Input: s = "aacecaaa" Output: "aaacecaaa" 
 * 
 * 
 * IDEA: 
 * 
 * s=abac
 * 
 * the longest string is caba|abac
 * which can be represented as 2 interlaced parts:
 * 
 * caba      <-- reversed
 *     abac  <-- original
 *     
 * final overlacing
 *     
 * caba      <-- reversed
 *  abac     <-- original
 *     
 * the answer should be c+abac
 * 
 * Basically we have to find the longest interlaced substrings in str1 and str2
 * Use Robin-Karp approach to calculate rolling hash
 * 
 * original: rolling-hash sum for growing string [:i]  
 * reversed: rolling-hash sum for growing string [n-i:n] - total length n - (n -i) = i
 * 
 * 
 * rolling sum for original: 
 *   17^2 * a + 17 * b + a = 17^n * next_letter + prev_sum
 *   
 * rolling sum for reversed: 
 *   17^2 * a + 17 * b + a = 17 * prev_sum + next_letter
 * 
 * 
 */
public class Solution214 {
	
	static final int MOD = 1000000007;

    public String shortestPalindrome(String s) {
        int n = s.length();
      if (n < 2) {
          return s;
      }
      String rev =  new StringBuilder(s).reverse().toString();
      
      if (rev.equals(s)){
          return s;
      }
      int index = 0;
      char[] orig = s.toCharArray();
      long rh_orig = 0, rh_rev = 0, base = 1;
      
      // try to find bigger and bigger substrings
      for (int i = 0; i < n; i++) {
          int c = orig[i] - 'a';
      	  rh_orig = (rh_orig * 26 + c) % MOD;
      	  rh_rev = (rh_rev  +  base * c) % MOD;
          base = (base * 26) % MOD;
          if(rh_rev == rh_orig) {
          	index = i + 1;
       	}
      }

	 return new StringBuilder(s.substring(index)).reverse().append(s).toString();
  }

}
