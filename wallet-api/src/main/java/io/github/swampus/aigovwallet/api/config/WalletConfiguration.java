package io.github.swampus.aigovwallet.api.config;

import io.github.swampus.aigovwallet.application.port.WalletRepository;
import io.github.swampus.aigovwallet.application.service.WalletService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletConfiguration {

    @Bean
    public WalletService walletService(WalletRepository walletRepository) {
        return new WalletService(walletRepository);
    }
}
