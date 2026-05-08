package edu.norcocollege.cis18b.week7.mini03;

// SAFE - Using synchronized keyword
public class SynchronizedCounter {
    private int value;

    // synchronized creates a critical section
    // Only ONE thread can execute this method at a time
    public synchronized void increment() {
        value++;  // Now atomic because lock prevents interleaving
    }
    
    public synchronized int getValue() {
        return value;
    }
}