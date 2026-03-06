package io.github.swampus.aigovwallet.api.controller;

import io.github.swampus.aigovwallet.application.service.WalletService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/wallet/deposit")
    public Map<String, Object> deposit(@RequestParam String userId,
                                       @RequestParam long amount) {
        var balance = walletService.deposit(userId, amount);
        return Map.of(
                "userId", userId,
                "balance", balance
        );
    }

    @PostMapping("/wallet/transfer")
    public Map<String, Object> transfer(@RequestParam String from,
                                        @RequestParam String to,
                                        @RequestParam long amount) {
        walletService.transfer(from, to, amount);
        return Map.of(
                "from", from,
                "to", to,
                "amount", amount
        );
    }

    @GetMapping("/wallet/{userId}")
    public Map<String, Object> balance(@org.springframework.web.bind.annotation.PathVariable String userId) {
        return Map.of(
                "userId", userId,
                "balance", walletService.getBalance(userId)
        );
    }
}
