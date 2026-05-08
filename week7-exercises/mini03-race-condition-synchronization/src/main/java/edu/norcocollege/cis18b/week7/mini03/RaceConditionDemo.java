package edu.norcocollege.cis18b.week7.mini03;

/*
 * ============================================
 * SELF-CHECK ANSWERS - MINI 03
 * ============================================
 * 
 * QUESTION 1: Why is value++ not atomic?
 * 
 * ANSWER: The statement `value++` compiles to THREE separate operations:
 * 
 *     1. READ the current value from memory
 *     2. ADD 1 to the value
 *     3. WRITE the new value back to memory
 * 
 * Two threads can interleave these operations:
 * 
 *   Thread A reads value=5
 *   Thread B reads value=5 (before A writes)
 *   Thread A computes 6 and writes
 *   Thread B computes 6 and writes
 * 
 * Result: Incremented twice but value increased by only 1 (from 5 to 6).
 * This is called a "lost update" race condition.
 * 
 * ============================================
 * 
 * QUESTION 2: Did synchronized path always return the expected total?
 * 
 * ANSWER: YES - The synchronized keyword creates a critical section.
 * 
 * When increment() is synchronized:
 * - Only ONE thread can execute increment() at a time
 * - The lock is held for the entire read-modify-write sequence
 * - Other threads block until the lock is released
 * 
 * Expected total: 8 threads × 25,000 increments = 200,000
 * The synchronized counter ALWAYS produces exactly 200,000.
 * 
 * ============================================
 */

public class RaceConditionDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Race Condition Demo ===\n");
        
        int threadCount = 8;
        int incrementsPerThread = 25_000;
        int expected = threadCount * incrementsPerThread;

        int unsafe = RaceConditionHarness.runUnsafeTrial(threadCount, incrementsPerThread);
        int safe = RaceConditionHarness.runSynchronizedTrial(threadCount, incrementsPerThread);
        int atomic = RaceConditionHarness.runAtomicTrial(threadCount, incrementsPerThread);
        
        System.out.println("Expected count: " + expected);
        System.out.println("Unsafe count: " + unsafe + " (WRONG - varies each run!)");
        System.out.println("Synchronized count: " + safe + " (CORRECT)");
        System.out.println("Atomic count: " + atomic + " (CORRECT)");
        System.out.println();
        System.out.println("The unsafe count is nondeterministic due to race conditions.");
        System.out.println("Synchronized and Atomic versions always produce correct results.");
    }
}