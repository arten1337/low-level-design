# Low-Level Design Practice Repo

Personal learning repo for low-level design (object-oriented design) problems. Java 21, Maven. The user (Akhand) is self-studying LLD by implementing 25 classic problems from a curated roadmap.

This is a **practice repo for interview prep and design intuition** — not production code. Optimize for learning, not for shipping.

## Canonical problem sources

**`docs/lld-problems.pdf`** — the original 25-problem roadmap. Source of truth for every base problem. For each problem it contains:
- A one-line interview prompt
- A base spec (core requirements for the first pass)
- A list of variations (changes that stress-test the design — *the real learning lives here*)

**`docs/lld-supplement.md`** — supplement tuned for the user's target companies (FAANG, Flipkart, Walmart, Confluent, Zomato, Swiggy, PhonePe). Adds: machine-coding round mode, Java code-quality checklist, 5 additional problems (Pizza Ordering, Snake Game, In-memory DB, Producer-Consumer, Pub/Sub), and a Tier 5 refocus proposal that drops/rewrites HLD-leaning problems.

When starting a problem, read its entry in the PDF (or supplement) before doing anything else. Do not improvise requirements.

**This repo is LLD-only.** If a problem leans toward HLD / system design (sharding, replication, distributed consensus, load-balancing across nodes), either skip it or focus only on the LLD slice (object model, single-process design, in-memory data structures).

## Per-problem workflow — solo attempt, then critique

The user has explicitly chosen this methodology for every problem. **Honour it.**

1. **User picks a problem.**
2. **User spends 30–45 min alone** on the loop below — requirements, design, code. Claude is *not* in this phase.
3. **User brings the attempt back** — requirements list, class design, and code.
4. **Claude critiques** — missed requirements first, then SOLID issues, then "where would a different pattern have been cleaner?"
5. **User refactors.** This is where most of the learning happens.
6. **Claude prompts for `NOTES.md`.** Once the refactor settles, ask the user what they want captured (e.g. what was hard, which pattern surprised them, anything they'd do differently, the critique points worth remembering). Write it to `NOTES.md` in the problem's package. Keep it short — a paragraph or a few bullets.
7. **User picks one variation** from `docs/lld-problems.pdf` (or the supplement) and repeats the loop. After each variation is done, prompt for a `NOTES.md` *append* with what the variation taught.

The loop the user follows alone in step 2:
1. Clarify requirements (~5 min) — functional + out-of-scope
2. Identify entities (~3 min) — nouns become classes
3. Class design (~15 min) — state + behavior, top-down
4. Implement core methods (~10 min) — happy path, then edges
5. Verify with a scenario (~2 min) — trace through tick by tick

**Do not preempt.** When the user announces a problem, *do not* start asking clarifying questions, sketching entities, or suggesting classes. Acknowledge and wait. The exception is if they explicitly ask for help mid-attempt ("I'm stuck on X") — then engage.

## Design philosophy

- **Variations matter more than the base.** Anyone can build a base. A design's quality shows when requirements change. If a small variation forces a half-rewrite, that's the lesson.
- **Don't force patterns.** Forced patterns are a stronger negative signal than missing ones in real interviews. Suggest a pattern only when it solves a real tension in the design.
- **Single Responsibility Principle.** "Where does this responsibility belong?" is the most useful question in LLD review.
- **Tell, don't ask.** Keep rules with the entity that owns the relevant state.
- **Code end-to-end, even if minimal.** A clean working tic-tac-toe beats a half-built parking lot.

## Code structure

Each problem is a self-contained package under a tier folder. Tests mirror the structure under `src/test/java/`.

```
src/main/java/arten/dev/
├── Dojo.java          ← entry point / index (not a problem)
├── a_foundations/     tier 1 — tictactoe, snakeladder, vendingmachine, atm
├── b_core/            tier 2 — parkinglot, library, splitwise, logger, stackoverflow
├── c_systems/         tier 3 — elevator, bookmyshow, cabbooking, fooddelivery, hotelbooking, shopping
├── d_concurrency/     tier 4 — cache, ratelimiter, concurrenthashmap, jobscheduler, notification
└── e_stretch/         tier 5 — chess, kvstore, urlshortener, calendar, stockexchange
```

The `a_`–`e_` prefix is intentional — it makes tier folders sort by difficulty in the IDE/file tree instead of alphabetically.

Problems must not depend on each other. Each package owns its own domain.

## Style notes

- Java 21 — use modern features (records, sealed interfaces, pattern-matching switch, text blocks) when they make the design cleaner, not as decoration.
- Don't generate scaffolding for problems that haven't been started.
- Don't add abstractions, error handling, or framework hooks beyond what the current problem teaches.
- Comments only when the *why* is non-obvious. Well-named identifiers are documentation.
- A `Main` class per problem is fine for hand-tracing; JUnit tests are encouraged for verification (step 5) once base is working.

## Recommended starting order (from the roadmap)

Tic-Tac-Toe → Vending Machine → Splitwise → Logger → Elevator. After these, most major patterns (State, Strategy, Chain of Responsibility, basic concurrency) have been touched at least once.

## What Claude should do when the user picks a problem

1. Acknowledge the choice. **Do not** ask clarifying questions or start sketching design — that work is the user's to do alone.
2. Read the problem's entry in `docs/lld-problems.pdf` so you're ready to critique against the actual base spec when they return.
3. Wait. When the user brings their attempt, ask for all three artifacts if they're not all present: requirements list, class design, code.
4. Critique in this order: missed requirements → SOLID violations → pattern alternatives. Be specific enough that the user can refactor against your feedback.
5. After refactor, **prompt the user for `NOTES.md` content** — ask what they want captured (suggested fields: what was hard, which pattern surprised them, what they'd do differently, the most important critique to remember). Write the result to `NOTES.md` inside the problem's package (e.g. `a_foundations/tictactoe/NOTES.md`). Keep it short.
6. Then prompt the user to pick exactly one variation from the PDF (or the supplement) and repeat the loop. Append to `NOTES.md` after each variation with what it taught.