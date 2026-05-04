# LLD Supplement

This supplements `docs/lld-problems.pdf` with material tuned for the user's target companies (FAANG, Flipkart, Walmart, Confluent, Zomato, Swiggy, PhonePe, and similar). The original PDF remains the source of truth for the 25-problem roadmap; this doc adds:

- A **machine-coding round mode** for timed (60–90 min) interviews
- A **Java code-quality checklist** that interviewers grade you on
- **Five additional problems** that fill gaps relevant to the target companies
- A **Tier 5 refocus proposal** to keep this repo strictly LLD (no HLD drift)

## A note for the beginner

You don't need to do every problem here. The original 25 + the 5 added below = 30 problems is a *target list*, not a checklist. Most candidates clear LLD rounds at these companies after seriously working through 12–15 problems. The discipline of doing them well (attempt → critique → refactor → variation) matters more than the count.

If anything below feels overwhelming, ignore it and circle back when you've finished a few base problems. The supplement is here when you need it, not before.

---

## Machine coding round mode

The 6-step workflow in the PDF is for *learning*. In an actual interview you have 60–90 minutes and must end with **working, runnable code**. Train for this format separately, after the base problem feels routine.

### A 90-minute budget for a typical LLD machine-coding round

```
0–10 min    Clarify requirements with the interviewer
            (functional + out-of-scope; agree on what you're building)

10–25 min   Sketch entities, relationships, and class skeletons
            (write classes/interfaces with method signatures only — no bodies yet)

25–75 min   Implement bottom-up
            (entities first, then services, then a Main demo)

75–85 min   Demo at least one happy-path scenario
            (run Main; show it works)

85–90 min   Add one stretch / variation if time allows
```

### What to cut, in order, when time runs short

1. **Persistence** — keep everything in memory, no DBs.
2. **Concurrency** — single-threaded unless the problem *is* concurrency.
3. **Authentication / users** — assume a userId is passed in.
4. **Validation depth** — basic null checks; skip elaborate input validation.
5. **Logging / metrics** — `System.out.println` is fine.
6. **Optional features** — anything labelled "nice to have" in the spec.

### Beginner-specific tips

- **Get one happy path running by minute 50.** A working small thing beats a half-built large thing — same rule under pressure as in self-study.
- **Don't write tests in the round** unless the interviewer explicitly asks. A `Main` that demos the flow is enough.
- **Speak as you code.** Interviewers grade your reasoning, not just the artifact. *"I'm using Strategy here so a different fee policy is a one-class change"* gets credit.
- **If you get stuck, say what you'd do.** *"Given more time I'd extract X into Y"* is a positive signal — silence is not.

---

## Java code-quality checklist for LLD rounds

These are the small things that separate "passes" from "strong hire". **Internalise them in this order — don't try to apply all 15 from your first problem.**

### Start with these 5 (apply from problem 1)

1. **Names.** Classes are nouns (`ParkingSpot`), methods are verbs (`assignSpot`). Avoid abbreviations (`assignSpotForVeh` → `assignSpot`).
2. **One responsibility per class.** If a class is doing two things, split it. If you can't name a class without "and" in it, split it.
3. **Private + final fields by default.** Only loosen when you must.
4. **No magic numbers.** Extract `private static final int MAX_RETRIES = 3;`.
5. **Throw specific exceptions.** `SpotUnavailableException` — not `RuntimeException("no spot")`.

### Add these next 5 once the first set is automatic

6. **Composition over inheritance.** Prefer "has-a" over "is-a"; almost always the right call.
7. **Enums (or sealed interfaces) for fixed type hierarchies.** `VehicleType.CAR` over `int = 1`.
8. **`equals` and `hashCode` together** — or neither. Use Java 21 `record` for value objects; you get both for free.
9. **Defensive copies** for mutable collections you accept or return. `return List.copyOf(items);`.
10. **Builder pattern** for objects with more than ~3 constructor parameters. Bonus points for a fluent API.

### Add these for senior-leaning rounds

11. **`Optional` instead of `null` returns.** `Optional<Spot> findSpot(...)`.
12. **Immutability for value objects.** Records do this for you in Java 21.
13. **Interface segregation.** Split `Readable` from `Writable` if a client needs only one.
14. **Tell, don't ask.** `account.debit(amount)` — not `if (account.balance >= amount) account.balance -= amount`.
15. **Dependency injection via constructor.** No `new` of collaborators inside business logic.

---

## Additional problems

Same format as the original PDF (interview prompt, base, variations). Suggested tier and insertion point given for each.

### A) Pizza Ordering (Decorator + Builder)
*Suggested placement: Tier 1 (Foundations), right after Vending Machine.*

> "Design an ordering system for a pizza shop where customers customise pizzas with toppings and sizes."

**Base.**
A pizza has a base type (Margherita, Farmhouse, etc.), a size (small / medium / large), and any number of toppings (cheese, olives, mushrooms, jalapeños). Each topping has its own price; size affects base price. Compute the final price for any pizza configuration. Reject invalid combinations (e.g. duplicate toppings if disallowed).

**Variations to explore.**
- Decorator pattern for toppings vs a flat list — compare designs
- Builder pattern with a fluent API: `Pizza.builder().margherita().large().topping(CHEESE).build()`
- Combo offers ("any 2 mediums = ₹599") via Strategy
- Veg-only filter on toppings
- Loyalty discount as a separate Strategy applied at checkout

*Why this problem matters:* Decorator is missing from the original roadmap and shows up often. Pizza / Coffee Shop is the canonical exercise for it.

