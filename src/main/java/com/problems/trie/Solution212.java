package com.problems.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trie
 */
public class Solution212 {

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;

        public TrieNode() {

        }
    }

    public List<String> findWords(char[][] board, String[] words) {

        // Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word; // store words in Trie
        }

        ArrayList<String> result = new ArrayList<>();
        
        // Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, board, root, result);
                }
            }
        }

        return result;
    }

    private void backtracking(int row, int col, char[][] board, TrieNode parent, ArrayList<String> result) {
        Character letter = board[row][col];
        TrieNode currNode = parent.children.get(letter);

        if (currNode.word != null) {
            result.add(currNode.word);
            currNode.word = null;
        }

        board[row][col] = '#';

        int[] rowOffset = {-1,  0, 1,  0 };
        int[] colOffset = { 0,  1, 0, -1 };
        for (int i = 0; i < 4; ++i) {
            int r = row + rowOffset[i];
            int c = col + colOffset[i];
            if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
                continue;
            }
            // use trie to decide to go further in recursion
            if (currNode.children.containsKey(board[r][c])) {
                backtracking(r, c, board, currNode, result);
            }
        }

        board[row][col] = letter;

        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
