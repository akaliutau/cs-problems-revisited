package problem.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You have a keypad with 9 buttons, numbered from 1 to 9, each mapped to lowercase English letters.
 * You can choose which characters each button is matched to as long as:
 *
 * All 26 lowercase English letters are mapped to.
 * Each character is mapped to by exactly 1 button.
 * Each button maps to at most 3 characters.
 * To type the first character matched to a button, you press the button once.
 * To type the second character, you press the button twice, and so on.
 *
 * Given a string s, return the minimum number of keypresses needed to type s using your keypad.
 *
 * Note that the characters mapped to by each button, and the order they are mapped in cannot be changed.
 *
 */

public class Solution2268 {

    class Letter {
        int count = 0;
        char let;
        int pressCount = 1;
    }

    public int minimumKeypresses(String s) {
        Letter[] freq = new Letter[26];
        for (int i = 0; i < 26; i++){
            freq[i] = new Letter();
            freq[i].let = (char)(i + 'a');
        }
        for (char c : s.toCharArray()){
            freq[c - 'a'].count ++;
        }
        Arrays.sort(freq, (o, p) -> p.count - o.count);
        int key = 1;
        int presses = 1;
        Map<Character, Letter> m = new HashMap<>();
        for (int i = 0; i < 26; i++){
            freq[i].pressCount = presses;
            key ++;
            if (key > 9){
                key = 1;
                presses ++;
            }
            m.put(freq[i].let, freq[i]);
        }
        int ans = 0;
        for (char c: s.toCharArray()){
            ans += m.get(c).pressCount;
        }
        return ans;

    }
}

