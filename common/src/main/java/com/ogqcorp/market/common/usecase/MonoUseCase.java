package com.ogqcorp.market.common.usecase;

import java.util.function.Function;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/08 6:53 오후 Last Modified At: 2020/12/08
 */
public interface MonoUseCase<I, O> extends Function<I, Mono<O>> {}
