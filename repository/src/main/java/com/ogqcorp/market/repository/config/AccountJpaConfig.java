package com.ogqcorp.market.repository.config;

import static com.ogqcorp.market.common.constants.TransactionManager.ACCOUNT_TRANSACTION_MANAGER;

import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 6:48 오후 Last Modified At: 2020/12/16
 */
public abstract class AccountJpaConfig {
  private JpaDialect jpaDialect;
  private HikariDataSource dataSource;

  public AccountJpaConfig(JpaDialect jpaDialect, HikariDataSource dataSource) {
    this.jpaDialect = jpaDialect;
    this.dataSource = dataSource;
  }

  @Bean(name = "accountEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    LocalContainerEntityManagerFactoryBean factoryBean = builder
        .dataSource(dataSource)
        .packages("com.ogqcorp.market.domain.account")
        .persistenceUnit("account")
        .build();
    Map<String, String> properties = new HashMap<>();
    properties.put("hibernate.jdbc.time_zone ", "UTC");
    factoryBean.setJpaPropertyMap(properties);
    factoryBean.setJpaDialect(jpaDialect);
    return factoryBean;
  }

  @Bean(ACCOUNT_TRANSACTION_MANAGER)
  public PlatformTransactionManager accountTransactionManager(@Qualifier("accountEntityManagerFactory") LocalContainerEntityManagerFactoryBean bean) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(bean.getObject());
    return transactionManager;
  }

  @Bean("accountTransactionTemplate")
  public TransactionTemplate accountTransactionTemplate(@Qualifier(ACCOUNT_TRANSACTION_MANAGER) @Autowired PlatformTransactionManager transactionManager) {
    return new TransactionTemplate(transactionManager);
  }

}
