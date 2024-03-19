package problem.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * Example:
 * 
 * Input: board = 
 * [ 
 * ['o','a','a','n'], 
 * ['e','t','a','e'], 
 * ['i','h','k','r'],
 * ['i','f','l','v'] 
 * ] 
 * words = ["oath","pea","eat","rain"]
 * 
 * Output: ["eat","oath"]
 * 
 * IDEA:
 * 1. Use BOTH sprawling and trie (i.e. traverse 2 structures at the same time)
 * 2. Use trie to fast access all words which start from specific prefix
 * 3. Simultaneously traverse board and trie structure
 * 
 *     b
 *      \
 *       o
 *        \
 *         o
 *          \
 *           k --> [book]
 *            \
 *             i
 *              \
 *               s
 *                \
 *                 h --> [bookish]
 * 
 */
public class Solution212 {

	static class TrieNode {
		Map<Character, TrieNode> children = new HashMap<>();
		String word = null;
	}
	
	void backtracking(int row, int col, char[][] board, TrieNode parent, List<String> result) {
		// initiate the sprawling
		char letter = board[row][col];
		TrieNode currNode = parent.children.get(letter);

		if (currNode.word != null) {
			result.add(currNode.word); // found the word!
			currNode.word = null;// one can use Set for collector instead
		}                        // Note: we continue the process, because there can be other words deeper in the tree

		board[row][col] = '#'; // mark as seen (previous value is in letter; Note, one can use seen map here)

		int[] rowOffset = {-1, 0, 1,  0 };
		int[] colOffset = { 0, 1, 0, -1 };
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

		board[row][col] = letter;// restore letter for backtracking

		if (currNode.children.isEmpty()) {
			parent.children.remove(letter);
		}
	}


	public List<String> findWords(char[][] board, String[] words) {

		// Construct the Trie
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode node = root;
			for (Character letter : word.toCharArray()) {
				node = node.children.computeIfAbsent(letter, k -> new TrieNode());
			}
			node.word = word; // store words in Trie
		}

		List<String> result = new ArrayList<>();

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




}
