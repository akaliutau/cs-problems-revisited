package com.problems.design;

import java.util.ArrayList;
import java.util.List;

/**
 * Design a queue that supports push and pop operations in the front, middle,
 * and back.
 * 
 * Implement the FrontMiddleBack class:
 * 
 * FrontMiddleBack() Initializes the queue. void pushFront(int val) Adds val to
 * the front of the queue. void pushMiddle(int val) Adds val to the middle of
 * the queue. void pushBack(int val) Adds val to the back of the queue. int
 * popFront() Removes the front element of the queue and returns it. If the
 * queue is empty, return -1. int popMiddle() Removes the middle element of the
 * queue and returns it. If the queue is empty, return -1. int popBack() Removes
 * the back element of the queue and returns it. If the queue is empty, return
 * -1. Notice that when there are two middle position choices, the operation is
 * performed on the frontmost middle position choice. For example:
 * 
 * Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
 * Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4,
 * 5, 6]
 * 
 * 
 * 
 */
public class Solution5560 {

	class FrontMiddleBackQueue {

		private List<Integer> lst = new ArrayList<>();

		public FrontMiddleBackQueue() {

		}

		public void pushFront(int val) {
			if (lst.size() > 0) {
				lst.add(0, val);
			} else {
				lst.add(val);
			}
		}

		public void pushMiddle(int val) {
			int size = lst.size();
			lst.add(size / 2, val);
		}

		public void pushBack(int val) {
			lst.add(val);
		}

		public int popFront() {
			return lst.size() == 0 ? -1 : lst.remove(0);
		}

		public int popMiddle() {
			int size = lst.size();
			if (size == 0) {
				return -1;
			}
			return size % 2 == 0 ? lst.remove(size / 2 - 1) : lst.remove(size / 2);
		}

		public int popBack() {
			int size = lst.size();
			if (size == 0) {
				return -1;
			}
			return lst.remove(size - 1);
		}
	}

	

}
