package com.ogqcorp.market.common.authentication;

import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/07/23 1:58 오후 Last Modified At: 2020/07/23
 */
public class MarketAuthenticationConverter implements ServerAuthenticationConverter {

  public static final String MARKET_TOKEN_NAME = "X-MARKET-AUTH-TOKEN";

  private MarketTokenGenerator marketTokenGenerator;

  public MarketAuthenticationConverter(MarketTokenGenerator marketTokenGenerator) {
    this.marketTokenGenerator = marketTokenGenerator;
  }

  @Override
  public Mono<Authentication> convert(ServerWebExchange exchange) {
    return Mono.fromCallable(() -> {
      Optional<List<String>> opTokens = Optional.ofNullable(exchange.getRequest().getHeaders().get(MARKET_TOKEN_NAME));
      return opTokens
        .map(tokens -> tokens.stream()
          .filter(s -> !StringUtils.isEmpty(s))
          .findFirst()
          .map(token -> {
            MarketToken marketToken = this.marketTokenGenerator.fromToken(token);
            List<GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(new String[]{marketToken.getRole()});
            //Authentication auth = new UsernamePasswordAuthenticationToken(marketToken, null, authorities);
            Authentication auth = new UsernamePasswordAuthenticationToken(marketToken.getUserId(), null, authorities);
            return auth;
          })
          .orElseGet(() -> null)
      )
      .orElseGet(() -> null);
    })
    .onErrorResume(Mono::error);
  }
}
