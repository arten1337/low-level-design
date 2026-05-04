package arten.dev;

/**
 * The Dojo is where forms are practiced until they become instinct.
 * <p>
 * This entry point exists only to enumerate what's inside the repo.
 * Each problem ships its own {@code Main} demo within its own package —
 * run those directly, not this one.
 */
public class Dojo {

    public static void main(String[] args) {
        System.out.println("""
                ┌─────────────────────────────────────────────┐
                │  Low-Level Design Dojo                      │
                │  25 problems · 5 tiers                      │
                └─────────────────────────────────────────────┘

                  Tier 1  Foundations    arten.dev.a_foundations.*
                  Tier 2  Core           arten.dev.b_core.*
                  Tier 3  Systems        arten.dev.c_systems.*
                  Tier 4  Concurrency    arten.dev.d_concurrency.*
                  Tier 5  Stretch        arten.dev.e_stretch.*

                Each problem has its own Main inside its package.
                Specs and variations: docs/lld-problems.pdf
                """);
    }
}