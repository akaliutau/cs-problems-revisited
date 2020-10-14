package com.problems.misc;

/**
 * Array
 */
public class Solution227 {

    static class Relation {

        public boolean knows(int a, int b) {
            return true;
        }
    }

    static class Solver extends Relation {

        private int numberOfPeople;

        public int findCelebrity(int n) {
            numberOfPeople = n;
            int celebrityCandidate = 0;
            for (int i = 0; i < n; i++) {
                if (knows(celebrityCandidate, i)) {
                    celebrityCandidate = i;
                }
            }
            if (isCelebrity(celebrityCandidate)) {
                return celebrityCandidate;
            }
            return -1;
        }

        private boolean isCelebrity(int i) {
            for (int j = 0; j < numberOfPeople; j++) {
                if (i == j)
                    continue; // Don't ask if they know themselves.
                if (knows(i, j) || !knows(j, i)) {
                    return false;
                }
            }
            return true;
        }

    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
