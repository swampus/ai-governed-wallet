package io.github.swampus.aigovwallet.domain;

/**
 * Domain entity representing a wallet.
 * Invariant: balance must never become negative.
 */
public class Wallet {

    private final String userId;
    private long balance;

    public Wallet(String userId) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("User ID must not be blank.");
        }
        this.userId = userId;
        this.balance = 0L;
    }

    public void deposit(long amount) {
        requirePositiveAmount(amount);
        balance += amount;
    }

    public void withdraw(long amount) {
        requirePositiveAmount(amount);
        if (balance - amount < 0) {
            throw new IllegalStateException("Invariant violation: balance must not become negative.");
        }
        balance -= amount;
    }

    public String getUserId() {
        return userId;
    }

    public long getBalance() {
        return balance;
    }

    private static void requirePositiveAmount(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }
}
