package com.ogqcorp.market.repository.config;

import static com.ogqcorp.market.common.constants.TransactionManager.HASH_TRANSACTION_MANAGER;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 10:26 오전 Last Modified At: 2020/12/17
 */
@EnableJpaRepositories(
    basePackages = {"com.ogqcorp.market.repository.persistence.hash"},
    entityManagerFactoryRef = "hashEntityManagerFactory",
    transactionManagerRef = HASH_TRANSACTION_MANAGER
)
public abstract class HashRepoConfig {

}
