package edu.norcocollege.cis18b.week7.mini05;

/*
 * ============================================
 * SELF-CHECK ANSWERS - MINI 05
 * ============================================
 * 
 * QUESTION 1: Did you re-check the guard condition after waking?
 * 
 * ANSWER: YES - Using `while (!empty) wait()` NOT `if (!empty) wait()`
 * 
 * WHY WHILE IS NECESSARY:
 * 
 * 1. Spurious wakeups: JVM can wake threads without notify()
 * 2. Multiple producers/consumers: Condition may have changed
 * 3. Safety: Thread must re-verify condition before proceeding
 * 
 * ============================================
 * 
 * QUESTION 2: Why is notifyAll safer than notify in beginner code?
 * 
 * ANSWER:
 * 
 * notify():
 * - Wakes ONE randomly chosen waiting thread
 * - Risk: May wake producer when consumer was needed
 * - Can cause deadlock if wrong thread type wakes
 * 
 * notifyAll():
 * - Wakes ALL waiting threads
 * - All re-check condition with while loop
 * - One proceeds, others go back to waiting
 * - Safer for beginners - prevents deadlocks
 * 
 * ============================================
 */

public class OneSlotMessageBuffer {
    private String message;
    private boolean empty = true;

    public synchronized void put(String nextMessage) throws InterruptedException {
        while (!empty) {           // ← while, NOT if!
            wait();                // Wait until buffer is empty
        }
        message = nextMessage;
        empty = false;
        notifyAll();               // Wake all waiting threads
        System.out.println("  [PUT] Added: " + nextMessage);
    }

    public synchronized String take() throws InterruptedException {
        while (empty) {            // ← while, NOT if!
            wait();                // Wait until buffer has data
        }
        String result = message;
        message = null;
        empty = true;
        notifyAll();               // Wake all waiting threads
        System.out.println("  [TAKE] Removed: " + result);
        return result;
    }

    public synchronized boolean isEmpty() {
        return empty;
    }
}