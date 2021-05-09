package problem.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * 
 */
public class Solution208 {

    class Trie {
        class Elem {
            public char c;
            public boolean terminal;
            public Map<Character, Elem> elems = new HashMap<>();

            public Elem(char c) {
                this.c = c;
            }

        }

        private Map<Character, Elem> root;

        public Trie() {
            root = new HashMap<>();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Map<Character, Elem> letters = root;
            Elem elem = null;
            for (char c : word.toCharArray()) {
                elem = letters.computeIfAbsent(c, ch -> new Elem(ch));
                letters = elem.elems;
            }
            if (elem != null) {
                elem.terminal = true;
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Map<Character, Elem> cur = root;
            Elem elem = null;
            for (char c : word.toCharArray()) {
                if (!cur.containsKey(c)) {
                    return false;
                }
                elem = cur.get(c);
                cur = cur.get(c).elems;
            }
            if (elem != null) {
                return elem.terminal;
            }
            return false;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Map<Character, Elem> cur = root;
            for (char c : prefix.toCharArray()) {
                if (!cur.containsKey(c)) {
                    return false;
                }
                cur = cur.get(c).elems;
            }
            return true;
        }
    }

 
}
