package com.ogqcorp.market.repository.config;

import static com.ogqcorp.market.common.constants.TransactionManager.HASH_TRANSACTION_MANAGER;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 10:11 오전 Last Modified At: 2020/12/17
 */
public class HashJpaConfig {
  private JpaDialect jpaDialect;
  private HikariDataSource dataSource;

  public HashJpaConfig(JpaDialect jpaDialect, HikariDataSource dataSource) {
    this.jpaDialect = jpaDialect;
    this.dataSource = dataSource;
  }


  @Bean(name = "hashEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean hashEntityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    LocalContainerEntityManagerFactoryBean factoryBean = builder
        .dataSource(dataSource)
        .packages("com.ogqcorp.market.domain.hash")
        .persistenceUnit("hash")
        .build();
    Map<String, String> properties = new HashMap<>();
    properties.put("hibernate.jdbc.time_zone ", "UTC");
    factoryBean.setJpaPropertyMap(properties);
    factoryBean.setJpaDialect(jpaDialect);
    return factoryBean;
  }

  @Bean(HASH_TRANSACTION_MANAGER)
  public PlatformTransactionManager hashTransactionManager(@Qualifier("hashEntityManagerFactory") LocalContainerEntityManagerFactoryBean bean) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(bean.getObject());
    return transactionManager;
  }

  @Bean("hashTransactionTemplate")
  public TransactionTemplate hashTransactionTemplate(@Qualifier(HASH_TRANSACTION_MANAGER) @Autowired PlatformTransactionManager transactionManager) {
    return new TransactionTemplate(transactionManager);
  }
}
