package io.github.swampus.aigovwallet.application.service;

import io.github.swampus.aigovwallet.application.port.WalletRepository;
import io.github.swampus.aigovwallet.domain.Wallet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WalletServiceTest {

    @Test
    void shouldTransferMoneyBetweenWallets() {
        var repository = new InMemoryWalletRepository();
        var service = new WalletService(repository);

        service.deposit("alice", 100);
        service.transfer("alice", "bob", 40);

        assertEquals(60, service.getBalance("alice"));
        assertEquals(40, service.getBalance("bob"));
    }

    private static final class InMemoryWalletRepository implements WalletRepository {
        private final Map<String, Wallet> storage = new HashMap<>();

        @Override
        public Optional<Wallet> findByUserId(String userId) {
            return Optional.ofNullable(storage.get(userId));
        }

        @Override
        public Wallet save(Wallet wallet) {
            storage.put(wallet.getUserId(), wallet);
            return wallet;
        }
    }
}
