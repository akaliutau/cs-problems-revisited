package com.problems.design;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Solution19 {

    static class MinStack {
        private List<Integer> stack = new ArrayList<>();
        private Integer minElem = Integer.MAX_VALUE;

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            stack.add(x);
            if (x < minElem) {
                minElem = x;
            }
        }

        public void pop() {
            int elem = top();
            stack.remove(stack.size() - 1);
            if (elem == minElem) {
                updateMin();
            }
        }

        public int top() {
            return stack.get(stack.size() - 1);
        }

        public int getMin() {
            return minElem;
        }

        private void updateMin() {
            minElem = Integer.MAX_VALUE;
            for (int i = 0; i < stack.size(); i++) {
                minElem = minElem > stack.get(i) ? stack.get(i) : minElem;
            }
        }
    }

    public static void main(String[] arg) {

        System.out.println();

    }

}
