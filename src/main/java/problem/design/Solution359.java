package problem.design;

import java.util.HashMap;
import java.util.Map;

public class Solution359 {
	
	static class Logger {
	    Map<String,Integer> lastPosted = new HashMap<>();
	    
	    public Logger() {
	    }
	    
	    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
	        If this method returns false, the message will not be printed.
	        The timestamp is in seconds granularity. */
	    public boolean shouldPrintMessage(int timestamp, String message) {
	        if (!lastPosted.containsKey(message)){
	            lastPosted.put(message, timestamp);
	            return true;
	        }else{
	            int lastSeenAt = lastPosted.get(message);
	            if (timestamp - lastSeenAt >= 10){
	                lastPosted.put(message, timestamp);
	                return true;
	            }
	        }
	        return false;
	    }
	}

	
}
