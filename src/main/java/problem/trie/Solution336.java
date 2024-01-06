package problem.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of unique words, return all the pairs of the distinct indices
 * (i, j) in the given list, so that the concatenation of the two words words[i]
 * + words[j] is a palindrome. 
 * 
 * Example 1: Input: words = ["abcd","dcba","lls","s","sssll", "ka"] 
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * 
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * 
 * IDEA:
 * dcbaabcd = [dc][baab][cd]
 * 
 * [dc]~[cd]
 * [ba]~[ab]
 * 
 * 
 * dcbaabcd = [dcb] + [aabcd]
 *     8        3        5
 *     
 *            [dcb]   [dcbaa]      
 *            prefix  prefix + palindrome   
 *              3       3        from 3
 *          ending=3  ending=5            
 *      prefixLi=[5]  prefixLi=[]           
 * 
 */
public class Solution336 {

    class TrieNode {
        public int ending = -1; // We'll use -1 to mean there's no word ending here.
        public TrieNode[] next = new TrieNode[256];
        public List<Integer> prefixLi = new ArrayList<>();// candidates which have a palindromic tail
    }

    // Is the given string a palindrome after index from?
    boolean isPalindrome(String s, int from) {
        int p1 = from;
        int p2 = s.length() - 1;
        while (p1 < p2) {
            if (s.charAt(p1) != s.charAt(p2))
                return false;
            p1++;
            p2--;
        }
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {

        TrieNode trie = new TrieNode();

        // Build the Trie
        for (int idx = 0; idx < words.length; idx++) {
            String word = words[idx];
            String reversedWord = new StringBuilder(word).reverse().toString();
            TrieNode curLevel = trie;
            for (int j = 0; j < word.length(); j++) {// build chain
                if (isPalindrome(reversedWord, j)) {
                    curLevel.prefixLi.add(idx);
                }
                char c = reversedWord.charAt(j);
                if (curLevel.next[c] == null) {
                    curLevel.next[c] = new TrieNode();
                }
                curLevel = curLevel.next[c];
            }
            curLevel.ending = idx;// full reversed word with id=idx ends here
        }

        // Find pairs
        List<List<Integer>> pairs = new ArrayList<>();
        for (int idx = 0; idx < words.length; idx++) {
            String word = words[idx];
            TrieNode curLevel = trie;
            for (int j = 0; j < word.length(); j++) {
                // on each level: check the case dcb - dcbaa
                if (curLevel.ending != -1 && isPalindrome(word, j)) {
                    pairs.add(Arrays.asList(idx, curLevel.ending));
                }
                // Move down to the next trie level.
                char c = word.charAt(j);
                curLevel = curLevel.next[c];
                if (curLevel == null)
                    break;
            }
            if (curLevel == null)// filter out isolated words
                continue;
            // Check for pairs of case 1. Note the check to prevent non distinct pairs.
            // case abcd - dcba - we went till the end of chain and found a word
            if (curLevel.ending != -1 && curLevel.ending != idx) {
                pairs.add(Arrays.asList(idx, curLevel.ending));
            }
            // case we have a following triple: abc, cba, aaabc
            // trie: 
            // 0 cba,  [] 
            // 1 abc, 
            // 2 cbaaa [2]
            
            for (int other : curLevel.prefixLi) {
                pairs.add(Arrays.asList(idx, other));
            }
        }

        return pairs;
    }
}
