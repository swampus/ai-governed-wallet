package io.github.swampus.aigovwallet.api.repository;

import io.github.swampus.aigovwallet.application.port.WalletRepository;
import io.github.swampus.aigovwallet.domain.Wallet;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryWalletRepository implements WalletRepository {

    private final Map<String, Wallet> storage = new ConcurrentHashMap<>();

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
