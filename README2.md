# Week 7 Assignment Submission Summary

## Student Information

| Field | Information |------|------|
| **Student Name** | Mahboob Shah Stanikzai |
| **Course** | CIS-18B Java Programming: Advanced | Objects |
| **Assignment** | Week 7 - Concurrency, Threads, Synchronization, Virtual Threads, and Object Pooling |
| **Submission Date**| May 2026 May |

---

## Submission Overview

This document serves as my official submission summary for Week 7. All 8 mini-assignments are complete, tested, and ready for review.

## Completion Status

| Mini | Assignment Name | | Status | Self-Check |
| 01 | Process vs Thread Clinic | ✅ Complete | ✅ Added | ✅ Added (Spotify) |
| 02 | Simple Thread Coordination | ✅ Complete | ✅ Added |
| 03 | Race Condition & Synchronization | ✅ Complete | ✅ Added |
| 04 | Liveness Diagnosis Lab | ✅ Complete | ✅ Added |
| 05 | Guarded Producer-Consumer | ✅ Complete | ✅ Added |
| 06 | ExecutorService & Concurrent Collections | ✅ Complete | ✅ Added |
| 07 | Virtual Threads Request Simulator | ✅ Complete | ✅ Added |
| 08 | Bounded Object Pool Capstone | ✅ Complete | ✅ Added |

**Total: 8/8 Mini-Assignments Complete**
|

---

## Test Results

All tests passed on my local environment:

```bash
$ cd week7-exercises

$ for dir in mini*/; do echo "=== $dir ==="; cd "$dir"; mvn test -q; cd ..; done

=== mini01-process-vs-thread-clinic ===
[INFO] Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS

=== mini02-simple-thread-coordination ===
[INFO] Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS

=== mini03-race-condition-synchronization ===
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS

=== mini04-liveness-diagnosis-lab ===
[INFO] Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS

=== mini05-guarded-block-producer-consumer ===
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS

=== mini06-executorservice-concurrent-collections ===
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS

=== mini07-virtual-threads-request-simulator ===
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS

=== mini08-bounded-object-pool-capstone ===
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```
