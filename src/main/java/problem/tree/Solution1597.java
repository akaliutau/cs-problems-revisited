package problem.tree;

/**
 * 
 * A binary expression tree is a kind of binary tree used to represent
 * arithmetic expressions. Each node of a binary expression tree has either zero
 * or two children. Leaf nodes (nodes with 0 children) correspond to operands
 * (numbers), and internal nodes (nodes with 2 children) correspond to the
 * operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/'
 * (division).
 * 
 * For each internal node with operator o, the infix expression that it
 * represents is (A o B), where A is the expression the left subtree represents
 * and B is the expression the right subtree represents.
 * 
 * You are given a string s, an infix expression containing operands, the
 * operators described above, and parentheses '(' and ')'.
 * 
 * Return any valid binary expression tree, which its in-order traversal
 * reproduces s after omitting the parenthesis from it (see examples below).
 * 
 * Please note that order of operations applies in s. That is, expressions in
 * parentheses are evaluated first, and multiplication and division happen
 * before addition and subtraction.
 * 
 * Operands must also appear in the same order in both s and the in-order
 * traversal of the tree. 
 * 
 * 2 + 3 * (3 -1)
 * 
 * IDEA:
 * 
 * find sign as a split point, and partition equation string around this point:
 * 
 *             2 + 3 * (3 - 1)
 *                      |   |
 *                     invoke recursively 
 *           
 *             2 + 3 * (3 - 1)
 *               |   
 *              \|/
 *                 
 *              [+]   
 *             /   \
 *            2     3 * (3 - 1)
 *            
 *  Note: order of operators is important!
 */
public class Solution1597 {
	
	static class Node {
		char val;
		Node left;
		Node right;

		Node() {
			this.val = ' ';
		}

		Node(char val) {
			this.val = val;
		}

		Node(char val, Node left, Node right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
	
	Node build(char[] str, int left, int right) {
        if (left > right) return null;

        if (left == right) {
            return new Node(str[left]);
        }

       int p = 0, idx = left, min = left, div = left, mul = left;
        
        while (idx < right) {
            if (str[idx] == '(') {
                p++;
            } else if (str[idx] == ')') {
                p--;
            } else if (str[idx] == '+' && p == 0) {
				// found the root location
                break;
            } else if (str[idx] == '-' && p == 0) {
                min = idx;
            } else if (str[idx] == '*' && p == 0) {
                mul = idx;
            } else if (str[idx] == '/' && p == 0) {
                div = idx;
            }
            idx++;
        }
        Node root = null;
		
        if (idx > left && idx < right) {
            root = new Node(str[idx]);
        } else if (min > left && min < right) {
            root = new Node(str[min]);
            idx = min;
        } else if (mul > left && mul < right) {
            root = new Node(str[mul]);
            idx = mul;
        } else if (div > left && div < right) {
            root = new Node(str[div]);
            idx = div;
        } else if (str[left] == '(' && str[right] == ')') {
            return build(str, left + 1, right - 1);
        }
		
        root.left = build(str, left, idx - 1);
        root.right = build(str, idx + 1, right);
        
        return root;
    }

	public Node expTree(String s) {
        return build(s.toCharArray(), 0, s.length() - 1);
    }

}
