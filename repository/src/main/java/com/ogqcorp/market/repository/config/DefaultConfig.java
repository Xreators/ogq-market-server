package com.ogqcorp.market.repository.config;

import java.util.HashMap;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/07/22 4:00 오후 Last Modified At: 2020/07/22
 */
@Configuration
public class DefaultConfig {

  @Bean("market")
  public JpaDialect jpaDialect() {
    return new HibernateJpaDialect();
  }

  @Bean
  public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
    return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
  }

}
