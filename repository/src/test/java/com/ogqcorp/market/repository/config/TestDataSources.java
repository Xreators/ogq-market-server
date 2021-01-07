package com.ogqcorp.market.repository.config;

import static com.ogqcorp.market.common.constants.DataSourceName.ACCOUNT_DATA_SOURCE;
import static com.ogqcorp.market.common.constants.DataSourceName.CONTENT_DATA_SOURCE;
import static com.ogqcorp.market.common.constants.DataSourceName.HASH_DATA_SOURCE;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 7:22 오후 Last Modified At: 2020/12/16
 */
@Configuration
public class TestDataSources {

  @Bean(ACCOUNT_DATA_SOURCE)
  @ConfigurationProperties("datasource.account")
  public HikariDataSource accountDataSource () {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean(CONTENT_DATA_SOURCE)
  @ConfigurationProperties("datasource.content")
  public HikariDataSource contentDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean(HASH_DATA_SOURCE)
  @ConfigurationProperties("datasource.hash")
  public HikariDataSource hashDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

}
