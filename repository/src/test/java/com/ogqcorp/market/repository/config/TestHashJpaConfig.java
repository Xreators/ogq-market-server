package com.ogqcorp.market.repository.config;

import static com.ogqcorp.market.common.constants.DataSourceName.HASH_DATA_SOURCE;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaDialect;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 10:14 오전 Last Modified At: 2020/12/17
 */
@Configuration
public class TestHashJpaConfig extends HashJpaConfig {

  public TestHashJpaConfig(
    @Qualifier("market") JpaDialect jpaDialect,
    @Qualifier(HASH_DATA_SOURCE) HikariDataSource dataSource
  ) {
    super(jpaDialect, dataSource);
  }

  @Configuration
  public static class TestHashRepoConfig extends HashRepoConfig {}

}
