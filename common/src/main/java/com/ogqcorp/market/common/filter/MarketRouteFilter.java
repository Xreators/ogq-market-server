package com.ogqcorp.market.common.filter;


import static com.ogqcorp.market.common.error.ErrorCodes.DefaultErrorCodes.UNKNOWN_SERVER_ERROR_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;
import com.ogqcorp.market.common.handler.response.FailureResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/11/06 4:23 오후 Last Modified At: 2020/11/06
 */
public class MarketRouteFilter implements RouteFilter {
  private Logger logger = LoggerFactory.getLogger(MarketRouteFilter.class);

  @Override
  public Mono<ServerResponse> filter(ServerRequest serverRequest, HandlerFunction<ServerResponse> next) {
    // check auth or param
    logger.debug("filter-handler-before: {}", logId(serverRequest));

    return next
      .handle(serverRequest)
      .doOnError(e -> logger.error("filter-handler-after-error: {} {}", logId(serverRequest), e.getMessage()))
      ;
  }
  @Override
  public Mono<ServerResponse> filter2(ServerRequest serverRequest, HandlerFunction<ServerResponse> next) {
    // check auth or param
    logger.debug("filter2-handler-before: {}", logId(serverRequest));

    return next
      .handle(serverRequest)
      .doOnError(e -> logger.error("filter2-handler-after-error: {} {}", logId(serverRequest), e.getMessage()))
      ;
  }
  @Override
  public ServerRequest before(ServerRequest serverRequest) {
    logger.debug("Start(before): {}", logId(serverRequest));
    return serverRequest;
  }
  @Override
  public ServerResponse after(ServerRequest serverRequest, ServerResponse serverResponse) {
    logger.info("end(after): {} {}", logId(serverRequest), serverResponse.statusCode());
    return serverResponse;
  }
  @Override
  public boolean errLog(Throwable e) {
    logger.error("onError-before(checker): {}", e.getMessage());
    return !(e instanceof MarketException);
  }
  @Override
  public Mono<ServerResponse> errResLogging(Throwable e, ServerRequest req) {
    return ServerResponse
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(BodyInserters.fromValue(new FailureResponse(UNKNOWN_SERVER_ERROR_EXCEPTION, e.getMessage())))
      .doOnTerminate(() -> logger.error("onError-after: {}", req.path(), e));
  }

  private Object logId(ServerRequest serverRequest) {
    return serverRequest.attribute(ServerWebExchange.LOG_ID_ATTRIBUTE).orElseGet(() -> "NULL");
  }

}
