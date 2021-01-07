package com.ogqcorp.market.common.authentication;

import static com.ogqcorp.market.common.error.ErrorCodes.DefaultErrorCodes.NO_AUTHENTICATED_EXCEPTION;

import com.google.gson.Gson;
import com.ogqcorp.market.common.handler.response.FailureResponse;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/07/23 2:08 오후 Last Modified At: 2020/07/23
 */
@Service
public class MarketAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

  private static final String AUTHENTICATE_HEADER_NAME = "WWW-Authenticate";

  private Gson gson;

  @Autowired
  public MarketAuthenticationEntryPoint(Gson gson) {
    this.gson = gson;
  }

  @Override
  public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
    serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
    serverWebExchange.getResponse().getHeaders().add(AUTHENTICATE_HEADER_NAME, MarketAuthenticationConverter.MARKET_TOKEN_NAME);
    serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    Mono<DataBuffer> mono = Mono.fromCallable(() ->
      serverWebExchange.getResponse().bufferFactory()
        .wrap(
          gson.toJson(new FailureResponse<>(NO_AUTHENTICATED_EXCEPTION, e.getMessage())).getBytes(StandardCharsets.UTF_8)
        )
    );
    return serverWebExchange.getResponse().writeWith(mono);
  }
}
