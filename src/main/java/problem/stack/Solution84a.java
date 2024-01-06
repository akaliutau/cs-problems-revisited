package problem.stack;

import java.util.PriorityQueue;

/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * see the optimal solution at
 * Solution84opt
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
 * 
 */
public class Solution84a {
	
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
	
	static void printChain(HNode head) {
		StringBuilder sb = new StringBuilder();
		while (head != null) {
			sb.append(head.toString());
			head = head.next;
		}
		System.out.println(sb.toString());
	}
	
	boolean merge(HNode main, HNode aux, PriorityQueue<HNode> pq) {
        if (aux.h > 0){
		    main.h = Math.min(main.h, aux.h);
		    main.w += aux.w;
    		aux.prev.next = aux.next;
	    	aux.next.prev = aux.prev;
	    	pq.remove(aux);
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
            last.next.prev = node;
			last.next = node;
			last = node;
			pq.add(node);
		}
        if (merges == 1){
            return pq.poll().h;
        }
		int maxRect = 0;
		while(merges-- > 1) {
			HNode node = pq.poll();
            maxRect = Math.max(maxRect, node.h * node.w);
			HNode left = node.prev;
			HNode right = node.next;
			int hLeft = node.prev == head ? -1 : node.prev.h;
			int hRight = node.next == tail ? -1 : node.next.h;
            boolean merged = true;
            if (hLeft == -1){
			    merged = merge(node, right, pq);
            }else if (hRight == -1){
			    merged = merge(node, left, pq);
            }else if (hLeft <= hRight) {
			    merged = merge(node, right, pq);
			}else {
			    merged = merge(node, left, pq);
			}
			maxRect = Math.max(maxRect, node.h * node.w);
            if (merged)
			    pq.add(node);
		}
		return maxRect;
		
	}
	


}
