package problem.design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Implement the StreamChecker class as follows: StreamChecker(words):
 * Constructor, init the data structure with the given words. query(letter):
 * returns true if and only if for some k >= 1, the last k characters queried
 * (in order from oldest to newest, including this letter just queried) spell
 * one of the words in the given list. 
 * 
 * Example: StreamChecker streamChecker =
 * new StreamChecker(["cd","f","kl"]); // init the dictionary.
 * streamChecker.query('a'); // return false 
 * streamChecker.query('b'); // return false 
 * streamChecker.query('c'); // return false 
 * streamChecker.query('d'); // return true, because 'cd' is in the wordlist 
 * streamChecker.query('e'); // return false 
 * streamChecker.query('f'); // return true, because 'f' is in the wordlist 
 * streamChecker.query('g'); // return false 
 * streamChecker.query('h'); // return false 
 * streamChecker.query('i'); // return false
 * streamChecker.query('j'); // return false 
 * streamChecker.query('k'); // return false 
 * streamChecker.query('l'); // return true, because 'kl' is in the wordlist
 * 
 * IDEA: reverse and use trie
 */
public class Solution1032 {

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean word = false;
    }

    class StreamChecker {
        TrieNode trie = new TrieNode();
        Deque<Character> stream = new ArrayDeque<>();// used as a memory buffer to hold the last n = max(all words) letters

        public StreamChecker(String[] words) {
            for (String word : words) {
                TrieNode node = trie;
                String reversedWord = new StringBuilder(word).reverse().toString();
                for (char ch : reversedWord.toCharArray()) {
                    node = node.children.computeIfAbsent(ch, key -> new TrieNode());
                }
                node.word = true;
            }
        }

        public boolean query(char letter) {
            stream.addFirst(letter);

            TrieNode node = trie;
            for (char ch : stream) {
                if (node.word) {
                    return true;
                }
                if (!node.children.containsKey(ch)) {
                    return false;
                }
                node = node.children.get(ch);
            }
            return node.word;
        }
    }

}
