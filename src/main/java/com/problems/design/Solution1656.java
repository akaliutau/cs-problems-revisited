package com.problems.design;

import java.util.ArrayList;
import java.util.List;

/**
 * There is a stream of n (id, value) pairs arriving in an arbitrary order,
 * where id is an integer between 1 and n and value is a string. No two pairs
 * have the same id.
 * 
 * Design a stream that returns the values in increasing order of their IDs by
 * returning a chunk (list) of values after each insertion. The concatenation of
 * all the chunks should result in a list of the sorted values.
 * 
 * Implement the OrderedStream class:
 * 
 * OrderedStream(int n) Constructs the stream to take n values. String[]
 * insert(int id, String value) Inserts the pair (id, value) into the stream,
 * then returns the largest possible chunk of currently inserted values that
 * appear next in the order.
 * 
 * Input ["OrderedStream", "insert", "insert", "insert", "insert", "insert"]
 * [[5], [3, "ccccc"], [1, "aaaaa"], [2, "bbbbb"], [5, "eeeee"], [4, "ddddd"]]
 * Output [null, [], ["aaaaa"], ["bbbbb", "ccccc"], [], ["ddddd", "eeeee"]]
 * 
 * Explanation // Note that the values ordered by ID is ["aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"]. 
 * OrderedStream os = new OrderedStream(5);
 * os.insert(3, "ccccc"); // Inserts (3, "ccccc"), returns []. 
 * os.insert(1, "aaaaa"); // Inserts (1, "aaaaa"), returns ["aaaaa"]. 
 * os.insert(2, "bbbbb"); // Inserts (2, "bbbbb"), returns ["bbbbb", "ccccc"]. 
 * os.insert(5, "eeeee"); // Inserts (5, "eeeee"), returns []. 
 * os.insert(4, "ddddd"); // Inserts (4, "ddddd"), returns ["ddddd", "eeeee"]. 
 * 
 * Concatentating all the chunks returned: 
 * [] + ["aaaaa"] + ["bbbbb", "ccccc"] + [] + ["ddddd", "eeeee"] = 
 * ["aaaaa", "bbbbb", "ccccc", "ddddd", "eeeee"] 
 * The resulting order is the same as the order above.
 * 
 * IDEA:
 * 1)
 * 2) update ptr if and only if it coincides with (id-1)
 * 
 */
public class Solution1656 {
	
	class OrderedStream {
		
		private int ptr = 0;
		private String[] chunks;

	    public OrderedStream(int n) {
	    	chunks = new String[n];
	    }
	    
	    public List<String> insert(int id, String value) {
	    	chunks[id - 1] = value;
	    	List<String> res = new ArrayList<>();
	    	// this code is used to generate a chunk if the pointer to the last saved str coincides with the current save position
	    	if (id - 1 == ptr) {
	    		while (ptr < chunks.length && chunks[ptr] != null) {
	    			res.add(chunks[ptr++]);
	    		}
	    	}
	    	return res;
	    }
	}
	

}
