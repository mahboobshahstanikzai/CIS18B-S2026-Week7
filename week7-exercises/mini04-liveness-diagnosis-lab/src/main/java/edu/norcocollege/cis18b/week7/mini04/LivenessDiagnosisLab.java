package edu.norcocollege.cis18b.week7.mini04;

import java.util.List;

/*
 * ============================================
 * SELF-CHECK ANSWERS - MINI 04
 * ============================================
 * 
 * QUESTION 1: Why can a program be livelocked instead of deadlocked?
 * 
 * ANSWER: 
 * 
 * DEADLOCK:
 * - Threads are BLOCKED waiting forever
 * - No progress possible
 * - Program appears frozen
 * - Example: Two threads each hold a lock and wait for the other's lock
 * 
 * LIVELOCK:
 * - Threads are ACTIVE but making NO PROGRESS
 * - They keep executing but their work is wasted/undone
 * - Program appears busy but never completes
 * - Example: Two people trying to pass in a hallway. Both step left,
 *   then both step right, then both step left again. They keep moving
 *   but never get past each other.
 * 
 * ============================================
 * 
 * QUESTION 2: Did each mitigation address the cause, not just symptom?
 * 
 * ANSWER: YES - Each mitigation targets the root cause:
 * 
 * DEADLOCK (lock-order-conflict):
 *   Cause: Circular wait dependency
 *   Mitigation: Consistent lock ordering
 *   Why it works: Eliminates the circular wait by establishing a fixed
 *   order for acquiring locks (e.g., always lock A then lock B)
 * 
 * STARVATION (always-last-in-line):
 *   Cause: Resource monopolization by higher-priority threads
 *   Mitigation: Fair scheduling, bounded time slices
 *   Why it works: Prevents any single thread from holding resources
 *   indefinitely, giving all threads a chance to run
 * 
 * LIVELOCK (over-polite-retry-loop):
 *   Cause: Identical retry patterns causing collisions
 *   Mitigation: Exponential backoff with random jitter
 *   Why it works: Randomness breaks symmetry, allowing one thread
 *   to proceed while others wait
 * 
 * ============================================
 */

public class LivenessDiagnosisLab {

    public static void main(String[] args) {
        System.out.println("=== Liveness Diagnosis Lab ===\n");
        for (LivenessScenario scenario : scenarios()) {
            System.out.println(scenario.name() + " -> " + scenario.issue());
            System.out.println("  Symptom: " + scenario.symptom());
            System.out.println("  Mitigation: " + scenario.mitigation());
            System.out.println();
        }
    }

    static List<LivenessScenario> scenarios() {
        return List.of(
            new LivenessScenario(
                "lock-order-conflict",
                LivenessIssue.DEADLOCK,
                "Two threads acquire the same pair of locks in opposite order.",
                "Use consistent lock ordering (always acquire lockA then lockB)."
            ),
            new LivenessScenario(
                "always-last-in-line",
                LivenessIssue.STARVATION,
                "A low-priority worker keeps losing access to a shared resource.",
                "Use fair scheduling and bound how long one thread can monopolize the resource."
            ),
            new LivenessScenario(
                "over-polite-retry-loop",
                LivenessIssue.LIVELOCK,
                "Both workers keep backing off and retrying without making progress.",
                "Add exponential backoff with random jitter to break symmetry."
            )
        );
    }
}