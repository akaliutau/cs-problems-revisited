package problem.twopointers;

/**
 * Given two strings S and T, return if they are equal when both are typed into
 * empty text editors. # means a backspace character. Note that after
 * backspacing an empty text, the text will continue empty. 
 * 
 * Example 1: Input: S
 * = "ab#c", T = "ad#c" Output: true Explanation: Both S and T become "ac".
 * 
 * IDEA:
 * straightforward - simulate typing from the end, omitting symbols, using # as command
 */
public class Solution844 {

    static class BackspaceStr {
        int pos;
        int toSkip;
        char[] str;

        BackspaceStr(String s) {
            pos = s.length() - 1;
            toSkip = 0;
            str = s.toCharArray();
        }

        char getCharAt() {
            return str[pos];
        }

        void next() {
            while (pos >= 0) { // Find position of next possible char in S
                if (str[pos] == '#') {// omit #, count it
                    toSkip++;
                    pos--;
                } else if (toSkip > 0) {// if pressed # 1 or more times, then skip as well
                    toSkip--;
                    pos--;
                } else// found it!
                    break;
            }
        }

    }

    public boolean backspaceCompare(String s, String t) {
        BackspaceStr strS = new BackspaceStr(s);
        BackspaceStr strT = new BackspaceStr(t);

        while (strS.pos >= 0 || strT.pos >= 0) { // While there are chars in S or T

            strS.next();
            strT.next();
            // If two actual characters are different
            if (strS.pos >= 0 && strT.pos >= 0 && strS.getCharAt() != strT.getCharAt())
                return false;
            // If expecting to compare char vs nothing
            if ((strS.pos >= 0) != (strT.pos >= 0))
                return false;
            strS.pos--;
            strT.pos--;
        }
        return true;
    }

 
}
