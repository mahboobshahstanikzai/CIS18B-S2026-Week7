package edu.norcocollege.cis18b.week7.mini03;

// UNSAFE - Race condition present!
// DO NOT USE IN PRODUCTION CODE
public class UnsafeCounter {
    private int value;

    // This method is NOT thread-safe!
    // Multiple threads can interleave the read-add-write operations
    public void increment() {
        value++;  // NOT ATOMIC: read, add, write can interleave
    }
    
    public int getValue() {
        return value;
    }
}