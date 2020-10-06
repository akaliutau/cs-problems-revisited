package com.problems.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://en.wikipedia.org/wiki/Least_frequently_used
 * 
 */
public class Solution460 {

    class LFUCache {

        // node with statistics
        class Node {
            int key;
            int value;
            int count;

            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            // attach to this node the next one
            void chain(Node node) {
                node.prev = this;
                next = node;
            }

            // remove from 2-linked list
            void remove() {
                if (next != null) {
                    next.prev = prev;
                }

                if (prev != null) {
                    prev.next = next;
                }
            }
        }

        int maxSize;
        int size;

        Map<Integer, LinkedList<Node>> counter;// for statistics
        Map<Integer, Node> nodeMap;// for fast access

        Node head;
        Node tail;

        int minCount;

        public LFUCache(int capacity) {
            maxSize = capacity;
            minCount = 0;
            size = 0;

            counter = new HashMap<>();
            nodeMap = new HashMap<>();

            head = null;
        }

        public int get(int key) {
            if (!nodeMap.containsKey(key)) {
                return -1;
            }

            Node node = nodeMap.get(key);

            LinkedList<Node> nodes = counter.get(node.count);
            nodes.remove(node);

            if (nodes.size() > 0) {
                Node node1 = nodes.peekFirst();
                node.chain(node1);
                if (node == tail) {
                    tail = node1;
                }
            }

            node.count += 1;
            if (!counter.containsKey(node.count)) {
                counter.put(node.count, new LinkedList<>());
            }

            LinkedList<Node> updatesNodes = counter.get(node.count);
            if (updatesNodes.size() > 0) {
                Node node1 = updatesNodes.peekLast();
                node1.chain(node);
                if (node1 == tail) {
                    tail = node;
                }
            }

            updatesNodes.offer(node);

            return node.value;
        }

        public void put(int key, int value) {
            if (maxSize == 0) {
                return;
            }

            if (nodeMap.containsKey(key)) {
                get(key);

                Node node = nodeMap.get(key);
                node.value = value;
                return;
            }

            if (size == maxSize) {
                int count = tail.count;
                Node node = counter.get(count).pollFirst();
                remove(node);

                nodeMap.remove(node.key);

                counter.get(node.count).remove(node);
            }

            Node node = addToTail(key, value);

            nodeMap.put(key, node);

            if (!counter.containsKey(node.count)) {
                counter.put(node.count, new LinkedList<>());
            }

            counter.get(node.count).offer(node);
        }

        void remove(Node node) {
            node.remove();

            if (head == node) {
                head = head.next;
                if (head != null) {
                    head.prev = null;
                }
            }

            if (tail == node) {
                tail = node.prev;
            }

            size--;
        }

        Node addToTail(int key, int value) {
            Node node = new Node(key, value);

            if (head == null) {
                head = node;
                tail = head;
            } else {
                tail.chain(node);
                tail = node;
            }

            size++;

            return node;
        }
    }

    public static void main(String[] arg) {

        System.out.println("D");

    }

}
