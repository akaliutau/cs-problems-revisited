package com.problems.model;

import java.util.List;

public class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
       this.children = children;
    }

	@Override
	public String toString() {
		return "Node [val=" + val + ", children=" + children + "]";
	}
     
}
