package com.ogqcorp.market.repository.config;

import static com.ogqcorp.market.common.constants.TransactionManager.CONTENT_TRANSACTION_MANAGER;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 10:24 오전 Last Modified At: 2020/12/17
 */

@EnableJpaRepositories(
    basePackages = {"com.ogqcorp.market.repository.persistence.content"},
    entityManagerFactoryRef = "contentEntityManagerFactory",
    transactionManagerRef = CONTENT_TRANSACTION_MANAGER
)
public abstract class ContentRepoConfig {}