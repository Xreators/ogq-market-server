package com.ogqcorp.market.hash.service.rpc.uci;

import java.util.function.Function;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/22 2:36 오후 Last Modified At: 2020/12/22
 */
public final class UciDocService {
  public interface GetUciDocBy<I, O> extends Function<I, Mono<O>> {}

}
