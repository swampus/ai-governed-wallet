package io.github.swampus.aigovwallet.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void shouldIncreaseBalanceOnDeposit() {
        var wallet = new Wallet("alice");

        wallet.deposit(100);

        assertEquals(100, wallet.getBalance());
    }

    @Test
    void shouldRejectNegativeBalance() {
        var wallet = new Wallet("alice");

        var exception = assertThrows(IllegalStateException.class, () -> wallet.withdraw(1));

        assertTrue(exception.getMessage().contains("Invariant violation"));
    }

    @Test
    void shouldRejectNonPositiveAmount() {
        var wallet = new Wallet("alice");

        assertThrows(IllegalArgumentException.class, () -> wallet.deposit(0));
        assertThrows(IllegalArgumentException.class, () -> wallet.withdraw(-5));
    }
}
