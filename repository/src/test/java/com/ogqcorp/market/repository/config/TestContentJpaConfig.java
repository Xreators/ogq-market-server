package com.ogqcorp.market.repository.config;

import static com.ogqcorp.market.common.constants.DataSourceName.CONTENT_DATA_SOURCE;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaDialect;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 10:09 오전 Last Modified At: 2020/12/17
 */
@Configuration
public class TestContentJpaConfig extends ContentJpaConfig {

  @Autowired
  public TestContentJpaConfig(
      @Qualifier("market") JpaDialect jpaDialect,
      @Qualifier(CONTENT_DATA_SOURCE) HikariDataSource dataSource) {
    super(jpaDialect, dataSource);
  }

  @Configuration
  public static class TestContentRepoConfig extends ContentRepoConfig {}

}
