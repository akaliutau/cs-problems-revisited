package problem.string;

/**
 * 
 * You are given a string s consisting only of characters 'a' and 'b'​​​​.
 * 
 * You can delete any number of characters in s to make s balanced. s is
 * balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b'
 * and s[j]= 'a'.
 * 
 * Return the minimum number of deletions needed to make s balanced.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "aababbab" Output: 2 
 * 
 * Explanation: You can either: Delete the
 * characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or Delete
 * the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
 * 
 * Example 2:
 * 
 * Input: s = "bbaaaaabb" Output: 2 
 * Explanation: The only solution is to delete
 * the first two characters.
 * 
 * Heuristic:
 * 
 * try to define and calculate the cost of operation to be perform on the basis of position (= which letter should be operated)
 * 
 * IDEA:
 * find the optimal PIVOT point by calculating cost for each position
 * 
 *          aababbab
 * countA   12233344
 * countB   00112334
 * 
 * a|ababbab
 * aa|babbab
 * aab|abbab
 *          aaabbb
 * countA   123333
 * countB   000123
 * 
 * 
 * 
 */
public class Solution1653 {

    public int minimumDeletions(String s) {
		char[] letters = s.toCharArray();
		int n = letters.length;
        if (n <= 1){
            return 0;
        }

        int totalA = 0;// 4
        int totalB = 0;// 4
        
        for(char c : letters){
            if(c == 'b') {
            	totalB ++;
            }else{
            	totalA ++;
            }
        }
        if (n == totalB || n == totalA){
            return 0;
        }

        int countA = 0;
        int countB = 0;
        int deleted = Integer.MAX_VALUE;

        for(char c : letters){
            int del = countB + (totalA - countA);// 3 2 3
            deleted = Math.min(deleted, del);// 2
            if(c == 'b') {
            	countB ++;
            }else{
            	countA ++;
            }
            del = countB + (totalA - countA);// 3 2 3
            deleted = Math.min(deleted, del);// 2
        }     
        return deleted == n ? deleted - 1 : deleted;
        
    }


}
