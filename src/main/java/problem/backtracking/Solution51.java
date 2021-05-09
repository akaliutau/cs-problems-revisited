package problem.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other
 * 
 * IDEA:
 * 1) generate all possible configurations, filtering out all invalid ones on the early stages
 * 
 */
public class Solution51 {

    boolean isValid(List<Integer> configuration, int col, int row) {
        for (int r = 0; r < configuration.size(); r++) {// check all rows
            if (configuration.get(r) == col) {
                return false;
            }
            if (Math.abs(configuration.get(r) - col) == Math.abs(r - row)) {
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

    // each configuration is a set of column's numbers, and the line in list define the ROW on which this column is chosen
    void backtrack(int n, int row, List<Integer> configuration, List<List<String>> results) {
    	// first, check the exit condition
        if (row == n) {// all rows have been went through
            List<String> found = configuration.stream().map(i -> {
                return getLine(i, n);
            }).collect(Collectors.toList());
            results.add(found);
            return;
        } 

        // check all possible positions for configuration on the row with index=row
        for (int col = 0; col < n; col++) {
            if (isValid(configuration, col, row)) {
                configuration.add(col);
                backtrack(n, row + 1, configuration, results);
                configuration.remove(row);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<Integer> configuration = new ArrayList<>();
        List<List<String>> results = new ArrayList<>();
        backtrack(n, 0, configuration, results);
        return results;
    }

 

}
