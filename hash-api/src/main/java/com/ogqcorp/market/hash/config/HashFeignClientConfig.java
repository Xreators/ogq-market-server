package com.ogqcorp.market.hash.config;

import com.ogqcorp.market.hash.interfaces.rpc.UciApiRpc;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.reactive.ReactorFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 3:00 오후 Last Modified At: 2020/12/18
 */
@Configuration
@EnableFeignClients
public class HashFeignClientConfig {

  @Bean
  public Decoder decoder() {
    return new GsonDecoder();
  }

  @Bean
  public Encoder encoder() {
    return new GsonEncoder();
  }

  @Bean
  public UciApiRpc uciApiRpc(@Value("${rpc.uci-api}") String uciRpcPath) {
    return ReactorFeign.builder()
      .encoder(encoder())
      .decoder(decoder())
      .target(UciApiRpc.class, uciRpcPath);
  }

}
