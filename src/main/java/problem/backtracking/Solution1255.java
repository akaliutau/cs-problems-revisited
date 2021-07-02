package problem.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given a list of words, list of single letters (might be repeating) and score
 * of every character.
 * 
 * Return the maximum score of any valid set of words formed by using the given
 * letters (words[i] cannot be used two or more times).
 * 
 * It is not necessary to use all characters in letters and each letter can only
 * be used once. Score of letters 'a', 'b', 'c', ... ,'z' is given by score[0],
 * score[1], ... , score[25] respectively.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: words = ["dog","cat","dad","good"], letters =
 * ["a","a","c","d","d","d","g","o","o"], score =
 * [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0] Output: 23 
 * 
 * Explanation:
 * Score a=1, c=9, d=5, g=3, o=2 Given letters, we can form the words "dad"
 * (5+1+5) and "good" (3+2+2+5) with a score of 23. Words "dad" and "dog" only
 * get a score of 21.
 *
 * IDEA:
 * 1. Backtracking, i.e. Brute Force in its pure form - 
 *   on each position make a choice:
 *   a) take current word into set
 *   b) switch to the next one
 * 
 * 
 * 
 */
public class Solution1255 {
	
	static class Word {
		int score;
		int[] letters;
		
		public Word(String word, int[] sc) {
			this.letters = new int[26];
			for (char c : word.toCharArray()) {
				letters[c - 'a'] ++;
				score += sc[c - 'a'];
			}
		}
		
		public boolean canUse(int[] haveLetters) {
			for (int i = 0; i < 26; i++) {
				if (haveLetters[i] < letters[i]) {
					return false;
				}
			}
			return true;
		}
		
		public int[] useWord(int[] haveLetters, int[] cost) {
			int[] res = haveLetters.clone();
			for (int i = 0; i < 26; i++) {
				res[i] = haveLetters[i] -  letters[i];
			}
			return res;
		}
	}
	
	int calcCost(int idx, List<Word> words, int[] haveLetters, int[] score, int[] result) {
		if (idx == words.size()) {
			return 0;
		}
		
		int takeWord  = 0;
		int notTakeWord = 0;
		Word word = words.get(idx);
		if (word.canUse(haveLetters)) {
			takeWord = word.score + calcCost(idx + 1, words, word.useWord(haveLetters, score), score, result);
		}
		notTakeWord = calcCost(idx + 1, words, haveLetters, score, result);
		int maxResult = Math.max(takeWord, notTakeWord);
		result[0] = Math.max(result[0], maxResult);
		return maxResult;
	}
	
	public int maxScoreWords(String[] words, char[] letters, int[] score) {
		int[] haveLetters = new int[26];
		for (char c : letters) {
			haveLetters[c - 'a'] ++;
		}
        int[] maxScore = new int[1];

        List<Word> wordsLi = new ArrayList<>();
        for (String word : words) {
        	wordsLi.add(new Word(word, score));
        }
        calcCost(0, wordsLi, haveLetters, score, maxScore);
        
        return maxScore[0];
    }

}