---

### B) Snake Game
*Suggested placement: Tier 2 (Core), after Logger.*

> "Design the snake game on an N×M board, supporting growth, food, and collision detection."

**Base.**
A snake of initial length 1 moves on a grid one cell per tick. Direction is set by user input (UP / DOWN / LEFT / RIGHT), but the snake cannot reverse onto itself. Eating food grows the snake by one and spawns a new food cell at random. The game ends if the snake hits a wall or itself.

**Variations to explore.**
- Different food types with different scores (Strategy)
- Obstacles placed on the grid
- Two-player mode (two snakes on the same grid)
- Speed increases as the snake grows
- Save / replay using a recorded move history (Memento or Command)

*Why this problem matters:* Google asks this often. Tests game-loop modelling, collision logic, and state encapsulation in a richer way than Tic-Tac-Toe.

---

### C) In-memory Database
*Suggested placement: Tier 3 (Systems), after Library Management.*

> "Design a tiny in-memory relational database supporting create-table, insert, and select with simple where clauses."

**Base.**
Support `CREATE TABLE` with typed columns (string, int, bool). Support `INSERT` of rows and `SELECT * WHERE col = value` queries. Tables live in memory only. Reject inserts that violate the column schema.

**Variations to explore.**
- Indexes on a column for faster equality lookup
- Multiple `WHERE` clauses combined with AND / OR
- Simple transactions (BEGIN / COMMIT / ROLLBACK on a single connection)
- Foreign-key relationships and basic JOIN
- Pluggable storage engine (HashMap-backed vs sorted-map-backed) via Strategy

*Why this problem matters:* Walmart, Flipkart, Atlassian and similar ask variants of this. Tests entity design, query parsing, and (in transactions) snapshot/lock thinking — all without leaving LLD territory.

---

### D) Producer-Consumer with Bounded Buffer
*Suggested placement: Tier 4 (Concurrency), after Cache.*

> "Design a thread-safe bounded buffer that producers can `put` to and consumers can `take` from."

**Base.**
Fixed capacity N. Producers calling `put` block when full; consumers calling `take` block when empty. Both operations must be thread-safe. No data is lost or duplicated.

**Variations to explore.**
- Multiple producers and multiple consumers (correctness under contention)
- Timed `put` / `take` that returns false on timeout instead of blocking
- Priority queue variant (consumers always get the highest-priority item)
- Drop-oldest vs drop-newest policy when full (Strategy)
- Implementation comparison: `ReentrantLock + Condition` vs `Semaphore` vs lock-free

*Why this problem matters:* Concurrency staple. Walmart / Flipkart concurrency rounds nearly always include a producer-consumer variant. Tests `wait` / `notify`, lock fairness, and signalling — concepts you'll reuse in every other concurrent design.

---

### E) Pub/Sub System (Kafka-lite)
*Suggested placement: Tier 4 (Concurrency), after Notification System.*

> "Design an in-process publish-subscribe system where publishers post messages to topics and subscribers receive messages on topics they subscribe to."

**Base.**
Topics are named strings. Publishers call `publish(topic, message)`; subscribers call `subscribe(topic, handler)`. Every message published to a topic is delivered to every current subscriber on that topic. Deliveries are best-effort and in publish order per topic.

**Variations to explore.**
- Multi-threaded delivery: each subscriber runs on its own thread; slow subscribers don't block the publisher
- Per-topic message retention with offsets (subscribers can replay from offset N)
- Consumer groups: a message is delivered to *one* subscriber within a group, not all
- Wildcard topic patterns (`orders.*`)
- Backpressure: bounded per-subscriber queue with a drop-or-block policy

*Why this problem matters:* This is the #1 problem to expect at Confluent. Variants show up at Flipkart, PhonePe, and any company building event-driven systems. Even if you skip the rest of the supplement, do this one.

---

## Tier 5 refocus proposal (LLD-only)

To honour the "this repo is for LLD rounds, not HLD rounds" goal, two of the five Tier 5 problems in the original PDF lean toward HLD / system design and aren't a good fit:

| Problem                              | Verdict           | Suggestion                                                                                                                           |
|--------------------------------------|-------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| 21) Chess Game                       | Pure LLD          | Keep as-is                                                                                                                           |
| 22) Distributed Key-Value Store      | HLD               | **Drop** from this repo's roadmap                                                                                                    |
| 23) URL Shortener                    | Half HLD          | **Refocus** on the LLD slice only: encoding strategy (base62 / hash), collision handling, click-analytics object model               |
| 24) Calendar / Meeting Scheduler     | Pure LLD          | Keep as-is                                                                                                                           |
| 25) Stock Exchange / Order Matching  | Pure LLD          | Keep as-is                                                                                                                           |

If you accept this, the changes are: delete `e_stretch/kvstore/`, rewrite the URL Shortener spec to LLD-only, and update `README.md` accordingly.

---

## Suggested updated starter sequence

Original PDF starter five: **Tic-Tac-Toe → Vending Machine → Splitwise → Logger → Elevator**.

Lightly modified for your target companies, still beginner-friendly:

**Tic-Tac-Toe → Vending Machine → Pizza Ordering → Splitwise → Logger → Producer-Consumer → Elevator**

This adds Decorator (Pizza) and a basic concurrency primitive (Producer-Consumer) before the Elevator — which itself is a more demanding concurrency problem. By the end of these seven, you'll have touched every major pattern most LLD rounds at your target companies care about.