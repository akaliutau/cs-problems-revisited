package problem.stack;

import java.util.PriorityQueue;

/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * Example:
 * 
 * Input: [2,1,5,6,2,3] Output: 10
 * 
 *     o
 *    oo
 *   ooo
 *  oooo
 * ooooo
 * 
 * lets hist = {width, height}
 * in the beginning:
 * {1,2} {1,1} {1,5} {1,6} {1,2} {1,3}
 * 
 *    o
 *   oo
 *   oo
 *   oo o
 * o oooo
 * oooooo
 * 
 * merge pairs starting from the tallest one (in the current moment)
 * update max rectangle as width X height
 * 
 * {1,2} {1,1} {2,5} {1,2} {1,3}
 * 
 * One of the situation:
 *    o
 *   oo
 *   oo
 *   oo o
 *   oooo
 *  ooooo
 * 
 * Can truncate from left to right, because bars in the middle are the HIGHEST and form the peak
 * 6X1 = 6
 * 5X2 = 10
 * 
 * {1,2} {1,1} {2,5} {1,2} {1,3}
 * {1,2} {1,1} {3,2} {1,3}
 * {1,2} {1,1} {4,2}
 * 
 * after
 * 
 *   o
 *  oo
 * ooo
 * 
 * 
 * 012345
 * 
 * stack:
 * [-1,0]
 * 
 * 
 * IDEA: truncate asc seq of bars
 * 
 * In this approach, we maintain a stack. Initially, we push a -1 onto the stack to mark the end. 
 * We start with the leftmost bar and keep pushing the current bar's index onto the stack until we get two successive numbers in descending order, 
 * i.e. until we get a[i−1] > a[i]. Now, we start popping the numbers from the stack until we hit a number stack[j] on the stack 
 * such that a [stack[j]] <= a[i]. Every time we pop, we find out the area of rectangle formed using the current element as the height of the rectangle
 *  and the difference between the the current element's index pointed to in the original array and the element stack[top−1]−1 as the width
 * 
 * 
 */
public class Solution84 {
	
	class HNode {
		int h;
		int w;
		HNode prev;
		HNode next;
		
		public HNode(int h, int w) {
			this.h = h;
			this.w = w;
		}
        
        @Override
        public String toString(){
            return "[" + h + ", " + w + "]";  
        }
        
	}
	
	boolean merge(HNode main, HNode aux) {
        System.out.println(main.toString() + " + " + aux.toString());
        if (aux.h > 0){
		    main.h = Math.min(main.h, aux.h);
		    main.w ++;
    		aux.prev.next = aux.next;
	    	aux.next.prev = aux.prev;
        }
        return aux.h > 0;
	}
	
	public int largestRectangleArea(int[] heights) {
        if (heights.length == 1){
            return heights[0];
        }
		HNode head = new HNode(-1, 0);
		HNode tail = new HNode(-1, 0);
		head.next = tail;
		tail.prev = head;
		HNode last = head;
		PriorityQueue<HNode> pq = new PriorityQueue<>((o,p) -> p.h - o.h);
		
        int merges = 0;
		for (int h : heights) {
            merges ++;
			HNode node = new HNode(h, 1);
			node.prev = last;
			node.next = last.next;
			last.next = node;
            if (last.prev != null)
			    last.prev.next = node;
			last = node;
			pq.add(node);
		}
        if (merges == 1){
            return pq.poll().h;
        }
		int maxRect = 0;
		while(merges-- > 1) {
			HNode node = pq.poll();
			HNode left = node.prev;
			HNode right = node.next;
			int hLeft = node.prev == head ? -1 : node.prev.h;
			int hRight = node.next == tail ? -1 : node.next.h;
            System.out.println(merges + ":" + hLeft + " + " + hRight);
            boolean merged = true;
            if (hLeft == -1){
			    merged = merge(node, right);
            }else if (hRight == -1){
			    merged = merge(node, left);
            }else if (hLeft <= hRight) {
			    merged = merge(node, right);
			}else {
			    merged = merge(node, left);
			}
			maxRect = Math.max(maxRect, node.h * node.w);
            if (merged)
			    pq.add(node);
		}
		return maxRect;
		
	}

}
