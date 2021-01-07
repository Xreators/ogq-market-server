package com.ogqcorp.market.relay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/18 6:13 오후 Last Modified At: 2020/12/18
 */
@SpringBootApplication(scanBasePackages = "com.ogqcorp.market")
public class RelayApplication {
  public static void main(String [] args) {
    SpringApplication.run(RelayApplication.class, args);
  }
}
