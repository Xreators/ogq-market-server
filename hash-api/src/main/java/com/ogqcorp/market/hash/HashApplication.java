package com.ogqcorp.market.hash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 1:42 오후 Last Modified At: 2020/12/16
 */
@SpringBootApplication(scanBasePackages = {"com.ogqcorp.market"})
public class HashApplication {
  public static void main(String [] args) {
    SpringApplication.run(HashApplication.class, args);
  }

}
