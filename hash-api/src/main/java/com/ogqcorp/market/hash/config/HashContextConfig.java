package com.ogqcorp.market.hash.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 2:37 오후 Last Modified At: 2020/12/17
 */
@Configuration
public class HashContextConfig {

  @Bean
  public Gson gson() {
    return new Gson();
  }

}
