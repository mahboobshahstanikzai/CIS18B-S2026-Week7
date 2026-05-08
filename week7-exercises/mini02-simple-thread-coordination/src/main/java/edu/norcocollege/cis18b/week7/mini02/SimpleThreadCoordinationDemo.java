package edu.norcocollege.cis18b.week7.mini02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/*
 * ============================================
 * SELF-CHECK ANSWERS - MINI 02
 * ============================================
 * 
 * QUESTION 1: Did you call start() instead of run()?
 * 
 * ANSWER: YES - In SimpleThreadCoordinationDemo.java line 18:
 * 
 *     for (Thread worker : workers) {
 *         worker.start();  // ← NOT worker.run()
 *     }
 * 
 * Why this matters:
 * - start() creates a NEW thread of execution
 * - run() executes sequentially in the CURRENT thread
 * - Using run() would make workers run one after another, not concurrently
 * 
 * ============================================
 * 
 * QUESTION 2: Did the main thread wait for worker completion?
 * 
 * ANSWER: YES - Lines 24-26:
 * 
 *     for (Thread worker : workers) {
 *         worker.join();  // Main thread blocks until worker finishes
 *     }
 * 
 * join() makes the calling thread wait. Without join(), the main thread
 * would print "All workers completed" immediately while workers were
 * still running, causing incorrect output.
 * 
 * ============================================
 * 
 * NONDETERMINISTIC VS STABLE OUTPUT:
 * 
 * NONDETERMINISTIC (order may vary each run):
 * - Order workers start executing
 * - Order workers complete each step
 * - Console interleaving of worker messages
 * 
 * DETERMINISTIC (always the same):
 * - "All workers launched" before any work begins
 * - Final summary list (sorted alphabetically)
 * - "All workers completed" after all join() calls
 * 
 * ============================================
 */

public class SimpleThreadCoordinationDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Simple Thread Coordination Demo ===\n");
        List<String> completionLog = runDemo();
        System.out.println("\nAll workers completed.");
        System.out.println(completionLog);
    }

    static List<String> runDemo() throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        List<String> completionLog = new ArrayList<>();
        List<Thread> workers = List.of(
            new Thread(new WorkerTask("grade-importer", 3, 20L, startGate, completionLog), "grade-importer"),
            new Thread(new WorkerTask("email-notifier", 2, 30L, startGate, completionLog), "email-notifier"),
            new Thread(new WorkerTask("roster-sync", 4, 15L, startGate, completionLog), "roster-sync")
        );

        for (Thread worker : workers) {
            worker.start();
        }

        System.out.println("All workers launched.");
        startGate.countDown();

        for (Thread worker : workers) {
            worker.join();
        }

        completionLog.sort(Comparator.naturalOrder());
        return completionLog;
    }
}