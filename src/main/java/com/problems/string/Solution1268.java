package com.problems.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * String
 */
public class Solution1268 {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        int n = products.length;
        int len = searchWord.length();
        String word = searchWord.substring(0, 1);
        List<String> found = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            found.add(products[i]);
        }
        Collections.sort(found);
        for (int i = 0; i < len; i++) {
            word = searchWord.substring(0, i + 1);
            List<String> subList = new ArrayList<>();
            for (String foundWord : found) {
                if (foundWord.startsWith(word)) {
                    subList.add(foundWord);
                }
            }
            if (subList.isEmpty()) {
                res.add(subList);
                continue;
            }
            int counter = Math.min(3, subList.size());
            res.add(subList.subList(0, counter));
            found = subList;
        }

        return res;

    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
