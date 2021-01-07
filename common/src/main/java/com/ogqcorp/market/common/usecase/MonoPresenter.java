package com.ogqcorp.market.common.usecase;

import java.util.function.Function;
import reactor.core.publisher.Mono;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 11:55 오전 Last Modified At: 2020/12/09
 */
public interface MonoPresenter<I, O> extends Function<I, Mono<O>> {}

