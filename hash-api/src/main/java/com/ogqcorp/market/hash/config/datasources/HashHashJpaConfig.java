package com.ogqcorp.market.hash.config.datasources;

import static com.ogqcorp.market.common.constants.DataSourceName.HASH_DATA_SOURCE;

import com.ogqcorp.market.repository.config.HashJpaConfig;
import com.ogqcorp.market.repository.config.HashRepoConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaDialect;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 4:15 오후 Last Modified At: 2020/12/17
 */
@Configuration
public class HashHashJpaConfig extends HashJpaConfig {

  @Autowired
  public HashHashJpaConfig(
    JpaDialect jpaDialect,
      @Qualifier(HASH_DATA_SOURCE) HikariDataSource dataSource) {
    super(jpaDialect, dataSource);
  }

  @Configuration
  public static class HashHashRepoConfig extends HashRepoConfig {}

}
