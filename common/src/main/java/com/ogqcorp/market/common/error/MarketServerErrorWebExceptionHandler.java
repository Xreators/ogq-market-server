package com.ogqcorp.market.common.error;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/07/23 3:19 오후 Last Modified At: 2020/07/23
 */
@Order(-2)
public class MarketServerErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

  private List<String> accessPaths;

  public MarketServerErrorWebExceptionHandler(
    final Resources resources,
    final ApplicationContext applicationContext,
    final ServerCodecConfigurer serverCodecConfigurer,
    final MarketServerErrorAttributes marketServerErrorAttributes,
    final List<String> accessPaths
  ) {
    super(marketServerErrorAttributes, resources, applicationContext);
    super.setMessageWriters(serverCodecConfigurer.getWriters());
    super.setMessageReaders(serverCodecConfigurer.getReaders());
    this.accessPaths = accessPaths;
  }

  @Override
  protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
    return
      RouterFunctions.route(
        getRequestPredicate(accessPaths),
        this::getErrorServerResponse
      );
  }

  protected Mono<ServerResponse> getErrorServerResponse(final ServerRequest serverRequest) {
    Map<String, Object> errorAttributes = getDefaultErrorAttr(serverRequest);

    HttpStatus status = getHttpStatusFromErrorType(
        errorAttributes.get(MarketServerErrorAttributes.KEY_TYPE)
    );
    errorAttributes.put(MarketServerErrorAttributes.KEY_STATUS, status.value());

    return ServerResponse
      .status(status)
      .contentType(MediaType.APPLICATION_JSON)
      .body(BodyInserters.fromValue(errorAttributes));
  }

  protected Map<String, Object> getDefaultErrorAttr(final ServerRequest serverRequest) {
    return getErrorAttributes(
      serverRequest,
      marketErrorAttr()
    );
  }
  protected final ErrorAttributeOptions marketErrorAttr() {
    return ErrorAttributeOptions.of(Include.BINDING_ERRORS, Include.EXCEPTION, Include.STACK_TRACE, Include.MESSAGE);
  }
  protected final ErrorAttributeOptions emptyErrorAttr() {
    return ErrorAttributeOptions.defaults();
  }
  private RequestPredicate getRequestPredicate(List<String> paths) {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    return req -> paths.stream().anyMatch(s -> antPathMatcher.match(s.trim(), req.path()));
  }
  private HttpStatus getHttpStatusFromErrorType(Object type) {
    return Optional.ofNullable(type)
      .map(Object::toString)
      .map(this::getHttpStatus)
      .orElseGet(() -> HttpStatus.INTERNAL_SERVER_ERROR)
      ;
  }
  private HttpStatus getHttpStatus(String errAttr) {
    if (MarketServerErrorAttributes.VALUE_BAD_REQ.equals(errAttr)) {
      return HttpStatus.BAD_REQUEST;
    }
    return MarketServerErrorAttributes.VALUE_NOT_FOUND.equals(errAttr) ?
      HttpStatus.NOT_FOUND :
      HttpStatus.INTERNAL_SERVER_ERROR
      ;
  }
}
