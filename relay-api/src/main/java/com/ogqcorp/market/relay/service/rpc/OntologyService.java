package com.ogqcorp.market.relay.service.rpc;

import java.util.function.Function;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 2:24 오후 Last Modified At: 2020/12/21
 */
public final class OntologyService {
  public interface BatchHashes<I, O> extends Function<I, Mono<O>> {}
}
