package com.problems.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other
 * 
 */
public class Solution51 {

    boolean isValid(List<Integer> combination, int col, int row) {
        for (int r = 0; r < combination.size(); r++) {
            if (combination.get(r) == col) {
                return false;
            }
            if (Math.abs(combination.get(r) - col) == Math.abs(r - row)) {
                return false;
            }
        }
        return true;
    }

    String getLine(Integer pos, int n) {
        char[] chars = new char[n];
        Arrays.fill(chars, '.');
        chars[pos] = 'Q';
        return String.valueOf(chars);
    }

    void find(int n, int row, List<Integer> combination, List<List<String>> results) {
        if (row == n) {// all rows have been went through
            List<String> found = combination.stream().map(i -> {
                return getLine(i, n);
            }).collect(Collectors.toList());
            results.add(found);
        } else {
            // check all possible positions for combination on the row with index=row
            for (int col = 0; col < n; col++) {
                if (isValid(combination, col, row)) {
                    combination.add(col);
                    find(n, row + 1, combination, results);
                    combination.remove(row);
                }
            }
        }

    }

    public List<List<String>> solveNQueens(int n) {
        List<Integer> combination = new ArrayList<>();
        List<List<String>> results = new ArrayList<>();
        find(n, 0, combination, results);
        return results;
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
