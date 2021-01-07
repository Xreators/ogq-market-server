package com.ogqcorp.market.common.filter;

import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/11/23 7:25 오후 Last Modified At: 2020/11/23
 */
public interface RouteFilter {
  Mono<ServerResponse> filter(ServerRequest serverRequest, HandlerFunction<ServerResponse> func);
  Mono<ServerResponse> filter2(ServerRequest serverRequest, HandlerFunction<ServerResponse> func);
  ServerRequest before(ServerRequest serverRequest);
  ServerResponse after(ServerRequest serverRequest, ServerResponse serverResponse);
  boolean errLog(Throwable e);
  Mono<ServerResponse> errResLogging(Throwable e, ServerRequest req);
}
