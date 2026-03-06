package io.github.swampus.aigovwallet.application.port;

import io.github.swampus.aigovwallet.domain.Wallet;

import java.util.Optional;

public interface WalletRepository {

    Optional<Wallet> findByUserId(String userId);

    Wallet save(Wallet wallet);
}
