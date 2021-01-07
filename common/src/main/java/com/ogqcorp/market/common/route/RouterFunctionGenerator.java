package com.ogqcorp.market.common.route;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.ogqcorp.market.common.filter.RouteFilter;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunctions.Builder;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/11/23 7:28 오후 Last Modified At: 2020/11/23
 */
public final class RouterFunctionGenerator {

  public static Consumer<Builder> generateGetRoute(HandlerFunction<ServerResponse> handlerFunction) {
    return builder -> builder.GET(handlerFunction);
  }
  public static Consumer<Builder> generatePostRoute(HandlerFunction<ServerResponse> handlerFunction) {
    return builder -> builder.POST(handlerFunction);
  }
  public static Consumer<Builder> generatePutRoute(HandlerFunction<ServerResponse> handlerFunction) {
    return builder -> builder.PUT(handlerFunction);
  }
  public static Consumer<Builder> generateDeleteRoute(HandlerFunction<ServerResponse> handlerFunction) {
    return builder -> builder.DELETE(handlerFunction);
  }
  public static Consumer<Builder> generateGetRoute(String pattern, HandlerFunction<ServerResponse> handlerFunction) {
    return builder -> builder.GET(pattern, handlerFunction);
  }
  public static Consumer<Builder> generatePostRoute(String pattern, HandlerFunction<ServerResponse> handlerFunction) {
    return builder -> builder.POST(pattern, handlerFunction);
  }
  public static Consumer<Builder> generatePutRoute(String pattern, HandlerFunction<ServerResponse> handlerFunction) {
    return builder -> builder.PUT(pattern, handlerFunction);
  }
  public static Consumer<Builder> generateDeleteRoute(String pattern, HandlerFunction<ServerResponse> handlerFunction) {
    return builder -> builder.DELETE(pattern, handlerFunction);
  }

  public static Consumer<Builder> generateGetRoute(String pattern, HandlerFunction<ServerResponse> handlerFunction, RouteFilter routeFilter) {
    return builder -> builder.GET(pattern, handlerFunction)
      .before(routeFilter::before)
      .filter(routeFilter::filter)
      .after(routeFilter::after)
      ;
  }
  public static Consumer<Builder> generatePostRoute(String pattern, HandlerFunction<ServerResponse> handlerFunction, RouteFilter routeFilter) {
    return builder -> builder.POST(pattern, handlerFunction)
      .before(routeFilter::before)
      .filter(routeFilter::filter)
      .after(routeFilter::after)
      ;
  }
  public static Consumer<Builder> generatePutRoute(String pattern, HandlerFunction<ServerResponse> handlerFunction, RouteFilter routeFilter) {
    return builder -> builder.PUT(pattern, handlerFunction)
      .before(routeFilter::before)
      .filter(routeFilter::filter)
      .after(routeFilter::after)
      ;
  }
  public static Consumer<Builder> generateDeleteRoute(String pattern, HandlerFunction<ServerResponse> handlerFunction, RouteFilter routeFilter) {
    return builder -> builder.POST(pattern, handlerFunction)
      .before(routeFilter::before)
      .filter(routeFilter::filter)
      .after(routeFilter::after)
      ;
  }
  public static Consumer<Builder> generate(List<Builder> builders, RouteFilter routeFilter) {
    Consumer<Builder> nestConsumer = (con) -> builders.forEach(b -> con.add(b.build()));
    return builderConsumer -> builderConsumer
      .nest(accept(MediaType.APPLICATION_JSON), nestConsumer)
      .before(routeFilter::before)
      .filter(routeFilter::filter)
      .after(routeFilter::after)
      ;
  }
  public static Consumer<Builder> generateFromConsumer(List<Consumer<Builder>> consumers, RouteFilter routeFilter) {
    Consumer<Builder> nestConsumer = (nc) -> consumers.forEach(c ->  c.accept(nc));
    return builderConsumer -> builderConsumer
      .nest(accept(MediaType.APPLICATION_JSON), nestConsumer)
      .before(routeFilter::before)
      .filter(routeFilter::filter)
      .after(routeFilter::after)
      ;
  }

}
