# Low-Level Design Practice

A self-study repository working through 25 classic low-level design problems, treating each one like a real interview.

The goal isn't to ship 25 finished products — it's to build the design instinct that makes the next unfamiliar problem easier than the last.

## Approach

Every problem follows the same loop. The discipline matters more than any individual problem.

1. **Clarify requirements** — functional + out-of-scope
2. **Identify entities** — nouns become classes
3. **Class design** — state + behavior, top-down
4. **Implement core methods** — happy path, then edges
5. **Verify with a scenario** — trace through tick by tick
6. **Add a variation** — *this is where the real learning happens*

The full roadmap with per-problem prompts, base specs, and variations lives in [`docs/lld-problems.pdf`](docs/lld-problems.pdf).

## Roadmap

### Tier 1 — Foundations
Warm-up problems with small surface area. One or two patterns per problem.
- [ ] Tic-Tac-Toe
- [ ] Snake & Ladder
- [ ] Vending Machine
- [ ] ATM Machine

### Tier 2 — Core Interview Problems
The most-asked problems. Cover most patterns you'll meet in real LLD interviews.
- [ ] Parking Lot
- [ ] Library Management System
- [ ] Splitwise / Expense Sharing
- [ ] Logger / Logging Framework
- [ ] Stack Overflow

### Tier 3 — System-Like Problems
Richer domain models with multiple interacting components.
- [ ] Elevator System
- [ ] Movie Booking System (BookMyShow)
- [ ] Cab Booking (Uber / Ola)
- [ ] Food Delivery (Swiggy / Zomato)
- [ ] Hotel Booking System
- [ ] Online Shopping (Amazon-lite)

### Tier 4 — Concurrency-Heavy & Advanced
Thread safety, locks, and trade-offs. Standard senior-interview territory.
- [ ] Cache (LRU / LFU)
- [ ] Rate Limiter
- [ ] Concurrent Hash Map
- [ ] Task / Job Scheduler
- [ ] Notification System

### Tier 5 — Stretch & Senior-Level
Multiple subsystems, complex domain rules, serious design trade-offs.
- [ ] Chess Game
- [ ] Distributed Key-Value Store
- [ ] URL Shortener
- 
- [ ] Calendar / Meeting Scheduler
- [ ] Stock Exchange / Trading System

## Repo layout

```
low-level-design/
├── docs/
│   └── lld-problems.pdf        # canonical problem specs + variations
├── src/main/java/arten/dev/
│   ├── Dojo.java               # entry point / index
│   ├── a_foundations/          # tier 1
│   ├── b_core/                 # tier 2
│   ├── c_systems/              # tier 3
│   ├── d_concurrency/          # tier 4
│   └── e_stretch/              # tier 5
└── src/test/java/arten/dev/    # mirrors main
```

Each problem lives in its own self-contained package and ships its own `Main` demo.

## Tech stack

- Java 21
- Maven

## Running a problem

Open the project in IntelliJ and run any problem's `Main` class directly. Each problem's demo lives inside its own package (e.g. `arten.dev.a_foundations.tictactoe.Main`).

## Guiding principles

- **Variations matter more than the base.** Anyone can build a base. A design's quality shows when requirements change.
- **Don't force patterns.** Forced patterns are a stronger negative signal than missing ones.
- **Single Responsibility.** "Where does this responsibility belong?" is the most useful question in any review.
- **Tell, don't ask.** Keep rules with the entity that owns the relevant state.
- **Code end-to-end, even if minimal.** A clean small implementation beats a half-built large one.
