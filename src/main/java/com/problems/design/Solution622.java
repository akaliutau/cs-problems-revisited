package com.problems.design;

/**
 * Design
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
