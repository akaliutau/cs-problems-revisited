package com.problems.hashtable;

/**
 * Hash table
 */
public class Solution957 {

    public int[] prisonAfterNDays(int[] cells, int day) {
        int n = cells.length;
        int[] res = new int[n];
        int prison = 0;
        for (int i = 0; i < 8; i++) {
            if (cells[i] == 1) {
                prison += 1;
            }
            prison = prison << 1;
        }
        prison = prison >> 1;
        prison = prison & 255;

        int mask = 126;
        boolean[] states = new boolean[256];
        boolean found = false;
        boolean cycle = false;
        int cycleLen = 0;
        int[] history = new int[256];
        history[cycleLen] = prison;
        while (day > 0) {
            int left = prison >> 1;
            int right = prison << 1;
            int newPrison = ((left ^ right) ^ 255) & mask;
            if (states[newPrison]) {
                cycle = true;
                prison = newPrison;
                break;
            }
            states[newPrison] = true;
            if (newPrison == prison) {
                found = true;
                break;
            }
            prison = newPrison;
            history[++cycleLen] = prison;
            day--;

        }
        if (!found && cycle) {
            int offset = day % cycleLen;
            if (cycleLen != 1) {
                if (offset == 0) {
                    offset = cycleLen;
                }
                prison = history[offset];
            }
        }
        for (int i = 7; i > -1; i--) {
            if ((prison & 1) == 1) {
                res[i] = 1;
            }
            prison = prison >> 1;
        }
        return res;
    }

    public static void main(String[] arg) {

        System.out.println("D");

    }

}
