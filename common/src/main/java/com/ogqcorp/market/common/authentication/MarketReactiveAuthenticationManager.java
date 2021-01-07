package com.ogqcorp.market.common.authentication;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/07/23 1:56 오후 Last Modified At: 2020/07/23
 */
@Service
public class MarketReactiveAuthenticationManager implements ReactiveAuthenticationManager {
  @Override
  public Mono<Authentication> authenticate(Authentication authentication) {
    return Mono.fromCallable(() -> authentication);
  }
}
