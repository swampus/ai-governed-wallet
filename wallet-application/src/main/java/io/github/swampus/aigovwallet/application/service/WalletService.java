package io.github.swampus.aigovwallet.application.service;

import io.github.swampus.aigovwallet.application.port.WalletRepository;
import io.github.swampus.aigovwallet.domain.Wallet;

/**
 * Application service that coordinates use cases while preserving domain invariants.
 */
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public long deposit(String userId, long amount) {
        var wallet = walletRepository.findByUserId(userId).orElseGet(() -> new Wallet(userId));
        wallet.deposit(amount);
        walletRepository.save(wallet);
        return wallet.getBalance();
    }

    public void transfer(String fromUserId, String toUserId, long amount) {
        var source = walletRepository.findByUserId(fromUserId).orElseGet(() -> new Wallet(fromUserId));
        var target = walletRepository.findByUserId(toUserId).orElseGet(() -> new Wallet(toUserId));

        source.withdraw(amount);
        target.deposit(amount);

        walletRepository.save(source);
        walletRepository.save(target);
    }

    public long getBalance(String userId) {
        return walletRepository.findByUserId(userId)
                .map(Wallet::getBalance)
                .orElse(0L);
    }
}
