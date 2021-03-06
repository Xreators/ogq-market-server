package com.ogqcorp.market.relay.config.datasources;

import static com.ogqcorp.market.common.constants.DataSourceName.CONTENT_DATA_SOURCE;

import com.ogqcorp.market.repository.config.ContentJpaConfig;
import com.ogqcorp.market.repository.config.ContentRepoConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.stereotype.Service;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 4:13 오후 Last Modified At: 2020/12/17
 */
@Configuration
public class RelayContentJpaConfig extends ContentJpaConfig {

  public RelayContentJpaConfig(
    JpaDialect jpaDialect,
    @Qualifier(CONTENT_DATA_SOURCE) HikariDataSource dataSource
  ) {
    super(jpaDialect, dataSource);
  }
  @Configuration
  public static class RelayContentRepoConfig extends ContentRepoConfig {}

}
