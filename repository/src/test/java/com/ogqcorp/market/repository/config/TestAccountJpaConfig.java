package com.ogqcorp.market.repository.config;

import static com.ogqcorp.market.common.constants.DataSourceName.ACCOUNT_DATA_SOURCE;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaDialect;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 7:19 오후 Last Modified At: 2020/12/16
 */
@Configuration
public class TestAccountJpaConfig extends AccountJpaConfig {

  @Autowired
  public TestAccountJpaConfig(
      @Qualifier("market") JpaDialect jpaDialect,
      @Qualifier(ACCOUNT_DATA_SOURCE) HikariDataSource dataSource) {
    super(jpaDialect, dataSource);
  }

  @Configuration
  public static class TestAccountRepoConfig extends AccountRepoConfig {}

}
