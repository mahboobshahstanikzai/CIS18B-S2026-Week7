package edu.norcocollege.cis18b.week7.mini04;

public enum LivenessIssue {
    DEADLOCK,    // Threads blocked forever, waiting on each other
    STARVATION,  // Thread cannot gain access to resources
    LIVELOCK     // Threads active but making no progress
}