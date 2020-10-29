package com.problems.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of words and a width maxWidth, format the text such that each
 * line has exactly maxWidth characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly maxWidth characters. Extra spaces between words should be
 * distributed as evenly as possible. If the number of spaces on a line do not
 * divide evenly between words, the empty slots on the left will be assigned
 * more spaces than the slots on the right. For the last line of text, it should
 * be left justified and no extra space is inserted between words.
 */
public class Solution68 {

    static String get(int len) {
        char[] line = new char[len];
        Arrays.fill(line, ' ');
        return new String(line);
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        List<StringBuilder> line = new ArrayList<>();
        int totLetters = 0;
        for (String word : words) {
            int len = word.length(), size = line.size();
            if (totLetters + size + len > maxWidth) {
                int space = maxWidth - totLetters;
                if (size == 1) {
                    res.add(line.get(0).toString() + get(space));
                } else {
                    int spc = space / (size - 1), r = space % (size - 1);
                    for (int i = 0; i < r; i++)
                        line.get(i).append(" ");
                    res.add(String.join(get(spc), line));
                }
                totLetters = 0;
                line.clear();
            }
            totLetters += len;
            line.add(new StringBuilder(word));
        }
        String last = String.join(" ", line);
        res.add(String.format("%-" + maxWidth + "s", last));
        return res;
    }

}
