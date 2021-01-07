package com.ogqcorp.market.common.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/11/30 2:27 오후 Last Modified At: 2020/11/30
 */
public abstract class MarketLoggingWebFilter implements WebFilter {
  private Logger logger = LoggerFactory.getLogger(MarketLoggingWebFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    if(exchange.getRequest().getPath().value().equalsIgnoreCase("/ping")) {
      return chain.filter(exchange);
    }
    ServerHttpRequest request = exchange.getRequest();
    logger.info("request: {} {} {} {}",
      request.getId(),
      request.getMethodValue(),
      request.getPath(),
      request.getQueryParams().isEmpty() ? "" : request.getQueryParams()
    );
    return chain.filter(decorate(exchange));
  }
  private ServerWebExchange decorate(ServerWebExchange exchange) {
    return new CustomServerWebExchange(exchange);
  }
  private class CustomServerWebExchange extends ServerWebExchangeDecorator {
    protected CustomServerWebExchange(ServerWebExchange delegate) {
      super(delegate);
    }

    @Override
    public ServerHttpRequest getRequest() {
      return new CustomServerHttpRequest(super.getRequest());
    }
  }

  private class CustomServerHttpRequest extends ServerHttpRequestDecorator {
    public CustomServerHttpRequest(ServerHttpRequest delegate) {
      super(delegate);
    }

    @Override
    public Flux<DataBuffer> getBody() {
      if (logger.isInfoEnabled()) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        return super.getBody()
          .map(dataBuffer -> this.getLoggableDataBuffer(baos, dataBuffer))
          .doOnComplete(() -> loggingPayload(getDelegate(), baos));
      } else {
        return super.getBody();
      }
    }
    private DataBuffer getLoggableDataBuffer(ByteArrayOutputStream baos, DataBuffer dataBuffer) {
      try(WritableByteChannel channel = Channels.newChannel(baos)) {
        channel.write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
      } catch (IOException e) {
        logger.error("", e);
      }
      return dataBuffer;
    }
    private void loggingPayload(ServerHttpRequest request, ByteArrayOutputStream baos) {
      logger.info("request: {} {}", request.getId(), baos);
    }
  }

}
