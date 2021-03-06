package com.problems.design;

import java.util.Iterator;

/**
 * Implement iterator
 * 
 * IDEA:
 * 1) use 2 vars: hasNextCached & nextCached - to return value and state of iterator
 * 2) change state only on next() invoking
 */
public class Solution284 {

    class PeekingIterator<T> implements Iterator<T> {
        Iterator<T> iterator = null;
        T nextCached = null;
        boolean hasNextCached = false;
        boolean updated = false;
        
        public PeekingIterator(Iterator<T> iterator) {
            this.iterator = iterator;       
        }
        
        // Returns the next element in the iteration without advancing the iterator.
        public T peek() {
            updateState(); 
            return nextCached;
        }
        
        @Override
        public T next() {
            updateState();
            updated = false;
            return nextCached;
        }
        
        @Override
        public boolean hasNext() {
            updateState();
            return hasNextCached;
        }
        
        private void updateState(){
            if (!updated){
                nextCached = null;
                hasNextCached = iterator.hasNext();
                if (hasNextCached){
                    nextCached = iterator.next();
                }
                updated = true;
            }
        }
    }
	

}
