package com.ogqcorp.market.common.adapter;

import reactor.core.publisher.Flux;

public interface FluxAdapterController<I, O> {
  Flux<O> execute(I input);
}