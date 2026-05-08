package edu.norcocollege.cis18b.week7.mini01;

import java.util.List;

/*
 * ============================================
 * SELF-CHECK ANSWERS - MINI 01
 * ============================================
 * 
 * QUESTION 1: Why is process isolation stronger than thread isolation?
 * 
 * ANSWER: Process isolation uses separate memory address spaces.
 * - Each process has its own heap, stack, and resources
 * - If one process crashes, other processes continue running
 * - Example: Chrome browser uses separate processes per tab
 * 
 * Threads share the same heap memory within one process:
 * - All threads can access the same objects
 * - A crash in one thread crashes the entire process
 * - Example: Video game - audio thread crash kills the whole game
 * 
 * ============================================
 * 
 * QUESTION 2: Distinguish "can overlap" from "must run in parallel"
 * 
 * ANSWER:
 * - "Can overlap" (CONCURRENCY): Tasks are structured to make progress
 *   during overlapping time periods, but may run on a single CPU core
 *   through time-slicing. Example: Auto-saving while typing.
 * 
 * - "Must run in parallel" (PARALLELISM): Tasks actually execute at the
 *   exact same instant on different CPU cores. Example: Processing large
 *   arrays by splitting work across 8 cores simultaneously.
 * 
 * Key insight: Concurrency is about program structure (designing 
 * independent tasks). Parallelism is about execution hardware.
 * 
 * ============================================
 */

public class ProcessVsThreadClinic {

    public static void main(String[] args) {
        for (Scenario scenario : defaultScenarios()) {
            System.out.println(scenario.name() + " -> " + scenario.recommendation());
        }
    }

    static List<Scenario> defaultScenarios() {
        return List.of(
            new Scenario(
                "student-code-runner",
                "Run untrusted student code with stronger fault isolation.",
                Recommendation.PROCESS,
                "Separate address spaces reduce the blast radius of crashes or unsafe code."
            ),
            new Scenario(
                "gradebook-auto-save",
                "Save updates while the UI remains responsive.",
                Recommendation.THREAD,
                "Shared in-process state makes background saves convenient, but shared data must be protected."
            ),
            new Scenario(
                "sort-single-list-once",
                "Sort one in-memory list and print it immediately.",
                Recommendation.NOT_MEANINGFULLY_CONCURRENT,
                "There is only one task, so concurrency adds complexity without benefit."
            ),
            // ===== EXTENSION CHALLENGE: Real-world scenario =====
            new Scenario(
                "spotify-music-streaming",
                "Download next song while current song plays for gapless playback.",
                Recommendation.THREAD,
                "Download thread and playback thread share a bounded buffer. Requires wait/notify synchronization to prevent playback underrun."
            )
        );
    }

    record Scenario(String name, String description, Recommendation recommendation, String reasoning) {
    }
    
    enum Recommendation {
        PROCESS,
        THREAD,
        NOT_MEANINGFULLY_CONCURRENT
    }
}