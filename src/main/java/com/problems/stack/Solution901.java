package com.problems.stack;

import java.util.Stack;

/**
 * Stack
 * 
 * 11, 3, 9, 5, 6, 4, 7
 * 
 * 1   1  2  1  2  1  4 :: span
 * 
 */
public class Solution901 {
    
    static class StockSpanner {
        Stack<Integer> prices, days;

        public StockSpanner() {
            prices = new Stack<>();
            days = new Stack<>();
        }

        public int next(int price) {
            int w = 1;
            while (!prices.isEmpty() && prices.peek() <= price) {
                prices.pop();
                w += days.pop();// days = how many days price persisted
            }

            prices.push(price);
            days.push(w);
            return w;
        }
    }


	public static void main(String[] arg) {
	    
	    StockSpanner s = new StockSpanner();
	        s.next(100);
	        s.next(80);
	        s.next(60);
	        s.next(70);
	        s.next(60);
	        s.next(75);
	        s.next(85);
		
		System.out.println();

	}

}
