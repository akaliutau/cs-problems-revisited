package problem.misc;

/**
 * Suppose we have a class:
 * 
 * public class Foo { public void first() { print("first"); } public void
 * second() { print("second"); } public void third() { print("third"); } } The
 * same instance of Foo will be passed to three different threads. Thread A will
 * call first(), thread B will call second(), and thread C will call third().
 * Design a mechanism and modify the program to ensure that second() is executed
 * after first(), and third() is executed after second().
 *
 * 
 */
public class Solution1114 {
	static class Foo {
	    
	    private volatile boolean[] printed = new boolean[3];

	    public Foo() {
	        
	    }

	    synchronized public void first(Runnable printFirst) throws InterruptedException {
	        
	        printed[0] = true;
	        printFirst.run();
	        notifyAll();
	    }

	    synchronized public void second(Runnable printSecond) throws InterruptedException {
	        while (!printed[0]){
	            wait();
	        }
	        printed[1] = true;
	        
	        printSecond.run();
	        notifyAll();
	    }

	    synchronized public void third(Runnable printThird) throws InterruptedException {
	        while (!printed[0] || !printed[1]){
	            wait();
	        }
	        printed[2] = true;
	        
	        printThird.run();
	        notifyAll();
	    }
	}
}
