package problem.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string text, we are allowed to swap two of the characters in the
 * string. Find the length of the longest substring with repeated characters.
 * 
 * Example 1:
 * 
 * Input: text = "ababa" Output: 3
 * 
 * IDEA: 
 * 
 * use 3 different available strategies to get the max result, they are:
 * 1) find a max block
 * 2) [N1N] - pattern
 * 3) [NabcN] - pattern pair of non-neighbor blocks
 * 
 * 
 * perform an effective merge
 *
 */
public class Solution1156 {
	
	static class Block {
		char letter;
		int freq;
		
		public Block(char letter, int freq) {
			this.letter = letter;
			this.freq = freq;
		}
	}
	
	public int maxRepOpt1(String text) {
		int n = text.length();
		List<Block> stat = new ArrayList<>();
		char[] letters = text.toCharArray();

		Block curBlock = new Block(letters[0], 1 );
		for (int i = 1; i < n; i++) {
			char ch = letters[i];
			if (ch == curBlock.letter) {
				curBlock.freq ++;
			} else {
				stat.add(curBlock);
				curBlock = new Block (ch, 1);
			}
		}
		stat.add(curBlock);
		
		if (stat.size() == 1) {
			return stat.get(0).freq;
		}
		if (stat.size() == 2) {
			return Math.max(stat.get(0).freq, stat.get(1).freq);
		}

		int maxSize = 1;
		int[] freq = new int[26];// statistics on blocks

		// strategy 1 -  just find a max block, works on string consisting from unique letters
		// 
		//      abbbnyyyyyyyyyyyyyc
		//
		for (Block block : stat) {
			maxSize = Math.max(maxSize, block.freq);
			freq[block.letter - 'a'] ++;
		}

		// strategy 2 - swap 1 letter with the end of M, gain = N + M
		//                        must be the same letters
		// aaabaaa              /                         \
		// a b a         [solid block] [gap in 1 letter] [solid block]
		// 3 1 3                 N             1              M
		// 

		for (int i = 1; i < stat.size() - 1; i++) {
			Block left = stat.get(i - 1);
			Block center = stat.get(i);
			Block right = stat.get(i + 1);
			if (left.letter != right.letter)
				continue;
			if (center.freq == 1) {
				int length = freq[left.letter - 'a'] <= 2 ? left.freq + right.freq : left.freq + right.freq + 1;// we can take letter from 3rd block OR use one of the existing one
				maxSize = Math.max(maxSize, length);
			} else if (center.freq > 1) {
				maxSize = Math.max(maxSize, Math.max(left.freq, right.freq) + 1);
			}
		}
		// strategy 3
		// pair of non-neighbor blocks
		// ashgdaaaaaa
		//  \  /
		//  will gain in max_size + 1
		
		for (int l = 0; l < 26; l++) {
			if (freq[l] >= 2) {
				for (Block block : stat) {// find the block with letter l
					if ((block.letter - 'a') == l) {
						maxSize = Math.max(maxSize, block.freq + 1);
						break;
					}
				}
			}
		}

		return maxSize;

	}
}
