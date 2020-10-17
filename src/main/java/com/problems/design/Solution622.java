package com.problems.design;

/**
 * Design your implementation of the circular queue. The circular queue is a
 * linear data structure in which the operations are performed based on FIFO
 * (First In First Out) principle and the last position is connected back to the
 * first position to make a circle. It is also called "Ring Buffer".
 * 
 * One of the benefits of the circular queue is that we can make use of the
 * spaces in front of the queue. In a normal queue, once the queue becomes full,
 * we cannot insert the next element even if there is a space in front of the
 * queue. But using the circular queue, we can use the space to store new
 * values.
 * 
 * Your implementation should support following operations:
 * 
 * MyCircularQueue(k): Constructor, set the size of the queue to be k. 
 * Front: Get the front item from the queue. If the queue is empty, return -1. 
 * Rear:  Get the last item from the queue. If the queue is empty, return -1.
 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful. 
 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful. 
 * isEmpty(): Checks whether the circular queue is empty or not. 
 * isFull(): Checks whether the circular queue is full or not.
 * 
 * 
 */
public class Solution622 {

	static class MyCircularQueue {

		private int[] queue;
		private int headIndex;
		private int count;
		private int capacity;

		/** Initialize your data structure here. Set the size of the queue to be k. */
		public MyCircularQueue(int k) {
			capacity = k;
			queue = new int[k];
			headIndex = 0;
			count = 0;
		}

		/**
		 * Insert an element into the circular queue. Return true if the operation is
		 * successful.
		 */
		public boolean enQueue(int value) {
			if (count == capacity)
				return false;
			queue[(headIndex + count) % capacity] = value;
			count += 1;
			return true;
		}

		/**
		 * Delete an element from the circular queue. Return true if the operation is
		 * successful.
		 */
		public boolean deQueue() {
			if (count == 0)
				return false;
			headIndex = (headIndex + 1) % capacity;
			count -= 1;
			return true;
		}

		/** Get the front item from the queue. */
		public int Front() {
			if (count == 0) {
				return -1;
			}
			return queue[headIndex];
		}

		/** Get the last item from the queue. */
		public int Rear() {
			if (count == 0) {
				return -1;
			}
			int tailIndex = (headIndex + count - 1) % capacity;
			return queue[tailIndex];
		}

		/** Checks whether the circular queue is empty or not. */
		public boolean isEmpty() {
			return count == 0;
		}

		/** Checks whether the circular queue is full or not. */
		public boolean isFull() {
			return count == capacity;
		}
	}

	public static void main(String[] arg) {

		System.out.println();

	}

}
