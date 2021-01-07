package com.ogqcorp.market.common.usecase;

import java.util.function.Function;

import reactor.core.publisher.Flux;

public interface FluxUseCase<I, O> extends Function<I, Flux<O>> {}
