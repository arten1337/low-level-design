# Low-Level Design Practice

A personal learning repo for working through classic **Low-Level Design (LLD)** problems from the ground up — with Claude as a structured tutor.

The goal isn't to ship 25 polished applications. It's to build the design instinct that makes the *next* unfamiliar problem easier than the last.

---

## What this is

A self-paced LLD curriculum built around solving real interview problems (Tic-Tac-Toe, Vending Machine, Parking Lot, Splitwise, Elevator System, BookMyShow, …). Every problem is approached the same way:

1. Pin down requirements
2. Identify entities and responsibilities
3. Sketch a class design
4. Code the core methods
5. Trace a real scenario through the code
6. Apply one small variation that stresses the design
7. Write down what was learned (`NOTES.md` per problem)

The repo is **driven by Claude** through a `CLAUDE.md` file at the root, which acts as a tutoring contract — defining how Claude should teach concepts, scaffold problems, and step back as the learner gains confidence.

---

## The approach

Three phases, progressing as skill builds:

- **Tutor Mode** — concepts taught before they're applied; problems solved collaboratively, step by step. The starting phase.
- **Coach Mode** — the learner attempts each step solo (timeboxed), then Claude critiques. Begins after the first three problems are comfortable.
- **Interviewer Mode** — full mock-interview conditions with vague prompts, pushback, and curveballs. Final phase, used for interview rehearsal.

Six foundation lessons (SOLID, composition over inheritance, the three patterns you'll use most, requirement-reading) come *before* any problem. Patterns are introduced only when a problem genuinely needs one — never bolted on for points.

---

## How to use this repo

If you want to follow the same curriculum:

1. Fork or clone the repo.
2. Open it in Claude Code, or upload `CLAUDE.md` to a Claude project on claude.ai. The file tells Claude how to coach you.
3. Start a session with something like *"let's begin"* — Claude will read the **Progress Tracker** at the bottom of `CLAUDE.md` and propose your next step.
4. Work through the foundation lessons, then problems, in order.
5. After each problem, write a short `NOTES.md` in that problem's package. The notes are most of the value.

You don't need to edit `CLAUDE.md` — Claude will update the Progress Tracker for you as you go. Customize it only if your priorities differ (different language, different problem list, different target companies).

---

## Tech stack

- **Java 8** — strict. Lambdas, streams, `Optional`, functional interfaces, `java.time`. No Java 9+ features (no `var`, `record`, `sealed`, switch expressions, text blocks, `List.of`, etc.).
- **Maven** — `pom.xml` set to source/target 1.8.
- **JUnit** for tests (encouraged once a problem's base is working, not required upfront).

---

## Repo layout

```
low-level-design/
├── CLAUDE.md                  ← tutoring contract (drives Claude's behaviour)
├── README.md
├── pom.xml
├── docs/
│   ├── lld-problems.pdf       ← optional canonical problem prompts
│   └── lld-supplement.md      ← optional company-tuned variations
└── src/
    ├── main/java/arten/dev/
    │   ├── Dojo.java          ← entry point / index (not a problem)
    │   ├── a_foundations/     tier 1 — tictactoe, vendingmachine, logger, atm
    │   ├── b_core/            tier 2 — parkinglot, splitwise, library, ...
    │   ├── c_systems/         tier 3 — elevator, bookmyshow, ...
    │   ├── d_concurrency/     tier 4 — cache, ratelimiter, jobscheduler, ...
    │   └── e_stretch/         tier 5 — chess, calendar, ...
    └── test/java/arten/dev/   (mirrors main)
```

Each problem lives in its own self-contained package and ships a `Main` class for hand-tracing demos. Problems do not depend on each other.

The `a_`–`e_` prefix on tier folders is intentional — it makes the file tree sort by difficulty in the IDE.

---

## Curriculum

### Foundations (do these before any problem)
1. Single Responsibility Principle
2. Open/Closed + Tell-Don't-Ask
3. Liskov + Interface Segregation + Dependency Inversion
4. Composition over Inheritance
5. Three patterns you'll use constantly: **Strategy, State, Factory**
6. Reading requirements like a designer

### Problems

**Tier 1 — Foundations**
- [ ] Tic-Tac-Toe
- [ ] Vending Machine
- [ ] Logger

**Tier 2 — Core**
- [ ] Parking Lot
- [ ] Splitwise
- [ ] ATM

**Tier 3 — Systems**
- [ ] Elevator System
- [ ] BookMyShow (movie ticket booking)
- [ ] Library Management System

**Tier 4 — Concurrency**
- [ ] LRU Cache
- [ ] Rate Limiter
- [ ] Job Scheduler

**Tier 5 — Stretch**
- [ ] Chess
- [ ] Notification System
- [ ] Calendar / Meeting Scheduler

The list is curated to teach distinct concepts, not to drill volume. Once these are done, harder problems and variations build interview fluency.

---

## Running a problem

Open the project in your IDE of choice. Each problem has its own `Main` class — run it directly:

```
arten.dev.a_foundations.tictactoe.Main
arten.dev.a_foundations.vendingmachine.Main
...
```

No build steps, no servers, no setup beyond Maven importing the project.

---

## Guiding principles

- **Variations matter more than the base.** Anyone can build a base. A design's quality shows when requirements change. If a small variation forces a half-rewrite, that's the lesson.
- **Don't force patterns.** Forced patterns are a stronger negative signal in interviews than missing ones. A pattern needs to solve a real tension in *this* design.
- **Single Responsibility.** "Where does this responsibility belong?" is the most useful question in any design review.
- **Tell, don't ask.** Keep behaviour with the data it operates on.
- **Code end-to-end, even if minimal.** A clean working tic-tac-toe beats a half-built parking lot.

---

## Status

This is a learning repo, updated as problems are worked through. Code here is for studying, not production. Issues, forks, and suggestions welcome — but expect the structure to keep evolving as the curriculum is refined.

---

## Acknowledgements

- Problem list inspired by the standard set of LLD interview questions used across product companies.
- Tutoring methodology shaped by the [hellointerview](https://www.hellointerview.com/) LLD delivery framework and assorted Refactoring Guru / *Head First Design Patterns* material.
