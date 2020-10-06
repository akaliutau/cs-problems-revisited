package com.problems.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Least Recently Used (LRU) cache.
 * https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU
 * 
 * 
 */
public class Solution146 {

	public class LRUCache {

		class Node {
			int key;
			int value;
			Node prev;
			Node next;
		}

		private void addNode(Node node) {
			node.prev = head;
			node.next = head.next;

			head.next.prev = node;
			head.next = node;
		}

		private void removeNode(Node node) {
			Node prev = node.prev;
			Node next = node.next;

			prev.next = next;
			next.prev = prev;
		}

		// composite op
		// the same as add, but for existing node
		private void bubbleUp(Node node) {
			removeNode(node);
			addNode(node);
		}

		private Node removeOldest() {
			Node res = tail.prev;
			removeNode(res);
			return res;
		}

		private Map<Integer, Node> cache = new HashMap<>();
		private int size;
		private int capacity;
		private Node head, tail;

		public LRUCache(int capacity) {
			size = 0;
			this.capacity = capacity;

			head = new Node();
			tail = new Node();

			head.next = tail;
			tail.prev = head;
		}

		public int get(int key) {
			Node node = cache.get(key);
			if (node == null)
				return -1;

			// move the accessed node to the head;
			bubbleUp(node);

			return node.value;
		}

		public void put(int key, int value) {
			Node node = cache.get(key);

			if (node == null) {
				Node newNode = new Node();
				newNode.key = key;
				newNode.value = value;

				cache.put(key, newNode);
				addNode(newNode);

				++size;

				if (size > capacity) {
					// pop the tail
					Node tail = removeOldest();// oldest elem
					cache.remove(tail.key);
					--size;
				}
			} else {
				// just update the value.
				node.value = value;
				bubbleUp(node);
			}
		}
	}

	public static void main(String[] arg) {
		System.out.println(true);
	}

}
