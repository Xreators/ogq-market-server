package com.ogqcorp.market.relay.config.datasources;

import static com.ogqcorp.market.common.constants.DataSourceName.CONTENT_DATA_SOURCE;
import static com.ogqcorp.market.common.constants.DataSourceName.HASH_DATA_SOURCE;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 4:12 오후 Last Modified At: 2020/12/17
 */
@Configuration
public class RelayDataSources {

  @Bean(CONTENT_DATA_SOURCE)
  @ConfigurationProperties("datasource.content")
  public HikariDataSource contentDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }


  @Bean(HASH_DATA_SOURCE)
  @ConfigurationProperties("datasource.hash")
  public HikariDataSource hashDataSource () {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

}
