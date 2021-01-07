package com.ogqcorp.market.common.adapter;

import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/08 3:35 오후 Last Modified At: 2020/12/08
 */
public interface MonoAdapterController<I, O> {
  Mono<O> execute(I input);
}
